package com.realtomjoney.memorygame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), GameFragment.GameFragmentListener {
    var thisIsSecondTap = false
    lateinit var tile1: Tile
    lateinit var tile2: Tile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.gameLayout, GameFragment.newInstance(),
            "game").commit()
    }

    override fun makeTiles(): ArrayList<Tile> {
        val tilesArray: ArrayList<Tile> = ArrayList()
        for (i in 1..16) {
            var num = i
            if (num > 8) {
                num -= 8
            }

            val newTile = Tile(this, num)

            newTile.updateTile()

            tilesArray.add(newTile)
        }
        tilesArray.shuffle()
        return tilesArray
    }

    override fun tileTapped(tile: Tile, index: Int) {
        tile.tileStatus = Status.FLIPPED
        tile.updateTile()

        if (!thisIsSecondTap) {
            tile1 = tile
            thisIsSecondTap = true
        } else {
            tile2 = tile
            thisIsSecondTap = false
            compare()
        }
    }

    fun compare() {
        Toast.makeText(this, "Let's compare", Toast.LENGTH_LONG).show()
    }
}