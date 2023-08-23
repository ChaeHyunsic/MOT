package com.example.mot

import retrofit2.Call
import retrofit2.http.*

interface Service {
    @POST("/login")
    fun login(
        @Body loginRequestData: LoginRequestData
    ) : Call<LoginData>

    @GET("/hotel/search/peopleandday")
    fun search(
        @Query("name") name: String?,
        @Query("checkin") checkin: String?,
        @Query("people") people: Int?,
        @Query("checkout") checkout: String?
    ) : Call<List<inq_accommodation>>

    @GET("/assign-access-token")
    fun refresh_token(
        @Header("Refresh") Refresh: String?
    ) : Call<Void>

}