package com.example.moto.presentation.list

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moto.R
import com.example.moto.presentation.Singletons
import com.example.moto.presentation.api.OnepieceListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class OnepieceFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = OnepieceAdapter(listOf(), ::onClickedOnepiece)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onepiece_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.onepiece_recyclerview)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@OnepieceFragment.adapter
        }

        Singletons.onepieceApi.getOnepieceList().enqueue(object : Callback<OnepieceListResponse> {
            override fun onResponse(call: Call<OnepieceListResponse>, response: Response<OnepieceListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val onepieceResponse = response.body()
                    if (onepieceResponse != null) {
                        showList(onepieceResponse.items)
                    }
                }
            }

            override fun onFailure(call: Call<OnepieceListResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })




    }

    private fun showList(onepieceList: List<Onepiece>) {
        adapter.updateList(onepieceList)
    }

    private fun onClickedOnepiece(id : Int) {
        findNavController().navigate(R.id.navigateToOnepieceDetailFragment, bundleOf(
                "onepieceId" to (id + 1)
        ))
    }
}

