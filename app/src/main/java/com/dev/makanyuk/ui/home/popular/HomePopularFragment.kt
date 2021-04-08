package com.dev.makanyuk.ui.home.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.makanyuk.R
import com.dev.makanyuk.model.response.home.Data
import com.dev.makanyuk.ui.detail.DetailActivity
import com.dev.makanyuk.ui.home.newtaste.HomeNewTasteAdapter
import com.dev.makanyuk.ui.home.viewpager.SectionPagerAdapter.Companion.EXTRA_DATA
import kotlinx.android.synthetic.main.fragment_home_new_taste.*

class HomePopularFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback {

    private var foodList: ArrayList<Data> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        foodList = arguments?.getParcelableArrayList(EXTRA_DATA)!!

        val adapter = HomeNewTasteAdapter(foodList, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter
    }


    override fun onClick(view: View, data: Data) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(EXTRA_DATA, data)
        startActivity(intent)
    }
}