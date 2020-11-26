package com.rsf.challengeapp.ui

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsf.challengeapp.R
import com.rsf.challengeapp.model.Normal
import com.rsf.challengeapp.model.Product
import com.rsf.challengeapp.model.Special
import com.rsf.challengeapp.model.SpotLight
import com.rsf.challengeapp.util.textColor
import com.rsf.challengeapp.util.word
import com.rsf.challengeapp.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.android.synthetic.main.item_cash.view.*
import kotlinx.android.synthetic.main.product_list.view.*
import org.koin.android.ext.android.inject


class MainActivity: AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private var username = "Maria"
    }
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar)
        toolbar.title = getString(R.string.greetings, username)

        setupProductListRecyclerView()
        setupFeaturedProductListRecyclerView()

        getProducts()
    }

    private fun setupProductListRecyclerView() {
        with(productListContainer.rvProductList) {
            layoutManager = LinearLayoutManager(applicationContext).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            hasFixedSize()
            adapter = ProductListAdapter(emptyList()).apply {
                interactionListener = ::onItemSelected
            }
        }
    }

    private fun setupFeaturedProductListRecyclerView() {
        with(featuredProductsContainer.rvProductList) {
            layoutManager = LinearLayoutManager(applicationContext).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            hasFixedSize()
            adapter = FeaturedProductListAdapter(emptyList()).apply {
                interactionListener = ::onItemSelected
            }
        }
    }

    private fun onItemSelected(product: Product) {

    }

    private fun getProducts() {
        viewModel.getProductList().observe(this) { result ->
            result.fold({ products ->
                products.find { it.type == Special }?.let { setSpecialProductView(it) }
                setProductListView(products.filter { it.type == Normal })
                setFeaturedProductListView(products.filter { it.type == SpotLight })
            }, {
                Log.e(TAG, "Exception of $it")
            })
        }
    }

    private fun setSpecialProductView(special: Product) {
        Picasso.get()
            .load(special.imageUrl)
            .placeholder(R.drawable.ic_custom_background)
            .into(itemCash.cashBanner);

        tvCashTitle.text = special.title
        tvCashTitle.textColor(special.title.word(0), R.color.titlesAccent)
    }

    private fun setProductListView(products: List<Product>) {
        with(productListContainer.rvProductList) {
            (adapter as ProductListAdapter).products = products
            adapter?.notifyDataSetChanged()
        }
    }

    private fun setFeaturedProductListView(products: List<Product>) {
        with(featuredProductsContainer.rvProductList) {
            (adapter as FeaturedProductListAdapter).products = products
            adapter?.notifyDataSetChanged()
        }
    }
}