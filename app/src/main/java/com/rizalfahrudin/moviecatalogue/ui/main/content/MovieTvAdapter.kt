package com.rizalfahrudin.moviecatalogue.ui.main.content


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizalfahrudin.moviecatalogue.R
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity
import kotlinx.android.synthetic.main.item.view.*

class MovieTvAdapter(private val listener: (MovieTvEntity) -> Unit) :
    PagedListAdapter<MovieTvEntity, MovieTvAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieTvEntity>() {
            override fun areItemsTheSame(oldItem: MovieTvEntity, newItem: MovieTvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieTvEntity,
                newItem: MovieTvEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieTvEntity = getItem(position)
        if (movieTvEntity != null) holder.bind(movieTvEntity, listener)
    }

}