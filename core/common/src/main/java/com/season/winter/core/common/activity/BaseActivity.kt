package com.season.winter.core.common.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding>(
    private val layoutResourceId: Int
): AppCompatActivity() {

    protected val tag = this::class.java.simpleName.toString()

    lateinit var binding: T

    protected abstract fun T.initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.initView()
    }
}