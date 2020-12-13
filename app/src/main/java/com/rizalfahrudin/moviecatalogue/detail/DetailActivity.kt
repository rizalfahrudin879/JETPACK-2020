
package com.rizalfahrudin.moviecatalogue.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizalfahrudin.moviecatalogue.R
import com.rizalfahrudin.moviecatalogue.core.vo.Resource
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val POSITION = "position"
        const val ID = "id"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val factory = ViewModelFactory.getInstance(this)
//        viewModel = ViewModelProvider(
//            this,
//            factory
//        )[DetailViewModel::class.java]

        val position = intent.getIntExtra(POSITION, 0)
        val id = intent.getIntExtra(ID, 0)

        detailViewModel.setTypeMovieTv(position, id)

        detailViewModel.movieTv.observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> loading_detail.visibility = View.VISIBLE
                    is Resource.Success -> {
                        loading_detail.visibility = View.GONE
                        tv_title_detail.text = it.data?.title
                        tv_description_detail.text = it.data?.description
                        Glide.with(this)
                            .load(it.data?.image)
                            .apply(RequestOptions())
                            .into(img_poster_detail)
                    }
                    is Resource.Error -> loading_detail.visibility = View.GONE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu

        detailViewModel.movieTv.observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Success -> {
                        if (it.data != null) {
                            val state = it.data.favorite
                            setFavoriteState(state)
                        }
                    }
                    else -> {
                    }
                }
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            detailViewModel.setBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state)
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_24)
        else
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_star_border_24)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}