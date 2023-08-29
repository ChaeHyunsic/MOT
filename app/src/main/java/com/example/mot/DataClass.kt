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