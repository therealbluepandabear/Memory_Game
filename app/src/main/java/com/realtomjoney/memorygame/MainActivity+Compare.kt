package com.realtomjoney.memorygame

import android.widget.Toast

fun MainActivity.compare() {
    if (tile1.value == tile2.value) {
        tile1.tileStatus = Status.FOUND
        tile2.tileStatus = Status.FOUND

        tile1.updateTile()
        tile2.updateTile()

        foundTiles.add(tile1)
        foundTiles.add(tile2)

        if (foundTiles.size == gridSize * gridSize) {
            Toast.makeText(this, "You Won!", Toast.LENGTH_LONG).show()
        }
    } else {
        tile1.tileStatus = Status.UNKNOWN
        tile2.tileStatus = Status.UNKNOWN

        tile1.updateTile()
        tile2.updateTile()
    }
    gameIsActive = true
}