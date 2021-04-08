package com.dev.makanyuk.ui.home.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dev.makanyuk.model.response.home.Data
import com.dev.makanyuk.ui.home.newtaste.HomeNewTasteFragment
import com.dev.makanyuk.ui.home.popular.HomePopularFragment
import com.dev.makanyuk.ui.home.recommended.HomeRecommendedFragment

class SectionPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private var newTasteList: ArrayList<Data> = ArrayList()
    private var popularList: ArrayList<Data> = ArrayList()
    private var recommendedList: ArrayList<Data> = ArrayList()

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList(EXTRA_DATA, newTasteList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = HomePopularFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList(EXTRA_DATA, popularList)
                fragment.arguments = bundle
                return fragment
            }
            2 -> {
                fragment = HomeRecommendedFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList(EXTRA_DATA, recommendedList)
                fragment.arguments = bundle
                return fragment
            }
        }
        return fragment as Fragment
    }

    fun setData(
        newTasteList: ArrayList<Data>,
        popularList: ArrayList<Data>,
        recommendedList: ArrayList<Data>
    ) {
        this.newTasteList = newTasteList
        this.popularList = popularList
        this.recommendedList = recommendedList
    }
}