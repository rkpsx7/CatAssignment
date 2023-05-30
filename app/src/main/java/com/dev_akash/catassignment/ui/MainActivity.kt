package com.dev_akash.catassignment.ui

import android.os.Bundle
import android.view.MotionEvent
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev_akash.catassignment.R
import com.dev_akash.catassignment.databinding.ActivityMainBinding
import com.dev_akash.catassignment.ui.adapter.CatsPagingAdapter
import com.dev_akash.catassignment.ui.adapter.PagingLoadingAdapter
import com.dev_akash.catassignment.ui.viewmodel.CatsViewModel
import com.dev_akash.catassignment.utils.visibilityGone
import com.dev_akash.catassignment.utils.visibilityVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val catsAdapter = CatsPagingAdapter()

    private val viewModel: CatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.prepareBreedFilterList()

        setMainAdapter()
        setObservers()
        setFilterView()

    }

    private fun setObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.catsList.collectLatest {
                catsAdapter.submitData(it)
            }
        }
    }

    private fun setFilterView() {
        binding.searchView.apply {
            val drawableEnd = compoundDrawablesRelative[2]
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)

            addTextChangedListener { text ->
                val value = text.toString()
                viewModel.setBreedId(value)

                val visibleDrawableEnd = if (value.isBlank()) null else drawableEnd
                setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    visibleDrawableEnd,
                    null
                )
            }

            setOnTouchListener { view, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    if (drawableEnd != null && event.rawX >= (right - drawableEnd.bounds.width())) {
                        setText("")
                        return@setOnTouchListener true
                    }
                }
                performClick()
                return@setOnTouchListener false
            }

            /**
             * shows the suggestion list when in focus
             */
            setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) showDropDown()
            }

            viewModel.filters.observe(this@MainActivity) { filterDtos ->
                val suggestions = filterDtos?.map { it.breedName }
                suggestions?.let {
                    binding.searchView.setAdapter(
                        ArrayAdapter(
                            this@MainActivity, android.R.layout.simple_spinner_dropdown_item, it
                        )
                    )
                }
            }
        }
    }

    private fun setMainAdapter() {
        binding.apply {
            rvCats.adapter = catsAdapter.withLoadStateHeaderAndFooter(
                header = PagingLoadingAdapter(retry = { catsAdapter.retry() }),
                footer = PagingLoadingAdapter(retry = { catsAdapter.retry() })
            )

            rvCats.layoutManager = LinearLayoutManager(this@MainActivity)

            catsAdapter.addLoadStateListener { loadState ->
                when (loadState.refresh) {
                    is LoadState.NotLoading -> {
                        binding.progressBar.visibilityGone()
                    }

                    is LoadState.Loading -> {
                        binding.progressBar.visibilityVisible()
                    }

                    is LoadState.Error -> {
                        binding.progressBar.visibilityGone()
                    }
                }

            }
        }
    }
}