package com.season.winter.core.common.views

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageFromUrl(imageUrl: String?) {

    if (imageUrl.isNullOrEmpty())
        return

    Glide.with(context).load(imageUrl)
//        .transition(DrawableTransitionOptions.withCrossFade())
//        .error(R.drawable.logo_icon_small)
//        .fallback(R.drawable.logo_icon_small)
//        .placeholder(R.drawable.loading)
        .into(this)
}
