package com.rizalfahrudin.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieTv(
    var id: Int?,
    var title: String?,
    var description: String?,
    var image: String?,
    var type: Int?,
    var favorite: Boolean = false
) : Parcelable