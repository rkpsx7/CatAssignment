package com.dev_akash.catassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev_akash.catassignment.databinding.ActivityMainBinding
import com.dev_akash.catassignment.feature_catshow.data.repository.CatsPagingAdapter
import com.dev_akash.catassignment.feature_catshow.ui.CatsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private val catsViewModel: CatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val adapter = CatsPagingAdapter()
        binding.apply {
            rvCats.adapter = adapter
            rvCats.layoutManager = LinearLayoutManager(this@MainActivity)
        }
        catsViewModel.catsList.observe(this){
            adapter.submitData(lifecycle,it)
        }
    }
}