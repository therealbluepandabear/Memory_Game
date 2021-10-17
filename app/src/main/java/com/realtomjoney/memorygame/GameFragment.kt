package com.realtomjoney.memorygame

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GameFragment : Fragment() {
    interface GameFragmentListener {
        fun makeTiles(): ArrayList<TextView>
    }

    private lateinit var caller: GameFragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is GameFragmentListener) {
            caller = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(
            R.layout.fragment_game,
            container,
            false)

        val context = activity as Context
        val recyclerView: RecyclerView = fragment.findViewById(R.id.gameRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 4)
        val textViews = caller.makeTiles()
        recyclerView.adapter = GameRecyclerAdapter(textViews)

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