package com.sazib.mypicker.picker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SliderItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

  val tvItem: TextView? = itemView?.findViewById(com.sazib.mypicker.R.id.tv_item)
}