package com.rsf.challengeapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsf.challengeapp.R
import com.rsf.challengeapp.model.Normal
import com.rsf.challengeapp.model.Product
import com.rsf.challengeapp.model.Special
import com.rsf.challengeapp.model.SpotLight
import com.rsf.challengeapp.util.*
import com.rsf.challengeapp.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_cash.view.*
import kotlinx.android.synthetic.main.loading.*
import kotlinx.android.synthetic.main.loading.view.*
import kotlinx.android.synthetic.main.product_list.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MainFragment : Fragment() {
    companion object {
        private const val TAG = "MainFragment"
    }
    private lateinit var rootView: View
    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_main, container, false)

        rootView.post {
            setupProductListRecyclerView()
            setupFeaturedProductListRecyclerView()
            setupProductViews(viewModel.products ?: createSkeletonProductList())

            loading.show()
            loading.message.text = getString(R.string.dialog_loading_data)
            getProducts()
        }

        return rootView
    }


    private fun setupProductListRecyclerView() {
        with(productListContainer.rvProductList) {
            layoutManager = LinearLayoutManager(requireContext().applicationContext).apply {
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
            layoutManager = LinearLayoutManager(requireContext().applicationContext).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(false)
            adapter = FeaturedProductListAdapter(emptyList()).apply {
                interactionListener = ::onItemSelected
            }
        }
    }

    private fun onItemSelected(product: Product) {
        viewModel.selectedProduct = product
        findNavController().navigate(R.id.action_mainFragment_to_productDetails)
    }

    private fun getProducts() {
        viewModel.getProductList().observe(viewLifecycleOwner) { result ->
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
        itemCash.setOnClickListener { onItemSelected(special) }
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