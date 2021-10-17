package com.realtomjoney.memorygame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(
            R.layout.fragment_game,
            container,
            false)

        return fragment
    }

    companion object {
        fun newInstance(): GameFragment {
            return GameFragment()
        }
    }
}
class RecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.card_layout, parent, false)) {
    val tileParent: SquareFrameLayout = itemView.findViewById(R.id.tileParent)
}

class GameRecyclerAdapter(private val inputData: ArrayList<TextView>) : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecyclerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val thisTile = inputData[position]

        holder.tileParent.addView(thisTile)
    }

    override fun getItemCount() = inputData.size;

}