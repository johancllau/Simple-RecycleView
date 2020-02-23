package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerview.R
import com.example.recyclerview.model.Hero
import kotlinx.android.synthetic.main.item_row_hero_grid.view.*


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
class HeroGridAdapter(private val itemHero: List<Hero>) : RecyclerView.Adapter<HeroGridAdapter.GridViewHolder>() {

    private var onItemClickCallBackGrid: OnItemClickCallBackGrid? = null

    fun setOnItemClickCallBackGrid(onItemClickCallBackGrid: OnItemClickCallBackGrid) {
        this.onItemClickCallBackGrid = onItemClickCallBackGrid
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero_grid, parent, false))
    }

    override fun getItemCount(): Int = itemHero.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(itemHero[position])
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Hero) {
            with(itemView) {
                Glide.with(context)
                    .load(item.photo)
                    .apply(RequestOptions().override(350, 350))
                    .into(imageViewHeroesGrid)
                this.setOnClickListener { onItemClickCallBackGrid?.onItemClickedGrid(item) }
            }
        }
    }

    interface OnItemClickCallBackGrid {
        fun onItemClickedGrid(data: Hero)
    }
}