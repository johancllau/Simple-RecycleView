package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerview.R
import com.example.recyclerview.model.Hero
import kotlinx.android.synthetic.main.item_row_hero_list.view.*


/*
    class Adapter yang akan digunakan pada recyclerview.
    recycler view membutuhkan adapter untuk proses inflate layout itemnya,
     bind data kedalam itemnya, mengetahui size itemnya dan lainnya.

     konsep umum dari sebuah adapter untuk recyclerview adalah
     class adapter tersebut harus extends (turunan) dari class class viewholder.
     dan ketika class adapter tersebut extends keclass yang viewholder tersebut maka
     harus mengimplementasikan 3 override function yaitu seperti dibawah ini->
     onCreateViewHolder(), getItemCount(), and onBindViewHolder()

 */
class HeroListAdapter(private val itemHero: List<Hero>) : RecyclerView.Adapter<HeroListAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero_list, parent, false))
    }

    override fun getItemCount(): Int = itemHero.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(itemHero[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Hero) {
            with(itemView) {
                textViewTitleHeroes.text = item.name
                textViewDescHeroes.text = item.description
                Glide.with(context).load(item.photo).apply(RequestOptions().override(80, 80)).into(imageViewHeroes)
                this.setOnClickListener { onItemClickCallback?.onItemClicked(item) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }
}