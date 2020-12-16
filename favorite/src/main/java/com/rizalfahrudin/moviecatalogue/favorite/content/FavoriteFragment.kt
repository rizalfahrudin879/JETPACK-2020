package com.rizalfahrudin.moviecatalogue.favorite.content

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizalfahrudin.moviecatalogue.core.ui.MovieTvAdapter
import com.rizalfahrudin.moviecatalogue.detail.DetailActivity
import com.rizalfahrudin.moviecatalogue.detail.DetailActivity.Companion.ID
import com.rizalfahrudin.moviecatalogue.detail.DetailActivity.Companion.POSITION
import com.rizalfahrudin.moviecatalogue.favorite.R
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {
    companion object {
        const val POSITION_TAB = "position"
    }

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var movieTvAdapter: MovieTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val position = arguments?.getInt(POSITION_TAB)

            movieTvAdapter =
                MovieTvAdapter {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(POSITION, position)
                    intent.putExtra(ID, it.id)
                    startActivity(intent)
                }

            favoriteViewModel.setTypeMovieTv(position)

            favoriteViewModel.getDataMovieTvFavorite().observe(this, { movieTv ->
                if (movieTv != null) {
                    loading_main.visibility = View.GONE
                    movieTvAdapter.setMovieTv(movieTv)
                    movieTvAdapter.notifyDataSetChanged()
                }
            })
            with(rv_movie_tv) {
                layoutManager = LinearLayoutManager(context)
                adapter = movieTvAdapter
            }
        }
    }
}