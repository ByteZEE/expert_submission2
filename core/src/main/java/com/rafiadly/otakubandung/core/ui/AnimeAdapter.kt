package com.rafiadly.otakubandung.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafiadly.otakubandung.core.databinding.ItemAnimeBinding
import com.rafiadly.otakubandung.core.domain.model.Anime
import com.rafiadly.otakubandung.core.utils.loadImage

class AnimeAdapter(private val callback: AnimeCallback) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    private val mData = ArrayList<Anime>()

    fun setData(data: ArrayList<Anime>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    interface AnimeCallback {
        fun onAnimeClick(anime: Anime)
    }

    inner class AnimeViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(anime: Anime) {
            with(binding) {
                tvTitleItem.text = anime.canonicalTitle ?: "-"
                var rating = anime.averageRating?.toFloat()
                if (rating != null) {
                    rating /= 20
                }
                tvRating.rating = rating ?: 0.0F
                tvAverageRatingDetailText.text =anime.averageRating.toString()
                tvSinopsis.text =  anime.synopsis ?: "-"
                imgPosterItem.loadImage(anime.posterImage?.large)
                root.setOnClickListener { callback.onAnimeClick(anime) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AnimeViewHolder(
            ItemAnimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) =
        holder.bind(mData[position])

    override fun getItemCount(): Int = mData.count()

}