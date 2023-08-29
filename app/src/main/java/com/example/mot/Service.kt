package com.example.mot

import retrofit2.Call
import retrofit2.http.*

interface Service {
    @POST("/login")
    fun login(
        @Body loginRequestData: LoginRequestData
    ) : Call<LoginData>

    @GET("/assign-access-token")
    fun refresh_token(
    ) : Call<Void>

    @GET("/hotel/search/peopleandday")
    fun search(
        @Query("name") name: String?,
        @Query("checkin") checkin: String?,
        @Query("people") people: Int?,
        @Query("checkout") checkout: String?
    ) : Call<List<inq_accommodation>>

    @GET("/hotel/search")
    fun search_name(
        @Query("name") name: String?
    ) : Call<List<inq_accommodation>>

    @POST("/hotel")
    fun hotel_add(
        @Header("Authorization") Authorization: String?,
        @Body hotelRequestData: HotelRequestData?
    ) : Call<HotelResponseData>

}