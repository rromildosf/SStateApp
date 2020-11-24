package com.rsf.challengeapp.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    fun getHelloMessage() : String {
        return "Hello World! :)"
    }
}