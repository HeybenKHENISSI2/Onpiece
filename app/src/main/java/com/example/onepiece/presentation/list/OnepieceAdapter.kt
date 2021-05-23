package com.example.onepiece.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onepiece.R
import com.example.onepiece.presentation.Singletons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.onepiece.presentation.api.OnepieceDetailResponse

class OnepieceAdapter(private var dataSet: List<Onepiece>, var listener: ((Int) -> Unit)? = null) :
    RecyclerView.Adapter<OnepieceAdapter.ViewHolder>() {
    private lateinit var textViewCharacter: TextView
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView
        val imageView2: ImageView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.onepiece_title)
            imageView = view.findViewById(R.id.onepiece_img)
            imageView2 = view.findViewById(R.id.onepiece_img2)
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
        viewHolder.textView.text = onepiece.chapter
       // viewHolder.textView2.text = onepiece.chapter
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(position)
        }

        val id = 985-position

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

                    if(response.body()!!.cover_images.contains("|")){
                        val yourArray: List<String> = response.body()!!.cover_images.split("|")
                        if(yourArray[1].contains("http")){
                            Glide.with(viewHolder.itemView.context).load(yourArray[1]).into(viewHolder.imageView2)
                        }else{
                            Glide.with(viewHolder.itemView.context).load("https://onepiececover.com" + yourArray[1]).into(viewHolder.imageView2)
                        }
                    }else{
                        Glide.with(viewHolder.itemView.context).load("https://onepiececover.com" + response.body()!!.cover_images).into(viewHolder.imageView2)

                    }

                }
            }
        })



        /*val yourArray: List<String> = onepiece.cover_images.split("|")
        Glide.with(viewHolder.itemView.context).load(yourArray[1]).into(viewHolder.imageView2);
*/
        Glide
            .with(viewHolder.itemView.context)
            .load(R.drawable.wanted)
            .centerCrop()
            .into(viewHolder.imageView);
    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
