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

    // 닉네임 조회
    @GET("/nickname")
    fun nicknameInq(
        //@Header("Authorization") Authorization: String?
    ) : Call<NicknameData>

    // 닉네임 변경
    @PUT("/nickname/{nick-name}")
    fun nicknameChange(
        //@Header("Authorization") Authorization: String?
        @Path("nickname") nickname : String?
    ) : Call<NicknameData>

    @GET("/hotel/search")
    fun areaSelect(
        @Query("name") name: String?,
    ) : Call<List<inq_accommodation>>

    // 구매자 댓글 생성
    @POST("/comment")
    fun commentWrite(
        // @Header("Authorization") Authorization: String?,
        @Body commentWriteData : CommentWriteData,
        @Query("reserveId") reserveId : Int?
    ) : Call<CommentData>

    // 구매자 댓글 수정
    @PATCH("/comment/PurchaseMember/{comment-id}")
    fun commentEdit(
        //@Header("Authorization") Authorization: String?,
        @Body commentEditData: CommentWriteData,
        @Path (value = "comment-id") commentId: Int?
    ) : Call<CommentData>

    // 구매자 댓글 사진 수정
    @PATCH("/comment/PurchaseMember/upload-images")
    fun commentImageEdit(
        //@Header("Authorization") Authorization: String?,
        @Body commentImage: CommentImageData
    ) : Call<CommentData>

    // 구매자 댓글 조회
    @GET("/comment/PurchaseMember")
    fun commentInq(
        //@Header("Authorization") Authorization: String?
    ) : Call<List<CommentInqData>>

    // 구매자 댓글 삭제
    @DELETE("/comment/PurchaseMember/{comment -id}")
    fun commentDel(
        //@Header("Authorization") Authorization: String?,
        @Path("comment-id") commentId : Int?
    ) : Call<Void>

    // 판매자 댓글 조회
    @GET("/comment/sellmember")
    fun selCommentInq(
        // @Header("Authorization") Authorization: String?,
    ) : Call<SelCommentInqData>

    // 답글 작성
    @POST("/message")
    fun replyWrite(
        // @Header("Authorization") Authorization: String?,
        @Body replyWriteData: ReplyWriteData
    ) : Call<ReplyData>

    // 답글 조회
    @GET("/message/{message-id}")
    fun replyInq(
        // @Header("Authorization") Authorization: String?,
        @Path(value = "message-id") messageId : Int
    ) : Call<ReplyData>

    // 판매자 댓글 보임여부 변경
    @PATCH("/comment/sellmember")
    fun commentVis(
        // @Header("Authorization") Authorization: String?,
        @Body selComVisible : ComVisibleData
    ): Call<ComVisibleChange>

    // 좋아요 생성
    @POST("/heart")
    fun heartCreate(
        //@Header("Authorization") Authorization: String?,
        @Query("hotelId") hotelId: String?
    ) : Call<HeartCreateData>

    // 좋아요 조회
    @GET("/heart")
    fun heartInq(
        //@Header("Authorization") Authorization: String?,
    ) : Call<List<HeartInqData>>

    // 좋아요 삭제
    @DELETE("heart/{heart-id}")
    fun heartDel(
        //@Header("Authorization") Authorization: String?
        @Path("heartId") heartId : Int
    ) : Call<Void>

    // 호텔 생성
    @POST("/hotel")
    fun hotel_add(
        //@Header("Authorization") Authorization: String?,
        @Body hotelRequestData: HotelRequestData?
    ) : Call<HotelResponseData>

    // 구매자 예약 내역 조회
    @GET("/reserve")
    fun reserveInq(
        //@Header("Authorization") Authorization: String?,
    ) : Call<List<ReserveInqData>>

}