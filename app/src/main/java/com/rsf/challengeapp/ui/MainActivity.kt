package com.rsf.challengeapp.ui

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsf.challengeapp.R
import com.rsf.challengeapp.model.Normal
import com.rsf.challengeapp.model.Product
import com.rsf.challengeapp.model.Special
import com.rsf.challengeapp.model.SpotLight
import com.rsf.challengeapp.service.model.CashProduct
import com.rsf.challengeapp.util.*
import com.rsf.challengeapp.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.android.synthetic.main.item_cash.view.*
import kotlinx.android.synthetic.main.loading.*
import kotlinx.android.synthetic.main.loading.view.*
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

        toolbar.post { // post used to wait for view layout
            setupProductViews(createSkeletonProductList())
        }

        loading.show()
        loading.message.text = getString(R.string.dialog_loading_data)
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
            setHasFixedSize(false)
            adapter = FeaturedProductListAdapter(emptyList()).apply {
                interactionListener = ::onItemSelected
            }
        }
    }

    private fun onItemSelected(product: Product) {

    }

    private fun getProducts() {
        viewModel.getProductList().observe(this) { result ->
            loading.hide()
            result.fold({ products -> setupProductViews(products) }) {
                Log.e(TAG, "Exception of $it")
            }
        }
    }

    private fun setupProductViews(products: List<Product>) {
        products.find { it.type == Special }?.let { setSpecialProductView(it) }
        setProductListView(products.filter { it.type == Normal })
        setFeaturedProductListView(products.filter { it.type == SpotLight })
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