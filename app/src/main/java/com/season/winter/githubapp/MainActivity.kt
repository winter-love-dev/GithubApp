package com.season.winter.githubapp

import com.season.winter.core.common.activity.BaseActivity
import com.season.winter.githubapp.databinding.ActivityMainBinding

class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun ActivityMainBinding.initView() {
        greeting = "hello~"
    }
}