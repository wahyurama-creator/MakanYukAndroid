package com.dev.makanyuk.ui.profile.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dev.makanyuk.ui.profile.account.AccountFragment
import com.dev.makanyuk.ui.profile.makanyuk.MakanYukFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Account"
            1 -> "MakanYuk"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        val fragment: Fragment
        when (position) {
            0 -> {
                fragment = AccountFragment()
                return fragment
            }
            1 -> {
                fragment = MakanYukFragment()
                return fragment
            }
            else -> {
                fragment = AccountFragment()
                return fragment
            }
        }
    }
}