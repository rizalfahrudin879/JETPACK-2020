package com.rizalfahrudin.moviecatalogue.core.utils

import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutor @VisibleForTesting constructor(
    private val diskIO: Executor,
) {

    constructor() : this(
        Executors.newSingleThreadExecutor(),
    )

    fun diskIO(): Executor = diskIO
}