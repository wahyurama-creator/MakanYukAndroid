package com.dev.makanyuk.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dev.makanyuk.R
import com.dev.makanyuk.app.MakanYuk
import com.dev.makanyuk.model.response.home.Data
import com.dev.makanyuk.model.response.home.HomeResponse
import com.dev.makanyuk.model.response.login.User
import com.dev.makanyuk.ui.detail.DetailActivity
import com.dev.makanyuk.ui.home.api.HomeContract
import com.dev.makanyuk.ui.home.api.HomePresenter
import com.dev.makanyuk.ui.home.viewpager.SectionPagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback, HomeContract.View {

    private lateinit var homePresenter: HomePresenter
    private lateinit var progressDialog: Dialog
    private var newTasteList: ArrayList<Data> = ArrayList()
    private var popularList: ArrayList<Data> = ArrayList()
    private var recommendedList: ArrayList<Data> = ArrayList()

    companion object {
        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.new_taste,
            R.string.popular,
            R.string.recommended
        )
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()

        homePresenter = HomePresenter(this)
        homePresenter.getHome()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null, false)
        progressDialog.apply {
            setContentView(dialogLayout)
            setCancelable(false)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        val user = MakanYuk.getApp().getUser()
        val userResponse = Gson().fromJson(user, User::class.java)
        if (userResponse.profile_photo_url.isNotEmpty()) {
            Glide.with(this)
                .load(userResponse.profile_photo_url)
                .apply(RequestOptions().transform(RoundedCorners(100)))
                .into(iv_profile)
        }
    }

    override fun onClick(view: View, data: Data) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(SectionPagerAdapter.EXTRA_DATA, data)
        startActivity(intent)
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {

        for (i in homeResponse.data.indices) {
            val items: List<String> = homeResponse.data[i].types?.split(",") ?: ArrayList()
            for (j in items.indices) {
                when {
                    items[j].equals("new_food", true) -> {
                        newTasteList.add(homeResponse.data[i])
                    }
                    items[j].equals("recommended", true) -> {
                        recommendedList.add(homeResponse.data[i])
                    }
                    items[j].equals("popular", true) -> {
                        popularList.add(homeResponse.data[i])
                    }
                }
            }
        }

        val adapter = HomeAdapter(homeResponse.data, this)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_food.layoutManager = layoutManager
        rv_food.adapter = adapter

        val sectionPagerAdapter = SectionPagerAdapter(this)
        view_pager.adapter = sectionPagerAdapter
        sectionPagerAdapter.setData(newTasteList, popularList, recommendedList)
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()

    }

    override fun onHomeFailed(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }
}