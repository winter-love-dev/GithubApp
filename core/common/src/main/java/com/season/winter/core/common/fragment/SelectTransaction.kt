package com.season.winter.core.common.fragment

import android.util.Log
import androidx.fragment.app.FragmentTransaction
import java.lang.Exception

enum class SelectTransaction{
    Commit,
    CommitAllowingStateLoss,
    CommitNow,
    CommitNowAllowingStateLoss,
    ;
}

fun FragmentTransaction.safeCommit(
    transaction: SelectTransaction = SelectTransaction.Commit
) {

    try {
        when(transaction) {
            SelectTransaction.Commit ->
                commit()
            SelectTransaction.CommitAllowingStateLoss ->
                commitAllowingStateLoss()
            SelectTransaction.CommitNow ->
                commitNow()
            SelectTransaction.CommitNowAllowingStateLoss ->
                commitNowAllowingStateLoss()
        }
    } catch (e: Exception) {
        Log.e("Transaction", "commit error: e: $e", )
    }
}
