package com.season.winter.core.common.fragment.util

import android.view.View
import androidx.fragment.app.Fragment

interface FragmentService {

    fun startFragment(
        frameBase: View,
        fragment: Fragment,
        transaction: SelectFragmentTransaction = SelectFragmentTransaction.Commit,
        addToBackStack: Boolean = true
    )

    fun goBack()

    fun clearBackStack()

    fun removeFragment(
        fragment: Fragment,
        transaction: SelectFragmentTransaction = SelectFragmentTransaction.Commit,
    )

    fun currentFragment(frameBase: View): Fragment?

    fun isContainsBackStackThisFragment(fragment: Fragment): Boolean
}