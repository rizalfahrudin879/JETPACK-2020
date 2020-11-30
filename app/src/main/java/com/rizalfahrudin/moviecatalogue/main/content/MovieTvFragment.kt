package com.rizalfahrudin.moviecatalogue.main.content

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizalfahrudin.moviecatalogue.MyApplication
import com.rizalfahrudin.moviecatalogue.R
import com.rizalfahrudin.moviecatalogue.core.ui.MovieTvAdapter
import com.rizalfahrudin.moviecatalogue.core.ui.ViewModelFactory
import com.rizalfahrudin.moviecatalogue.core.vo.Resource
import com.rizalfahrudin.moviecatalogue.detail.DetailActivity
import com.rizalfahrudin.moviecatalogue.detail.DetailActivity.Companion.ID
import com.rizalfahrudin.moviecatalogue.detail.DetailActivity.Companion.POSITION
import kotlinx.android.synthetic.main.fragment_movie_tv.*
import javax.inject.Inject

class MovieTvFragment : Fragment() {
    companion object {
        const val POSITION_TAB = "position"
        const val PAGE = "page"
        const val MAIN = 100
        const val FAVORITE = 200
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MovieTvViewModel by viewModels {
        factory
    }
    private lateinit var movieTvAdapter: MovieTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_tv, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val position = arguments?.getInt(POSITION_TAB)
            val pageCode = arguments?.getInt(PAGE)

            movieTvAdapter =
                MovieTvAdapter {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(POSITION, position)
                    intent.putExtra(ID, it.id)
                    startActivity(intent)
                }

            viewModel.setTypeMovieTv(position)

            if (pageCode == MAIN) {
                viewModel.getDataMovieTv().observe(viewLifecycleOwner, { movieTv ->
                    if (movieTv != null) {
                        when (movieTv) {
                            is Resource.Loading -> loading_main.visibility = View.VISIBLE
                            is Resource.Success -> {
                                loading_main.visibility = View.GONE
                                movieTvAdapter.setMovieTv(movieTv.data)
                                movieTvAdapter.notifyDataSetChanged()
                            }
                            is Resource.Error -> {
                                loading_main.visibility = View.GONE
                            }
                        }
                    }
                })
            } else {
                viewModel.getDataMovieTvFavorite().observe(viewLifecycleOwner, { movieTv ->
                    if (movieTv != null) {
                        loading_main.visibility = View.GONE
                        movieTvAdapter.setMovieTv(movieTv)
                        movieTvAdapter.notifyDataSetChanged()
                    }
                })
            }

            with(rv_movie_tv) {
                layoutManager = LinearLayoutManager(context)
                adapter = movieTvAdapter
            }
        }
    }
}