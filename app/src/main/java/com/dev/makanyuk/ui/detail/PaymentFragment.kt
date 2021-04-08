package com.dev.makanyuk.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dev.makanyuk.R
import com.dev.makanyuk.app.MakanYuk
import com.dev.makanyuk.model.response.home.Data
import com.dev.makanyuk.model.response.login.User
import com.dev.makanyuk.ui.home.HomeFragment.Companion.EXTRA_DATA
import com.dev.makanyuk.utils.Helpers.formatPrice
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment : Fragment(), View.OnClickListener {

    private var total: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DetailActivity).toolbarPayment()

        val data = arguments?.getParcelable<Data>(EXTRA_DATA)
        initView(data)

        btn_checkout_now.setOnClickListener(this)
    }

    private fun initView(data: Data?) {
        val totalTax = data?.price?.times(10.div(100))
        val totalDriver = data?.price?.times(8.div(100))
        total = data?.price!!.plus((totalDriver!! + totalTax!!))

        Glide.with(requireContext())
            .load(data.picturePath)
            .apply(RequestOptions().transform(RoundedCorners(30)))
            .into(iv_picture)
        tv_title.text = data.name
        tv_price.formatPrice(data.price.toString())
        tv_count.text = "1 Items"
        tv_item_title.text = data.name
        tv_item_price.formatPrice(data.price.toString())
        tv_item_driver_price.formatPrice(totalDriver.toString())
        tv_item_tax_price.formatPrice(totalTax.toString())
        tv_item_total_price.formatPrice(total.toString())

        val user = MakanYuk.getApp().getUser()
        val userResponse = Gson().fromJson(user, User::class.java)

        tv_item_name.text = userResponse.name
        tv_item_phone_no.text = userResponse.phoneNumber
        tv_item_address.text = userResponse.address
        tv_item_house_no.text = userResponse.houseNumber
        tv_item_city.text = userResponse.city
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_checkout_now -> {
                Navigation.findNavController(v)
                    .navigate(R.id.action_paymentFragment_to_paymentSuccessFragment)
            }
        }
    }
}