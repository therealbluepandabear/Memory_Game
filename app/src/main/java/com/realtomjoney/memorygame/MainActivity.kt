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

    fun extendedMakeTiles(): ArrayList<Tile> {
        val tilesArray: ArrayList<Tile> = ArrayList()
        for (i in 1..gridSize * gridSize) {
            var num = i
            if (num > gridSize * gridSize / 2) {
                num -= gridSize * gridSize / 2
            }

            val newTile = Tile(this, num)

            newTile.updateTile()

            tilesArray.add(newTile)
        }
        tilesArray.shuffle()
        return tilesArray
    }

    override fun makeTiles(): ArrayList<Tile> {
        return extendedMakeTiles()
    }

    override fun tileTapped(tile: Tile, index: Int) {
        if (!gameIsActive || tile.tileStatus == Status.FOUND || tile.tileStatus == Status.FLIPPED)
            return

        tile.tileStatus = Status.FLIPPED
        tile.updateTile()

        if (!thisIsSecondTap) {
            tile1 = tile
            thisIsSecondTap = true
        } else {
            tile2 = tile
            thisIsSecondTap = false

            gameIsActive = false

            Handler(Looper.getMainLooper()).postDelayed({
                compare()
            }, 1000)
        }
    }


}