package com.example.hope.jemaat.fragment.model

data class Users(
    var uid : String,
    var fullname : String,
    var email : String,
    var nomertelefon : String? = null,
    var tgllahir : String? = null,
    var alamat : String? = null,
    var jeniskelamin : String? = null,
    var fotoprofil : String = ""
)
