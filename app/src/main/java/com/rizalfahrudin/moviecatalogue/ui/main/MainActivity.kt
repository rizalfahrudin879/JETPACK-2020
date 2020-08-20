package com.rizalfahrudin.moviecatalogue.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.rizalfahrudin.moviecatalogue.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.elevation = 0f

        view_pager.adapter = PagerAdapter(this)

        val title = intArrayOf(R.string.movie, R.string.tv)
        TabLayoutMediator(tabs, view_pager) { tabs, position ->
            tabs.text = resources.getString(title[position])
        }.attach()
    }
}