package com.rsf.challengeapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rsf.challengeapp.R
import com.rsf.challengeapp.datasource.IProductDataSource
import com.rsf.challengeapp.repository.IProductRepository
import com.rsf.challengeapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity: AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }
    private val viewModel: MainViewModel by inject()
    private val dataSource: IProductRepository by inject()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sayHello()

        getProducts()
    }

    private fun getProducts() {
        viewModel.getProductList().observe(this) {
            it.fold({
                it.forEach {
                    Log.d(TAG, "$it")
                }
            }, {
                Log.e(TAG, "Exception of $it")
            })
        }
    }

    private fun sayHello() {
        tvMessage.text = viewModel.getHelloMessage()
    }
}