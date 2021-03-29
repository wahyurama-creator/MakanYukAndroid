package com.dev.makanyuk.ui.home.newtaste

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.makanyuk.R
import com.dev.makanyuk.model.dummy.HomeVerticalModel
import com.dev.makanyuk.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home_new_taste.*

class HomeNewTasteFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback {

    private var foodList: ArrayList<HomeVerticalModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()
        val adapter = HomeNewTasteAdapter(foodList, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter
    }

    private fun initDataDummy() {
        foodList = ArrayList()
        foodList.add(HomeVerticalModel("Nasi Goreng", "20000", "", 4f))
        foodList.add(HomeVerticalModel("Nasi Goreng", "15000", "", 5f))
        foodList.add(HomeVerticalModel("Nasi Goreng", "30000", "", 3f))
        foodList.add(HomeVerticalModel("Nasi Goreng", "10000", "", 4f))
    }

    override fun onClick(view: View, data: HomeVerticalModel) {
        startActivity(Intent(activity, DetailActivity::class.java))
    }
}