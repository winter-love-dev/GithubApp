package com.season.winter.core.common.extension.context

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat


fun Context.getDimension(res: Int): Float {
    return resources.getDimension(res)
}

fun Context.getInteger(res: Int): Int {
    return resources.getInteger(res)
}

fun Context.getFontRes(res: Int): Typeface? {
    return ResourcesCompat.getFont(this, res)
}