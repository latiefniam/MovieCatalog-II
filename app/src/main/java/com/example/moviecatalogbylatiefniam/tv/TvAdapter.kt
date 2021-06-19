package com.example.tvcatalogbylatiefniam.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogbylatiefniam.data.TvEntity
import com.example.moviecatalogbylatiefniam.databinding.ItemContentBinding
import com.example.moviecatalogbylatiefniam.detail.DetailContentActivity


class TvAdapter: RecyclerView.Adapter<TvAdapter.ViewHolder>() {

    private var list = ArrayList<TvEntity>()
    fun setTv(tvs: List<TvEntity>?){
        if (tvs == null) return
        this.list.clear()
        this.list.addAll(tvs)
    }
    class ViewHolder(private val binding: ItemContentBinding):RecyclerView.ViewHolder
        (binding.root){
            fun bind (tvs: TvEntity){
                with(binding){
                    title.text = tvs.tvTitle
                    genre.text = tvs.tvGenre
                    duration.text = tvs.tvDuration
                    Glide.with(itemView.context)
                        .load(tvs.tvPicture)
                        .into(image)
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailContentActivity::class.java)
                        intent.putExtra(DetailContentActivity.EXTRA_TVSHOW,tvs.tvId)
                        itemView.context.startActivity(intent)
                    }
                }

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContentBinding.inflate(LayoutInflater.from(parent.context),
        parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvs = list[position]
        holder.bind(tvs)
    }

    override fun getItemCount(): Int = list.size
}