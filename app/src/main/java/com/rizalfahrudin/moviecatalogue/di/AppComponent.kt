package com.rizalfahrudin.moviecatalogue.di

import com.rizalfahrudin.moviecatalogue.MainActivity
import com.rizalfahrudin.moviecatalogue.core.di.CoreComponent
import com.rizalfahrudin.moviecatalogue.detail.DetailActivity
import com.rizalfahrudin.moviecatalogue.main.content.MovieTvFragment
import com.rizalfahrudin.moviecatalogue.main.favorite.FavoriteActivity
import dagger.Component


@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent):AppComponent
    }

    fun inject(fragment: MovieTvFragment)
    fun inject(activity: DetailActivity)
}