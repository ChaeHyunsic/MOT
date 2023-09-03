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