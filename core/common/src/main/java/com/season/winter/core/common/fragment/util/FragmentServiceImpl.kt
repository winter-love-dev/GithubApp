package com.season.winter.core.common.fragment.util

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class FragmentServiceImpl(
    private val context: FragmentActivity,
): FragmentService {

    @SuppressLint("CommitTransaction")
    override fun startFragment(
        frameBase: View,
        fragment: Fragment,
        transaction: SelectFragmentTransaction,
        addToBackStack: Boolean
    ) {
        context.supportFragmentManager.beginTransaction().run {
            replace(frameBase.id, fragment)
            if (addToBackStack)
                addToBackStack(fragment::class.java.simpleName)
            safeCommit(transaction)
        }
    }


    override fun goBack() = context.supportFragmentManager.popBackStack()

    override fun clearBackStack() = context.supportFragmentManager.run {
        repeat(backStackEntryCount) {
            popBackStackImmediate()
        }
    }

    @SuppressLint("CommitTransaction")
    override fun removeFragment(
        fragment: Fragment,
        transaction: SelectFragmentTransaction,
    ) = context.supportFragmentManager.beginTransaction().run {
        remove(fragment)
        safeCommit(transaction)
    }

    override fun currentFragment(frameBase: View): Fragment? =
        context.supportFragmentManager.findFragmentById(frameBase.id)

    override fun isContainsBackStackThisFragment(fragment: Fragment): Boolean {
        return context.supportFragmentManager.run {
            repeat(backStackEntryCount) { i ->
                val entry = getBackStackEntryAt(i)
                return entry.name == fragment::class.java.simpleName
            }
            false
        }
    }
}