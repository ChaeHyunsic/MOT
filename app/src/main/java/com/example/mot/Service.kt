package com.example.mot

import android.accounts.Account
import okhttp3.ResponseBody
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

    @GET("/check-login-id/{id}") //root url이후에 것을 작성할 것
    fun requestId(
        @Path("id") id: String?
    ) : Call<Check> //아우풋을 정의하는 곳

    @POST("/send-message/{phone}") //root url이후에 것을 작성할 것
    fun requestCertifi(
        @Path("phone") phone:String? //post로 받는 이름이랑
    ) : Call<Certifi> //아우풋을 정의하는 곳

    @POST("/signin")
    fun requestJoin(
        @Body joinRequestData: JoinRequestData
    ): Call<JoinResponseData>

    @GET("/check-random-number")
    fun chkRandomNum(
        @Body certifi: Certifi
    ): Call<Check>


    @GET("/find-login-id") //root url이후에 것을 작성할 것
    fun findId(
        @Body certifi: Certifi
    ) : Call<FindId> //아우풋을 정의하는 곳


    @POST("/login/phone") //root url이후에 것을 작성할 것
    fun chkId(
        @Body chkId: ChkId
    ): Call<Check>

    @PATCH("/change-pw")
    fun modifyPw(
        @Body loginPw: String
    ): Call<ResponseBody>

    @PATCH("/account/{account}")
    fun modifyaccount(
        @Path("account") account: Account
    ): Call<RequestAccount>

    @GET("/accountt?accountId=1")
    fun getaccount(
    ): Call<RequestAccount>

    @POST("/room/{hotel-id}")
    fun produceroom(
        @Body produceRoom: ProduceRoom
    ): Call<RequestProduceRoom>

}