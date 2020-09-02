
package com.rizalfahrudin.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizalfahrudin.moviecatalogue.R
import com.rizalfahrudin.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val POSITION = "position"
        const val ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.title = "Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailViewModel::class.java]

        val position = intent.getIntExtra(POSITION, 0)
        val id = intent.getIntExtra(ID, 0)
        viewModel.setTypeMovieTv(position, id)

        loading_detail.visibility = View.VISIBLE
        viewModel.getDataMovie().observe(this, Observer {
            if (it != null) {
                loading_detail.visibility = View.GONE
                tv_title_detail.text = it.title
                tv_description_detail.text = it.description
                Glide.with(this)
                    .load(it.image)
                    .apply(RequestOptions())
                    .into(img_poster_detail)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}