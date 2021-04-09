package com.dev.makanyuk.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dev.makanyuk.R
import com.dev.makanyuk.model.response.home.Data
import com.dev.makanyuk.ui.home.HomeFragment.Companion.EXTRA_DATA
import com.dev.makanyuk.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(), View.OnClickListener {

    private var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).extraData?.let { it1 ->
                initView(it1)
            }
        }

        btn_order_now.setOnClickListener(this)
    }

    private fun initView(data: Data) {
        data.apply {
            bundle = bundleOf(EXTRA_DATA to data)
            Glide.with(requireView())
                .load(this.picturePath)
                .into(iv_picture)
            tv_title.text = this.name
            tv_description.text = this.description
            tv_ingredients.text = this.ingredients
            rating_bar_food.rating = this.rate?.toFloat() ?: 0f
            tv_total.formatPrice(this.price.toString())
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_order_now -> {
                Navigation.findNavController(v)
                    .navigate(R.id.action_detailFragment_to_paymentFragment, bundle)
            }
        }
    }
}