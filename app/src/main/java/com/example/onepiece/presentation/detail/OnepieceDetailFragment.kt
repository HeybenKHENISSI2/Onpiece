package com.example.onepiece.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.onepiece.R
import com.example.onepiece.presentation.Singletons
import com.example.onepiece.presentation.api.OnepieceDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class OnepieceDetailFragment : Fragment() {
    private lateinit var textViewSummary: TextView
    private lateinit var textViewLink: TextView
    private lateinit var textViewCharacter: TextView
    private lateinit var imageView: ImageView


    /*class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView
        init {
            imageView = view.findViewById(R.id.onepiece_img)
        }
    }*/

    override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onepiece_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewSummary = view.findViewById(R.id.onepiece_detail_summary)
        textViewLink = view.findViewById(R.id.onepiece_detail_link)
        textViewCharacter = view.findViewById(R.id.onepiece_detail_characters)
        imageView = view.findViewById(R.id.onepiece_detail_img)
       // textViewlien = view.findViewById(R.id.onepiece_detail_characters)
       callApi()
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
                    textViewCharacter.text = response.body()!!.title
                   // textViewSummary.text = response.body()!!.summary
                    if(response.body()!!.cover_images.contains("|")){
                        val yourArray: List<String> = response.body()!!.cover_images.split("|")
                        if(yourArray[1].contains("http")){
                            Glide.with(this@OnepieceDetailFragment).load(yourArray[1]).into(imageView)
                        }else{
                            Glide.with(this@OnepieceDetailFragment).load("https://onepiececover.com" + yourArray[1]).into(imageView)
                        }
                    }else{
                        Glide.with(this@OnepieceDetailFragment).load("https://onepiececover.com" + response.body()!!.cover_images).into(imageView)

                    }



                    //val yourArray: List<String> = response.body()!!.cover_images.split("|")
                    //textViewCharacter.text = yourArray[1]

                    textViewSummary.text = response.body()!!.summary
                }
            }
        })
    }


}