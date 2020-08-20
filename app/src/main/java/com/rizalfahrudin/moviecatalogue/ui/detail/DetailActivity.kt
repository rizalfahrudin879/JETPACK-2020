
package com.rizalfahrudin.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizalfahrudin.moviecatalogue.R
import com.rizalfahrudin.moviecatalogue.data.MovieTvEntity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val POSITION = "position"
        const val ID = "id"
    }

    private lateinit var movieTvEntity: MovieTvEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]
        val position = intent.getIntExtra(POSITION, 0)
        val id = intent.getIntExtra(ID, 0)

        viewModel.setIdData(id)
        movieTvEntity = if (position == 0) viewModel.getDataMovie() else viewModel.getDataTv()

        tv_title_detail.text = movieTvEntity.title
        tv_description_detail.text = movieTvEntity.description
        Glide.with(this)
            .load(movieTvEntity.image)
            .apply(RequestOptions())
            .into(img_poster_detail)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}