package com.dev.makanyuk.ui.home.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dev.makanyuk.ui.home.newtaste.HomeNewTasteFragment
import com.dev.makanyuk.ui.home.popular.HomePopularFragment
import com.dev.makanyuk.ui.home.recommended.HomeRecommendedFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "New Taste"
            1 -> "Popular"
            2 -> "Recommended"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        val fragment: Fragment
        when (position) {
            0 -> {
                fragment = HomeNewTasteFragment()
                return fragment
            }
            1 -> {
                fragment = HomePopularFragment()
                return fragment
            }
            2 -> {
                fragment = HomeRecommendedFragment()
                return fragment
            }
            else -> {
                fragment = HomeNewTasteFragment()
                return fragment
            }
        }
    }
}