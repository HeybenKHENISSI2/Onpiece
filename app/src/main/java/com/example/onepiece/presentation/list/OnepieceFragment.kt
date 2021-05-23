package com.example.onepiece.presentation.list

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
import com.example.onepiece.R


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class OnepieceFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView
    private lateinit var imgerror: ImageView
    private lateinit var imgerror2: ImageView
    private lateinit var imgLoader: ImageView
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
        loader = view.findViewById(R.id.onepiece_loader)
        textViewError = view.findViewById(R.id.onepiece_error)
        imgerror = view.findViewById(R.id.onepiece_error2)
        imgerror2 = view.findViewById(R.id.onepiece_error3)
        imgLoader = view.findViewById(R.id.onepiece_loader2)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@OnepieceFragment.adapter
        }

        viewModel.onepieceList.observe(viewLifecycleOwner, Observer { onepieceModel ->
            loader.isVisible = onepieceModel is OnepieceLoader
            imgLoader.isVisible = onepieceModel is OnepieceLoader
            textViewError.isVisible = onepieceModel is OnepieceError
            imgerror.isVisible = onepieceModel is OnepieceError
            imgerror2.isVisible = onepieceModel is OnepieceError
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
                "onepieceId" to (985-id)
        ))
    }
}

