package com.rizalfahrudin.moviecatalogue.ui.main.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizalfahrudin.moviecatalogue.R
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity
import kotlinx.android.synthetic.main.item.view.*

class MovieTvAdapter(private val listener: (MovieTvEntity) -> Unit) :
    RecyclerView.Adapter<MovieTvAdapter.ViewHolder>() {
    private var listMovieTv = ArrayList<MovieTvEntity>()

    fun setMovieTv(dataMovieTv: List<MovieTvEntity>?) {
        if (dataMovieTv == null) return
        listMovieTv.clear()
        listMovieTv.addAll(dataMovieTv)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dataMovieTv: MovieTvEntity, listener: (MovieTvEntity) -> Unit) {
            with(itemView) {
                tv_title.text = dataMovieTv.title
                tv_description.text = dataMovieTv.description
                Glide.with(context)
                    .load(dataMovieTv.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_error))
                    .error(R.drawable.ic_error)
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