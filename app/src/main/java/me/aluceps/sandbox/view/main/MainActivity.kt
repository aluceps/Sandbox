package me.aluceps.sandbox.view.main

import android.databinding.DataBindingUtil
import android.os.Bundle

import me.aluceps.sandbox.R
import me.aluceps.sandbox.databinding.ActivityMainBinding
import me.aluceps.sandbox.view.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent().inject(this)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        replaceFragment(MainFragment.newInstance(), binding.container.id)
    }
}
