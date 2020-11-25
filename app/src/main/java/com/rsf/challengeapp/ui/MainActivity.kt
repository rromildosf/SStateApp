package com.rsf.challengeapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rsf.challengeapp.R
import com.rsf.challengeapp.model.Product
import com.rsf.challengeapp.model.Special
import com.rsf.challengeapp.repository.IProductRepository
import com.rsf.challengeapp.util.textColor
import com.rsf.challengeapp.util.word
import com.rsf.challengeapp.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_cash.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.android.inject


class MainActivity: AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getProducts()
    }

    private fun getProducts() {
        viewModel.getProductList().observe(this) {
            it.fold({ products ->
                products.find { it.type == Special }?.let { special ->
                    setSpecialProductView(special)
                }
            }, {
                Log.e(TAG, "Exception of $it")
            })
        }
    }

    private fun setSpecialProductView(special: Product) {
        Picasso.get()
            .load(special.imageUrl)
            .placeholder(R.drawable.ic_custom_background)
            .into(itemCash.imageView);

        tvCashTittle.text = special.title
        tvCashTittle.textColor(special.title.word(0), R.color.digio_color)
    }
}