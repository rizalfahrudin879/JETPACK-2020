package com.rizalfahrudin.moviecatalogue.data.source.remote.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class RemoteApiRepository {
    suspend fun doRequestAsync(url: String) = withContext(Dispatchers.IO) {
        URL(url).readText()
    }
}