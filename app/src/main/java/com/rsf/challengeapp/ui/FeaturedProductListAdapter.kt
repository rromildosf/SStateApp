package com.rsf.challengeapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsf.challengeapp.R
import com.rsf.challengeapp.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cash.view.*


class FeaturedProductListAdapter(var products: List<Product>) :
    RecyclerView.Adapter<FeaturedProductListAdapter.ProductHolder>() {

    var interactionListener: (currency: Product) -> Unit = {}

    class ProductHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.item_cash, parent, false)
        setMargins(itemView, parent)

        return ProductHolder(itemView)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        Log.e("Bingind", "${products[position]}")
        with(holder.itemView) {
            val product = products[position]
            setOnClickListener { interactionListener.invoke(product) }
            cashBanner.contentDescription = product.title

            loadImage(product, this)
        }
    }

    private fun setMargins(itemView: View, parent: ViewGroup) {
        val layoutParams = itemView.layoutParams
        layoutParams.width = (parent.width * 0.8).toInt()

        val margins = itemView.context.resources.getDimensionPixelSize(R.dimen.product_item_margin)
        val margin = itemView.context.resources.getDimensionPixelSize(R.dimen.product_item_margin_large)
        with(layoutParams as ViewGroup.MarginLayoutParams) {
            marginEnd = margin
            topMargin = margins
            bottomMargin = margins
        }
        itemView.layoutParams = layoutParams
    }

    private fun loadImage(product: Product, rootView: View) {
        Picasso.get()
            .load(product.imageUrl)
            .placeholder(R.drawable.ic_default_banner)
            .into(rootView.cashBanner)
    }
}