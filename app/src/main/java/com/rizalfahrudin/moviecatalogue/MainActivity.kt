package com.rizalfahrudin.moviecatalogue

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.rizalfahrudin.moviecatalogue.main.PagerAdapter
import com.rizalfahrudin.moviecatalogue.main.content.MovieTvFragment.Companion.MAIN
import com.rizalfahrudin.moviecatalogue.main.favorite.FavoriteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.elevation = 0f

        view_pager.adapter =
            PagerAdapter(this, MAIN)

        val title = intArrayOf(R.string.movie, R.string.tv)
        TabLayoutMediator(tabs, view_pager) { tabs, position ->
            tabs.text = resources.getString(title[position])
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}