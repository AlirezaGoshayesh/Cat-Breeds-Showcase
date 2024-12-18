package com.sample.cats.data.remote.connection

import com.sample.cats.data.model.Cat
import retrofit2.http.GET

interface MService {

    /**
     * get cats breeds
     */
    @GET("breeds")
    suspend fun getBreeds(): List<Cat>


}