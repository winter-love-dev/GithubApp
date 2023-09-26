package com.season.winter.core.common.util.fragment

import android.util.Log
import androidx.fragment.app.FragmentTransaction
import java.lang.Exception

enum class SelectFragmentTransaction{
    Commit,
    CommitAllowingStateLoss,
    CommitNow,
    CommitNowAllowingStateLoss,
    ;
}

fun FragmentTransaction.safeCommit(
    transaction: SelectFragmentTransaction = SelectFragmentTransaction.Commit
) {

    try {
        when(transaction) {
            SelectFragmentTransaction.Commit ->
                commit()
            SelectFragmentTransaction.CommitAllowingStateLoss ->
                commitAllowingStateLoss()
            SelectFragmentTransaction.CommitNow ->
                commitNow()
            SelectFragmentTransaction.CommitNowAllowingStateLoss ->
                commitNowAllowingStateLoss()
        }
    } catch (e: Exception) {
        Log.e("Transaction", "commit error: e: $e", )
    }
}
