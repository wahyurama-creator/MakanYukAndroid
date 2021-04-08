package com.dev.makanyuk.ui.detail

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.dev.makanyuk.R
import com.dev.makanyuk.ui.home.viewpager.SectionPagerAdapter.Companion.EXTRA_DATA
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.extras.let {
            val navController =
                Navigation.findNavController(findViewById(R.id.detail_host_fragment))
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_DATA, it?.get(EXTRA_DATA) as Parcelable)
            navController.setGraph(navController.graph, bundle)
        }
    }

    fun toolbarPayment() {
        toolbar.visibility = View.VISIBLE
        toolbar.title = "Payment"
        toolbar.subtitle = "You deserve better meal"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarDetail() {
        toolbar.visibility = View.GONE
    }
}