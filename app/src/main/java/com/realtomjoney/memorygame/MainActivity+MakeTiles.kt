package com.realtomjoney.memorygame

fun MainActivity.extendedMakeTiles(): ArrayList<Tile> {
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