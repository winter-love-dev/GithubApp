package com.season.winter.core.common.domain

import android.view.View
import androidx.fragment.app.Fragment
import com.season.winter.core.common.util.fragment.SelectFragmentTransaction

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