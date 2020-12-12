package com.bernatangg.testmukminapp.repo

import ApiResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bernatangg.testmukminapp.api.ApiClient
import com.bernatangg.testmukminapp.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {

    private var apiService:ApiService? = null

    init {
        apiService = ApiClient.getApiClient().create(ApiService::class.java)
    }

    fun fetchAllPosts(id:Int):LiveData<List<ApiResponse>>{
        val data = MutableLiveData<List<ApiResponse>>()

        apiService?.fetchAllPosts(id)?.enqueue(object : Callback<List<ApiResponse>>{

            override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
                t.printStackTrace()
                data.value = null
                print("sampai sini 2")
            }

            override fun onResponse(call: Call<List<ApiResponse>>, response: Response<List<ApiResponse>>) {
                Log.e("respon", response.toString())
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                    print("sampai sini 3")
                }else{
                    data.value = null
                }

            }
        })

        return data

    }
}

