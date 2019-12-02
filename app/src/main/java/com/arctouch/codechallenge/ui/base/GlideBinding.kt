package com.arctouch.codechallenge.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object GlideBinding {

    @JvmStatic
    @BindingAdapter("bind:imageUrlCenterCrop")
    fun loadRemoteImageCenterCrop(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
                .load(imageUrl)
                .centerCrop()
                .into(view)
    }

    @JvmStatic
    @BindingAdapter("bind:imageUrlFitCenter")
    fun loadRemoteImageFitCenter(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
                .load(imageUrl)
                .fitCenter()
                .into(view)
    }

}