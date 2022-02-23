package com.epam.caloriecalc.data.remote

import com.epam.caloriecalc.data.local.entities.ProductRecord
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("/getItems")
    suspend fun getProducts() : Response<ProductRecord>

    @POST("/postIntakes")
    suspend fun postIntake() : Unit

}