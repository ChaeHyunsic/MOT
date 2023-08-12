package com.example.mot

import com.example.mot.Certifi
import com.example.mot.Id
import com.example.mot.Id_Duplic
import com.example.mot.Pw
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {
    @FormUrlEncoded //인코딩 오류를 방지
    @POST("/") //root url이후에 것을 작성할 것
    fun requestId(
        //여기가 인풋을 정의하는 곳
        @Field("userid") userid:String //post로 받는 이름이랑
    ) : Call<Id_Duplic> //아우풋을 정의하는 곳


    @FormUrlEncoded //인코딩 오류를 방지
    @POST("/") //root url이후에 것을 작성할 것
    fun requestCertifi(
        //여기가 인풋을 정의하는 곳
        @Field("certifi") certifi:String //post로 받는 이름이랑
    ) : Call<Certifi> //아우풋을 정의하는 곳

    @FormUrlEncoded //인코딩 오류를 방지
    @GET("/") //root url이후에 것을 작성할 것
    fun requestId(
        //여기가 인풋을 정의하는 곳
    ) : Call<Id> //아우풋을 정의하는 곳

    @FormUrlEncoded //인코딩 오류를 방지
    @POST("/") //root url이후에 것을 작성할 것
    fun requestPw(
        //여기가 인풋을 정의하는 곳
    ) : Call<Pw> //아우풋을 정의하는 곳

}