package com.bernatangg.testmukminapp.repo

import ApiResponse
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class HomeViewModel (application: Application): AndroidViewModel(application) {

    private var homeRepository: HomeRepository? = null
    var homeModelListLiveData : LiveData<List<ApiResponse>>? = null

    init {
        homeRepository = HomeRepository()
        homeModelListLiveData = MutableLiveData()
    }

    fun fetchAllPost(id:Int) {
        print("sampai sini 1")
        homeModelListLiveData = homeRepository?.fetchAllPosts(id)
    }

}