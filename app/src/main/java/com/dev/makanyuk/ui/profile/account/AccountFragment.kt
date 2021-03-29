package com.dev.makanyuk.ui.profile.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.makanyuk.R
import com.dev.makanyuk.model.dummy.ProfileMenuModel
import com.dev.makanyuk.ui.profile.ProfileMenuAdapter
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback {

    private var menu: ArrayList<ProfileMenuModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()

        val adapter = ProfileMenuAdapter(menu, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter
        rv_list.setHasFixedSize(true)
    }

    private fun initDataDummy() {
        menu = ArrayList()
        menu.add(ProfileMenuModel("Edit Profile"))
        menu.add(ProfileMenuModel("Home Address"))
        menu.add(ProfileMenuModel("Security"))
        menu.add(ProfileMenuModel("Payments"))
    }

    override fun onClick(view: View, data: ProfileMenuModel) {

    }
}