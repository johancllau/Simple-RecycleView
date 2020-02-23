package com.example.recyclerview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
    class inilah yang dinamakan sebagai model
    dimana model tersebutlah yang akan digunakan
    untuk memanipulasi data yang akan ditampilkan,
    baik itu recyclerview maupun item lainnya.
 */

@Parcelize
data class Hero(
    var name: String,
    var description: String,
    var photo: String
) : Parcelable