package com.rizalfahrudin.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val movie: List<MovieEntityResponse>
)
