package com.epam.caloriecalc.data.remote

import com.epam.caloriecalc.data.local.entities.ProductRecord
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface FakeApi {

    companion object {
        const val BASE_URL = "https://benchfoodapp.com"
        const val TAG_FAKE_API = "FakeApi"
        const val LOG_ADD_INTAKE_FAILED = "postAddIntake call failed."
        const val LOG_REMOVE_INTAKE_FAILED = "postRemoveIntake call failed."
    }

    @GET("/getItems")
    suspend fun getProducts(): Response<ProductRecord>

    @POST("/addItem")
    suspend fun postAddIntake(
        @Field("id") id: Long
    )

    @POST("/removeItem")
    suspend fun postRemoveIntake(
        @Field("id") id: Long
    )

}