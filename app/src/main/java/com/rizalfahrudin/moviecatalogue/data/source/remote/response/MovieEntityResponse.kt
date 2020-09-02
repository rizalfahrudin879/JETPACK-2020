package com.rizalfahrudin.moviecatalogue.data.source.remote.response

data class MovieEntityResponse(
    var id: Int? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var poster_path: String? = null
)