package com.season.winter.core.common.extension.activity

import android.app.Activity
import android.content.Intent

enum class CBActivityTransition {
    LEFT,
    RIGHT,
    UP,
    DOWN,
}

fun <T: Activity> Activity.customStartActivity(
    activity: Class<T>,
    finishThisActivity: Boolean,
    transition: CBActivityTransition? = null
) = Intent(this, activity).run {

    if(transition == null)
        addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

    startActivity(this)
    if (finishThisActivity)
        finish()
}