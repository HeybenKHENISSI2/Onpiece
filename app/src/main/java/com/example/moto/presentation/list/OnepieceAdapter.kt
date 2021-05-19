package com.example.moto.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moto.R

class OnepieceAdapter(private var dataSet: List<Onepiece>, var listener: ((Int) -> Unit)? = null) :
    RecyclerView.Adapter<OnepieceAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.onepiece_title)
            imageView = view.findViewById(R.id.onepiece_img)
        }
    }


    fun updateList(list: List<Onepiece>) {
        dataSet = list
        notifyDataSetChanged()
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.onepiece_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val onepiece = dataSet[position]
        viewHolder.textView.text = onepiece.title
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(position)
        }
        Glide
            .with(viewHolder.itemView.context)
            .load("https://i.pinimg.com/originals/c2/61/e7/c261e73fc24cd8b535bd9a6199bf44c7.png")
            .centerCrop()
            .into(viewHolder.imageView);
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
