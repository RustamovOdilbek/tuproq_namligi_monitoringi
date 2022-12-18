package com.smartirrigation.tuproq_namligi_monitoringg.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.ItemCottonExperienceViewBinding
import com.smartirrigation.tuproq_namligi_monitoringg.model.CottonExperience

class Adapter(private var onItemClicked: ((Int) -> Unit)):
    ListAdapter<CottonExperience, Adapter.ItemViewHolder>(ITEM_DIF) {

    companion object {
        val ITEM_DIF = object : DiffUtil.ItemCallback<CottonExperience>() {
            override fun areItemsTheSame(oldItem: CottonExperience, newItem: CottonExperience): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CottonExperience, newItem: CottonExperience): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ItemViewHolder(val bn: ItemCottonExperienceViewBinding) : RecyclerView.ViewHolder(bn.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            val item = getItem(position)
            with(bn){
                if (item.image!= null){
                    iv.setImageResource(item.image)
                }else{
                    cv.visibility = View.GONE
                }
                tv.text = item.name
                cl.setOnClickListener {
                    onItemClicked.invoke(item.nav)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemCottonExperienceViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }


}