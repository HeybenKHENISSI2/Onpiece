package com.example.moto.presentation.list

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView
    private lateinit var imageView: ImageView
    private val adapter = OnepieceAdapter(listOf(), ::onClickedOnepiece)
    private val viewModel: OnepieceListViewModel by viewModels()

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
        imageView = view.findViewById(R.id.onepiece_logo)
        loader = view.findViewById(R.id.onepiece_loader)
        textViewError = view.findViewById(R.id.onepiece_error)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@OnepieceFragment.adapter
        }

        viewModel.onepieceList.observe(viewLifecycleOwner, Observer { onepieceModel ->
            imageView.isVisible = onepieceModel is OnepieceLoaderLogo
            loader.isVisible = onepieceModel is OnepieceLoader
            textViewError.isVisible = onepieceModel is OnepieceError
            if(onepieceModel is OnepieceSuccess) {
                adapter.updateList(onepieceModel.onepieceList)

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

