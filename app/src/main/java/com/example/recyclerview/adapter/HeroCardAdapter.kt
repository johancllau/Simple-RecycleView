package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerview.R
import com.example.recyclerview.model.Hero
import kotlinx.android.synthetic.main.item_row_hero_card.view.*

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

class HeroCardAdapter(private val itemHero: List<Hero>) : RecyclerView.Adapter<HeroCardAdapter.CardViewHolder>() {

    private var onItemClickCallbackCard: OnItemClickCallbackCard? = null

    fun setOnItemClickedCard(onItemClickCallbackCard: OnItemClickCallbackCard) {
        this.onItemClickCallbackCard = onItemClickCallbackCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero_card, parent, false))
    }

    override fun getItemCount(): Int = itemHero.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(itemHero[position])
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Hero) {
            with(itemView) {
                Glide.with(context)
                    .load(item.photo)
                    .apply(RequestOptions().override(350, 350))
                    .into(imageViewHeroesCard)

                textViewTitleHeroesCard.text = item.name
                textViewDescHeroesCard.text = item.description
                buttonLikeCard.setOnClickListener { Toast.makeText(context, "You Like ${item.name}", Toast.LENGTH_SHORT).show() }
                buttonShareCard.setOnClickListener { Toast.makeText(context, "You Want To Share ${item.name}", Toast.LENGTH_SHORT).show() }
                this.setOnClickListener { onItemClickCallbackCard?.onItemClickedCard(item) }
            }
        }
    }

    interface OnItemClickCallbackCard {
        fun onItemClickedCard(data: Hero)
    }
}