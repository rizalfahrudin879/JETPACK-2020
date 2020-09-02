package com.rizalfahrudin.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("results")
    val tv: List<TvEntityResponse>
)
