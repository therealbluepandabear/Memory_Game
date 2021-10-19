package com.realtomjoney.memorygame

import android.os.Handler
import android.os.Looper

fun MainActivity.extendedTileTapped(tile: Tile) {
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