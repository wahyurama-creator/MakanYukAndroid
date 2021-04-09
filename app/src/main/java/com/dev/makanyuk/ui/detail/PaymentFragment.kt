package com.dev.makanyuk.ui.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dev.makanyuk.R
import com.dev.makanyuk.app.MakanYuk
import com.dev.makanyuk.model.response.checkout.CheckoutResponse
import com.dev.makanyuk.model.response.home.Data
import com.dev.makanyuk.model.response.login.User
import com.dev.makanyuk.ui.detail.api.PaymentContract
import com.dev.makanyuk.ui.detail.api.PaymentPresenter
import com.dev.makanyuk.ui.home.HomeFragment.Companion.EXTRA_DATA
import com.dev.makanyuk.utils.Helpers.formatPrice
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment : Fragment(), PaymentContract.View {

    private var total: Int = 0
    private lateinit var progressDialog: Dialog
    private lateinit var paymentPresenter: PaymentPresenter

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
        initView()
        paymentPresenter = PaymentPresenter(this)
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialog = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog.apply {
            setContentView(dialog)
            setCancelable(false)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initView(data: Data?) {
        val totalTax = data?.price?.times(0.1)?.toInt()
        val totalDriver = 10000
        total = data?.price!!.plus((totalDriver + totalTax!!))

        Glide.with(this)
            .load(data.picturePath)
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

        btn_checkout_now.setOnClickListener {
            paymentPresenter.getCheckout(
                data.id.toString(),
                userResponse.id.toString(),
                "1",
                total.toString(),
                it
            )
        }
    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(checkoutResponse.paymentUrl)
        startActivity(intent)
        Navigation.findNavController(view)
            .navigate(R.id.action_paymentFragment_to_paymentSuccessFragment)
    }

    override fun onCheckoutFailed(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }
}