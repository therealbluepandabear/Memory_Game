package com.realtomjoney.memorygame

interface GameFragmentListener {
    fun makeTiles(): ArrayList<Tile>
    fun tileTapped(tile: Tile)
}