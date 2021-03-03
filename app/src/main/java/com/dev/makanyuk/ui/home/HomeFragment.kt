package com.dev.makanyuk.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.makanyuk.R
import com.dev.makanyuk.model.dummy.HomeModel
import com.dev.makanyuk.ui.home.viewpager.SectionPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback {

    private var foodList: ArrayList<HomeModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()
        val adapter = HomeAdapter(foodList, this)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_food.layoutManager = layoutManager
        rv_food.adapter = adapter

        val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

    private fun initDataDummy() {
        foodList = ArrayList()
        foodList.add(HomeModel("Nasi Goreng", "", 5f))
        foodList.add(HomeModel("Nasi Goreng", "", 4f))
        foodList.add(HomeModel("Nasi Goreng", "", 3f))
        foodList.add(HomeModel("Nasi Goreng", "", 2f))

    }

    override fun onClick(view: View, data: HomeModel) {

    }
}