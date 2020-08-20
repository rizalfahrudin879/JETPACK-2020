package com.rizalfahrudin.moviecatalogue.ui.main.content

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizalfahrudin.moviecatalogue.R
import com.rizalfahrudin.moviecatalogue.ui.detail.DetailActivity
import com.rizalfahrudin.moviecatalogue.ui.detail.DetailActivity.Companion.ID
import com.rizalfahrudin.moviecatalogue.ui.detail.DetailActivity.Companion.POSITION
import kotlinx.android.synthetic.main.fragment_movie_tv.*

class MovieTvFragment : Fragment() {

    companion object {
        const val POSITION_TAB = "position"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieTvViewModel::class.java]
            val position = arguments!!.getInt(POSITION_TAB)
            val listMovieTv = if (position == 0) viewModel.getDataMovie() else viewModel.getDataTv()

            val movieTvAdapter = MovieTvAdapter {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(POSITION, position)
                intent.putExtra(ID, it.id)

                startActivity(intent)
            }
            movieTvAdapter.setMovieTv(listMovieTv)
            movieTvAdapter.notifyDataSetChanged()

            with(rv_movie_tv){
                layoutManager = LinearLayoutManager(context)
                adapter = movieTvAdapter
            }
        }
    }
}