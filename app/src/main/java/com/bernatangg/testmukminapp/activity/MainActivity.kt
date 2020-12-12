package com.bernatangg.testmukminapp.activity

import ApiResponse
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bernatangg.testmukminapp.R
import com.bernatangg.testmukminapp.adapter.HomeAdapter
import com.bernatangg.testmukminapp.repo.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter
import java.util.*


class MainActivity : AppCompatActivity(), HomeAdapter.HomeListener {

    private lateinit var homeVm: HomeViewModel
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeVm = ViewModelProvider(this)[HomeViewModel::class.java]

        initAdapter()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            showDate()
        }

        homeVm.fetchAllPost(23)
        homeVm.homeModelListLiveData?.observe(this, Observer {
            if (it != null) {
                rv_home.visibility = View.VISIBLE
                adapter.setData(it as ArrayList<ApiResponse>)
            } else {
                showToast("Something went wrong")
            }

            progress_home.visibility = View.GONE
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDate() {
        val currentDate: String = SimpleDateFormat("EEEE, dd MMMM", Locale.getDefault()).format(Date())
        tv_date_masehi.text = currentDate

        val hijrahDate = HijrahDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        val formatted = formatter.format(hijrahDate)
        tv_date_hijriah.text = formatted

    }

    private fun initAdapter() {
        adapter = HomeAdapter(this)
        rv_home.layoutManager = LinearLayoutManager(this)
        rv_home.adapter = adapter
    }

    override fun onItemClicked(apiResponse: ApiResponse, position: Int) {
        TODO("Not yet implemented")
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
