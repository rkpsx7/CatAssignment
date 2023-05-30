package com.dev_akash.catassignment.utils

import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dev_akash.catassignment.R


fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .transform(CenterCrop(),RoundedCorners(24))
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(R.drawable.placeholder)
        .into(this)
}


fun AppCompatImageView.loadImage(@DrawableRes img: Int?) {
    Glide.with(this.context)
        .load(img)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

fun View.visibilityGone(){
    this.isVisible = false
}

fun View.visibilityVisible(){
    this.isVisible = true
}
