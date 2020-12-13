package com.rizalfahrudin.moviecatalogue.main.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.rizalfahrudin.moviecatalogue.R
import com.rizalfahrudin.moviecatalogue.main.PagerAdapter
import com.rizalfahrudin.moviecatalogue.main.content.MovieTvFragment.Companion.FAVORITE
import kotlinx.android.synthetic.main.activity_main.*

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        view_pager.adapter =
            PagerAdapter(this, FAVORITE)

        val title = intArrayOf(R.string.movie, R.string.tv)
        TabLayoutMediator(tabs, view_pager) { tabs, position ->
            tabs.text = resources.getString(title[position])
        }.attach()

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        view_pager.adapter =
            PagerAdapter(this, FAVORITE)
    }
}