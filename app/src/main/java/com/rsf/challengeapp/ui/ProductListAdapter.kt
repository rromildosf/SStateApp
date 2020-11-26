package com.rsf.challengeapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsf.challengeapp.R
import com.rsf.challengeapp.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.coroutines.*
import java.lang.Exception

class ProductListAdapter(var products: List<Product>) :
    RecyclerView.Adapter<ProductListAdapter.ProductHolder>() {

    companion object {
        private const val ITEM_SIZE_PERCENTAGE = 35.0
    }

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    var interactionListener: (currency: Product) -> Unit = {}

    class ProductHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        val layoutParams = itemView.layoutParams
        layoutParams.width = (parent.width * ITEM_SIZE_PERCENTAGE/100).toInt()
        layoutParams.height = (parent.width * ITEM_SIZE_PERCENTAGE/100).toInt()
        itemView.layoutParams = layoutParams

        return ProductHolder(itemView)
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