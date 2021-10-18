package com.realtomjoney.memorygame

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realtomjoney.memorygame.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null

    private val binding get() = _binding!!

    interface GameFragmentListener {
        fun makeTiles(): ArrayList<Tile>
        fun tileTapped(tile: Tile, index: Int)
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
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        val context = activity as Context
        binding.gameRecyclerView.layoutManager = GridLayoutManager(context, 4)
        val textViews = caller.makeTiles()
        binding.gameRecyclerView.adapter = GameRecyclerAdapter(textViews, caller)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

class GameRecyclerAdapter(private val inputData: ArrayList<Tile>,
                          private val caller: GameFragment.GameFragmentListener)
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
            caller.tileTapped(thisTile, position)
        }
    }

    override fun getItemCount() = inputData.size;

}