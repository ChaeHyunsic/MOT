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
    var createdAt: String,
    var modifiedAt: String,
    var id: Int,
    var maxPeople: Int,
    var minPeople: Int,
    var price: Int,
    var name: String,
    var star: Int,
    var map: String,
    var transfer: String,
    var address: String,
    var info: String,
    var commentCount: Int,
    var distance: String,
    var sellMember: String,
    var hearts: Array<Int>,
    var comments: Array<String>,
    var hotelCategories: Array<String>,
    var packages: Array<String>,
    var rooms: Array<String>
)
