package com.raju.rigi_sample.utils

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import com.raju.rigi_sample.R

fun ShapeableImageView.loadCenterCropImageFromUrl(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()){
        Glide.with(this)
            .load(R.drawable.placeholder)
            .centerCrop()
            .into(this)
    }else{
        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(this)
    }
}


fun AppCompatImageView.loadImageFromUrl(imageUrl: String?) {

    if (imageUrl.isNullOrEmpty()){
        Glide.with(this)
            .load(R.drawable.placeholder)
            .centerCrop()
            .into(this)
    }else{
        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(this)
    }

}

fun AppCompatImageView.loadImageFromUrlWithoutScaleType(imageUrl: String?) {

    if (imageUrl.isNullOrEmpty()){
        Glide.with(this)
            .load(R.drawable.placeholder)
            .into(this)
    }else{
        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)
    }
}

fun ImageView.loadImageFromUrl(imageUrl: String?) {

    if (imageUrl.isNullOrEmpty()){
        Glide.with(this)
            .load(R.drawable.placeholder)
            .centerCrop()
            .into(this)
    }else{
        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(this)
    }

}

fun ImageView.loadImageFromUrlWithoutScaleType(imageUrl: String?) {

    if (imageUrl.isNullOrEmpty()){
        Glide.with(this)
            .load(R.drawable.placeholder)
            .into(this)
    }else{
        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(this)
    }
}

