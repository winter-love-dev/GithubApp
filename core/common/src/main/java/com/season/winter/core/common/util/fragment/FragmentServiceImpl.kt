package com.season.winter.core.common.util.fragment

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.season.winter.core.common.domain.FragmentService
import javax.inject.Inject

class FragmentServiceImpl @Inject constructor(
    private val fragmentActivity: FragmentActivity,
): FragmentService {

//    private val fragmentActivity = context as FragmentActivity

    @SuppressLint("CommitTransaction")
    override fun startFragment(
        frameBase: View,
        fragment: Fragment,
        transaction: SelectFragmentTransaction,
        addToBackStack: Boolean
    ) {
        fragmentActivity.supportFragmentManager.beginTransaction().run {
            replace(frameBase.id, fragment)
            if (addToBackStack)
                addToBackStack(fragment::class.java.simpleName)
            selectFragmentTransition(transaction)
        }
    }


    override fun goBack() = fragmentActivity.supportFragmentManager.popBackStack()

    override fun clearBackStack() = fragmentActivity.supportFragmentManager.run {
        repeat(backStackEntryCount) {
            popBackStackImmediate()
        }
    }

    @SuppressLint("CommitTransaction")
    override fun removeFragment(
        fragment: Fragment,
        transaction: SelectFragmentTransaction,
    ) = fragmentActivity.supportFragmentManager.beginTransaction().run {
        remove(fragment)
        selectFragmentTransition(transaction)
    }

    override fun currentFragment(frameBase: View): Fragment? =
        fragmentActivity.supportFragmentManager.findFragmentById(frameBase.id)

    override fun isContainsBackStackThisFragment(fragment: Fragment): Boolean {
        return fragmentActivity.supportFragmentManager.run {
            repeat(backStackEntryCount) { i ->
                val entry = getBackStackEntryAt(i)
                return entry.name == fragment::class.java.simpleName
            }
            false
        }
    }
}