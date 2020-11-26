package com.rsf.challengeapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.rsf.challengeapp.R
import kotlinx.android.synthetic.main.app_toolbar.*

class MainActivity: AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private var username = "Maria"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar)
        toolbar.title = getString(R.string.greetings, username)
    }
}