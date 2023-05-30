package com.dev_akash.catassignment.data.remote

import com.dev_akash.catassignment.data.model.BreedDto
import com.dev_akash.catassignment.data.model.CatsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("v1/images/search")
    suspend fun getCatList(
        @Query("page") page: Int,
        @Query("breed_ids") breedId: String?,
        @Query("limit") limit: Int = 10,
        @Query("has_breeds") hasBreeds: Int = 1
    ): Response<List<CatsDto>>

    @GET("v1/breeds")
    suspend fun getBreedsList(): Response<List<BreedDto>>

}