package com.example.moto.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moto.presentation.Singletons
import com.example.moto.presentation.api.OnepieceListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OnepieceListViewModel : ViewModel(){
    val onepieceList : MutableLiveData<List<Onepiece>> = MutableLiveData()
    init {
callApi()
    }

    private fun callApi() {
        Singletons.onepieceApi.getOnepieceList().enqueue(object : Callback<OnepieceListResponse> {
            override fun onResponse(call: Call<OnepieceListResponse>, response: Response<OnepieceListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val onepieceResponse = response.body()
                    if (onepieceResponse != null) {
                        onepieceList.value= onepieceResponse.items

                    }
                }
            }

            override fun onFailure(call: Call<OnepieceListResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}