package com.example.moto.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.moto.R
import com.example.moto.presentation.Singletons
import com.example.moto.presentation.api.OnepieceDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class OnepieceDetailFragment : Fragment() {
    private lateinit var textViewName: TextView
    private lateinit var textViewCharacters: TextView
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onepiece_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewName = view.findViewById(R.id.onepiece_detail_name)
        textViewCharacters = view.findViewById(R.id.onepiece_detail_characters)
       callApi()
      //  view.findViewById<Button>(R.id.button_second).setOnClickListener {
        //    findNavController().navigate(R.id.navigateToOnepieceListFragment)
      // }
    }

    private fun callApi() {
        val id = arguments?.getInt("onepieceId") ?: -1

        Singletons.onepieceApi.getOnepieceDetail(id).enqueue(object : Callback<OnepieceDetailResponse>{
            override fun onFailure(
                    call: Call<OnepieceDetailResponse>,
                    t: Throwable
            ) {

            }

            override fun onResponse(
                    call: Call<OnepieceDetailResponse>,
                    response: Response<OnepieceDetailResponse>
            ) {
                if(response.isSuccessful && response.body() != null){
                    textViewName.text = response.body()!!.summary
                    textViewCharacters.text = response.body()!!.characters
                }
            }
        })
    }
}