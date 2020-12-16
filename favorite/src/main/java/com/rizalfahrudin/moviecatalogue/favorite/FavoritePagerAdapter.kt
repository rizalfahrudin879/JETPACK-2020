package com.rizalfahrudin.moviecatalogue.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rizalfahrudin.moviecatalogue.favorite.content.FavoriteFragment
import com.rizalfahrudin.moviecatalogue.main.content.MovieTvFragment.Companion.POSITION_TAB

class FavoritePagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment =
            FavoriteFragment()
        fragment.arguments = Bundle().apply {
            putInt(POSITION_TAB, position)
        }
        return fragment
    }
}
