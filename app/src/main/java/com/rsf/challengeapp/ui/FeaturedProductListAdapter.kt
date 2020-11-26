package com.rsf.challengeapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsf.challengeapp.R
import com.rsf.challengeapp.model.Product
import com.rsf.challengeapp.model.SpotLight
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cash.view.*
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.coroutines.*
import java.lang.Exception

class ProductListAdapter(var products: List<Product>) :
    RecyclerView.Adapter<ProductListAdapter.ProductHolder>() {

    companion object {
        const val TYPE_FEATURED = 1
        const val TYPE_PRODUCT = 2
    }

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    var interactionListener: (currency: Product) -> Unit = {}

    class ProductHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layout = if (viewType == TYPE_FEATURED) R.layout.item_cash else R.layout.item_product

        return ProductHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(layout, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return if (products[position].type == SpotLight) TYPE_FEATURED else TYPE_PRODUCT
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        with(holder.itemView) {
            val product = products[position]
            setOnClickListener { interactionListener.invoke(product) }

            imageIcon.contentDescription = product.title
            tvHelper.text = product.title

            loadImage(product, this)
        }
    }

    private fun loadImage(product: Product, rootView: View) {
        scope.launch {
            try {
                val image = Picasso.get()
                    .load(product.imageUrl)
                    .get()

                withContext(Dispatchers.Main) {
                    if (image != null) {
                        rootView.tvHelper.visibility = View.GONE
                        rootView.imageIcon.setImageBitmap(image)
                    }
                }
            } catch (ex: Exception) {
                rootView.tvHelper.visibility = View.VISIBLE
            }
        }
    }
}