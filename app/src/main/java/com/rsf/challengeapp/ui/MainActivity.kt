package com.rsf.challengeapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rsf.challengeapp.R
import com.rsf.challengeapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity: AppCompatActivity() {
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sayHello()
    }

    private fun sayHello() {
        tvMessage.text = viewModel.getHelloMessage()
    }
}