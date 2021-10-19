package com.realtomjoney.memorygame

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.card_layout, parent, false)) {
    val tileParent: SquareFrameLayout = itemView.findViewById(R.id.tileParent)
}

class GameRecyclerAdapter(private val inputData: ArrayList<Tile>,
                          private val caller: GameFragmentListener)
    : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecyclerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val thisTile = inputData[position]

        val params = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        params.setMargins(5, 5, 5, 5)
        thisTile.layoutParams = params
        thisTile.gravity = Gravity.CENTER
        thisTile.textSize = 24F

        holder.tileParent.addView(thisTile)

        holder.tileParent.setOnClickListener {
            caller.tileTapped(thisTile)
        }
    }

    override fun getItemCount() = inputData.size;

}