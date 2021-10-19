package com.realtomjoney.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.realtomjoney.memorygame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), GameFragment.GameFragmentListener {
    var gridSize = 4
    var thisIsSecondTap = false
    lateinit var tile1: Tile
    lateinit var tile2: Tile
    var gameIsActive = true
    val foundTiles: ArrayList<Tile> = ArrayList()
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()

        restartGame()

        binding.restartButton.setOnClickListener {
            restartGame()
        }
    }

    private fun setBindings() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun makeTiles(): ArrayList<Tile> {
        return extendedMakeTiles()
    }

    override fun tileTapped(tile: Tile) {
        extendedTileTapped(tile)
    }


}