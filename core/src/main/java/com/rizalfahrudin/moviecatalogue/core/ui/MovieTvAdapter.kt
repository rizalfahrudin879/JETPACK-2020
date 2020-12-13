package com.rizalfahrudin.moviecatalogue.core.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizalfahrudin.moviecatalogue.core.R
import com.rizalfahrudin.moviecatalogue.core.domain.model.MovieTv
import kotlinx.android.synthetic.main.item.view.*

class MovieTvAdapter(private val listener: (MovieTv) -> Unit) :
    RecyclerView.Adapter<MovieTvAdapter.ViewHolder>() {
    private var listMovieTv = ArrayList<MovieTv>()

    fun setMovieTv(dataMovieTv: List<MovieTv>?) {
        if (dataMovieTv == null) return
        listMovieTv.clear()
        listMovieTv.addAll(dataMovieTv)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataMovieTv: MovieTv, listener: (MovieTv) -> Unit) {
            with(itemView) {
                tv_title.text = dataMovieTv.title
                tv_description.text = dataMovieTv.description
                Glide.with(context)
                    .load(dataMovieTv.image)
                    .apply(RequestOptions())
                    .into(img_poster)
                setOnClickListener { listener(dataMovieTv) }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMovieTv.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataMovieTv = listMovieTv[position]
        holder.bind(dataMovieTv, listener)
    }

}