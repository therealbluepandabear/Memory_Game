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

class GameFragment(var gridSize: Int) : Fragment() {
    private var _binding: FragmentGameBinding? = null

    private val binding get() = _binding!!

    private lateinit var caller: GameFragmentListener

    companion object {
        fun newInstance(grid: Int): GameFragment {
            return GameFragment(grid)
        }
    }

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
        binding.gameRecyclerView.layoutManager = GridLayoutManager(context, gridSize)
        val textViews = caller.makeTiles()
        binding.gameRecyclerView.adapter = GameRecyclerAdapter(textViews, caller)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}