//package com.season.winter.core.common.fragment
//
//import android.annotation.SuppressLint
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//
//class FragmentManager(
//    private val context: FragmentActivity,
//    private var frameBase: View
//) {
//
//    @SuppressLint("CommitTransaction")
//    fun startFragment(
//        fragment: Fragment,
//        transaction: SelectTransaction = SelectTransaction.Commit,
//        addToBackStack: Boolean = true
//    ) {
//        context.supportFragmentManager.beginTransaction().run {
//            replace(frameBase.id, fragment)
//            if (addToBackStack)
//                addToBackStack(fragment::class.java.simpleName)
//            safeCommit(transaction)
//        }
//    }
//
//
//    fun goBack() = context.supportFragmentManager.popBackStack()
//
//    fun clearBackStack() = context.supportFragmentManager.run {
//        repeat(backStackEntryCount) {
//            popBackStackImmediate()
//        }
//    }
//
//    @SuppressLint("CommitTransaction")
//    fun removeFragment(
//        fragment: Fragment,
//        transaction: SelectTransaction = SelectTransaction.Commit,
//    ) = context.supportFragmentManager.beginTransaction().run {
//        remove(fragment)
//        safeCommit(transaction)
//    }
//
//    fun currentFragment(): Fragment? =
//        context.supportFragmentManager.findFragmentById(frameBase.id)
//
//    fun isContainsBackStackThisFragment(fragment: Fragment): Boolean = context.supportFragmentManager.run {
//        repeat(backStackEntryCount) { i ->
//            val entry = getBackStackEntryAt(i)
//            return entry.name == fragment::class.java.simpleName
//        }
//        return false
//    }
//}