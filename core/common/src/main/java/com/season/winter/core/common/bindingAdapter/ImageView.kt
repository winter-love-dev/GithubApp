package com.season.winter.core.common.bindingAdapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.season.winter.core.common.R
import com.season.winter.core.common.views.setImageFromUrl

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {

    if (imageUrl.isNullOrEmpty())
        return

    view.setImageFromUrl(imageUrl)
}


@SuppressLint("UseCompatLoadingForDrawables")
@BindingAdapter("bindLikeStateFromBoolean")
fun bindLikeStateFromBoolean(view: ImageView, liked: Boolean) {

    val res = if (liked) {
        R.drawable.like_filled
    } else {
        R.drawable.like_outlined
    }

    val image = ContextCompat.getDrawable(
        view.context,
        res
    ).also {
        if (liked) it?.setTint(Color.RED)
    }

    view.setImageDrawable(image)
}
