package com.example.onepiece.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onepiece.presentation.Singletons
import com.example.onepiece.presentation.api.OnepieceListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OnepieceListViewModel : ViewModel(){
    val onepieceList : MutableLiveData<OnepieceModel> = MutableLiveData()
    init {
callApi()
    }

    private fun callApi() {
        onepieceList.value = OnepieceLoader
        Singletons.onepieceApi.getOnepieceList().enqueue(object : Callback<OnepieceListResponse> {
            override fun onResponse(call: Call<OnepieceListResponse>, response: Response<OnepieceListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val onepieceResponse = response.body()!!
                   // if (onepieceResponse != null) {
                        onepieceList.value= OnepieceSuccess(onepieceResponse.items)
                   // }
                    if (onepieceResponse == null){
                        onepieceList.value= OnepieceError
                    }
                }
            }

            override fun onFailure(call: Call<OnepieceListResponse>, t: Throwable) {

                onepieceList.value= OnepieceError

            }

        })
    }
}