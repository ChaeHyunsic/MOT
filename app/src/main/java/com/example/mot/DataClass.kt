package com.example.mot
//아웃풋을 만든다.

data class LoginRequestData(
    var loginId:String,
    var loginPw:String
)

data class LoginData(
    var name: String,
    var loginId: String,
    var purchaseMember: Boolean
)

data class inq_accommodation(
    var id: Int,
    var maxPeople: Int,
    var minPeople: Int,
    var price: Int,
    var name: String,
    var star: Double,
    var address: String,
    var map: String,
    var region: String,
    var addressInfo: String,
    var transfer: String,
    var info: String,
    var distance: String,
    var photo: String
)

data class HotelRequestData(
    var name: String,
    var region: String,
    var address: String,
    var addressInfo: String
)

data class HotelResponseData(
    var id: Int,
    var maxPeople: Int,
    var minPeople: Int,
    var price: Int,
    var name: String,
    var star: Double,
    var address: String,
    var map: String,
    var region: String,
    var addressInfo: String,
    var transfer: String,
    var info: String,
    var distance: String,
    var photo: String
)

data class Check(
    var check: Boolean
)

data class Certifi(
    var phoneNumber: String,
    var randomNumber: Int?
)

data class  Id(
    var id: String
)

data class Pw(
    var pw: String
)

data class JoinRequestData(
    var loginId: String,
    var loginPw: String,
    var phone: String
)

data class JoinResponseData(
    var id: Int,
    var loginId: String
)

data class FindId(
    var id: Int,
    var loginId: String,
    var createdAt: String
)

data class ChkId(
    var loginId: String,
    var phone: String,
    var randomNumber: Int
)

data class CertifiCode(
    var code: String
)

data class Account(
    var name: String,
    var bank: String,
    var number: String
)

data class RequestAccount(
    var id: Int,
    var name: String,
    var bank: String,
    var number: String,
    var sellMemberId: Int
)

data class ProduceRoom(
    var name: String,
    var person: Int,
    var price: Int,
    var minPeople: Int,
    var maxPeople: Int
)

data class RequestProduceRoom(
    var id: Int,
    var name: String,
    var person: Int,
    var price: Int,
    var minPeople: Int,
    var maxPeople: Int
)


data class CommentWriteData(
    var context: String,
    var star: Double
)

data class CommentData(
    var id: Int,
    var context: String,
    var star: Double,
    var photo: List<String>,
    var visible : Boolean
)

data class CommentImageData(
    var commentId: Int,
    var image : List<String>
)

data class CommentInqData(
    var id: Int,
    var hotelstar: Double,
    var context: String,
    var star: Double,
    var photo: List<String>,
    var visible: Boolean,
    var modifiedAt : String,
    var packageName : String,
    var hotelName : String,
    var roomName : String
)

data class ReplyWriteData(
    var commentId: Int,
    var content : String
)

data class ReplyData(
    var id: Int,
    var context: String
)

data class ComVisibleData(
    var commentId: Int,
    var visible: Boolean
)

data class HeartCreateData(
    var id: Int,
    var purchaseMemberId : Int,
    var hotelId: Int
)

data class HeartInqData(
    var id: Int,
    var star: Double,
    var commentCount: Int,
    var name: String,
    var photo: String,
    var price: Int
)

data class ComVisibleChange(
    var id: Int,
    var context: String,
    var star: Double,
    var photo: List<String>,
    var visible: Boolean
)
data class NicknameData(
    var name : String
)

data class SelCommentInqData(
    var hotelstar: Double,
    var comments: List<SelCommentData>,
)

data class SelCommentData(
    var commentId: Int,
    var name: String,
    var context: String,
    var star: Double,
    var photo: String,
    var visible: Boolean,
    var modifiedAt: String,
    var messages: List<SelCommentDetail>,
    var packageName : String,
    var roomName : String
)

data class SelCommentDetail(
    var mesageId: Int,
    var content: String,
)

data class ReserveInqData(
    var hotelInfo : HotelInfoData,
    var name : String,
    var star : Double,
    var commentCount: Int,
    var photo: String,
    var checkIn: String,
    var checkOut: String,
    var roomInfo: List<RoomInfoData>,
    var packageInfo: List<Int>
)

data class HotelInfoData(
    var name: String,
    var star: Double,
    var commentCount: Int,
    var photo: String
)

data class RoomInfoData(
    var name: String
)