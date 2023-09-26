package com.season.winter.core.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding>(private val layoutResourceId: Int): Fragment() {

    private var viewDataBinding: T? = null
    val binding
        get() = viewDataBinding!!

    protected abstract fun T.initViewCreated()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        DataBindingUtil.inflate<T>(inflater, layoutResourceId, container,false).apply {
            viewDataBinding = this
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewDataBinding = null
    }
}