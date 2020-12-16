package com.rizalfahrudin.moviecatalogue.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rizalfahrudin.moviecatalogue.main.content.MovieTvFragment
import com.rizalfahrudin.moviecatalogue.main.content.MovieTvFragment.Companion.POSITION_TAB

class PagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment =
            MovieTvFragment()
        fragment.arguments = Bundle().apply {
            putInt(POSITION_TAB, position)
        }
        return fragment
    }
}
