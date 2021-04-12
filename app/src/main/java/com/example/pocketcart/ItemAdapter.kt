package com.example.pocketcart

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.grocery_item.view.*

class ItemAdapter (
    private val items: MutableList<Item>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.grocery_item,
                parent,
                false
            )
        )
    }

    fun addItem(item: Item){
        items.add(item)
        notifyItemInserted(items.size -1)
    }

    fun deleteCheckedItems() {
        items.removeAll { item ->
            item.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvItemTitle: TextView, isChecked: Boolean) {
        if(isChecked){
            tvItemTitle.paintFlags = tvItemTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            // .inv() function inverts
            tvItemTitle.paintFlags = tvItemTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]

        //shorthand method
        holder.itemView.apply {
            tvItemTitle.text = currentItem.title
            cbChecked.isChecked = currentItem.isChecked
            toggleStrikeThrough(tvItemTitle, currentItem.isChecked)
            // lambda function, is called every time cbChecked state is changed
            cbChecked.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvItemTitle, isChecked)
                currentItem.isChecked = !currentItem.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}