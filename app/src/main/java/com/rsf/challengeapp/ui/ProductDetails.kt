package com.rsf.challengeapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.rsf.challengeapp.R
import com.rsf.challengeapp.model.Normal
import com.rsf.challengeapp.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.android.synthetic.main.fragment_product_details.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProductDetails : Fragment() {
    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().actionBar?.title = "Detalhes"
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedProduct?.run {
            if (type == Normal) {
                imgIcon.isVisible = true
                Picasso.get().load(imageUrl).into(imgIcon)
            }
            else {
                imgIcon.isVisible = false
                Picasso.get().load(imageUrl).into(imgBanner)
            }
            tvProductTittle.text = title
            tvDescription.text = description
        }


        Log.e("Details", "${viewModel.selectedProduct?.description}")
    }
}