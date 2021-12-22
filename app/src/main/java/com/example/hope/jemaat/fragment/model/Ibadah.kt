package com.example.hope.jemaat.fragment.model

data class Ibadah(
    var ibadah_id : String,
    var nama_ibadah : String,
    var date : String? = null,
    var firman : String? = null,
    var singer : String? = null,
    var music : String? = null,
    var dancer : String? = null,
)
