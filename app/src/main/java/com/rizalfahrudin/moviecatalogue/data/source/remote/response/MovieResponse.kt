package com.rizalfahrudin.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val movie: List<MovieEntityResponse>
)
