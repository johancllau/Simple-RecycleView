package com.example.recyclerview.helper

import android.content.Context
import com.example.recyclerview.R
import com.example.recyclerview.model.Hero

/*
    class inilah yang memiliki function getDataHero
    dimana digunakan untuk mengambil data dari resource string,
    karena data-data yang akan digunakan pada project ini saya simpankan kedalam
    file string dimana bisa dilihat pada file res->values->string.xml

    karena datanya terdapat lebih dari 1 item maka saya menggunakan
    for loop (perulangan for) -> untuk memasukan pada model.

    (teman-teman bisa menggunakan loop lainnya, misalkan forEach, while, do-While)
    sehingga teman-teman bisa mengasa logika dari teman-teman sendiri
 */
class HeroData(private val context: Context) {

    fun getDataHero() : ArrayList<Hero> {
        val heroName = context.resources.getStringArray(R.array.data_hero_name)
        val heroDescription = context.resources.getStringArray(R.array.data_hero_description)
        val heroPhoto = context.resources.getStringArray(R.array.data_photo_hero)

        val listHero = ArrayList<Hero>()

        for (position in heroName.indices) {
            listHero.add(
                Hero(
                    heroName[position],
                    heroDescription[position],
                    heroPhoto[position]
                )
            )
        }
        return listHero
    }
}