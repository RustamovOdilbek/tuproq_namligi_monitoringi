package com.smartirrigation.tuproq_namligi_monitoringg.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smartirrigation.tuproq_namligi_monitoringg.databinding.ItemBluetoothDeviceBinding
import com.smartirrigation.tuproq_namligi_monitoringg.helper.OnClickEvent
import com.smartirrigation.tuproq_namligi_monitoringg.model.BluetoothDeviceModel

class BluetoothListAdapter( var onClickEvent: OnClickEvent):
    ListAdapter<BluetoothDeviceModel, BluetoothListAdapter.ItemViewHolder>(
    ITEM_DIF
) {

    companion object {
        val ITEM_DIF = object : DiffUtil.ItemCallback<BluetoothDeviceModel>() {
            override fun areItemsTheSame(oldItem: BluetoothDeviceModel, newItem: BluetoothDeviceModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: BluetoothDeviceModel, newItem: BluetoothDeviceModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ItemViewHolder(val bn: ItemBluetoothDeviceBinding) : RecyclerView.ViewHolder(bn.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            val item = getItem(position)
            with(bn){
                deviceName.text = item.name
                macAddress.text = item.macNumber

                constraintlayout.setOnClickListener {
                    onClickEvent.setOnClickListener(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemBluetoothDeviceBinding.inflate(
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