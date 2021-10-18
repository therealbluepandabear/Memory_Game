package com.realtomjoney.memorygame

import android.content.Context
import android.graphics.Color
import android.widget.TextView

enum class Status {
    UNKNOWN, FLIPPED, FOUND
}

data class Tile(var myContext: Context, var value: Int) : androidx.appcompat.widget.AppCompatTextView(myContext) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        setMeasuredDimension(width, width)
    }

    var tileStatus: Status = Status.UNKNOWN

    fun updateTile() {
        when (tileStatus) {
            Status.UNKNOWN -> {
                this@Tile.text = "?"
                this@Tile.setBackgroundColor(Color.DKGRAY)
            }
            Status.FLIPPED -> {
                this@Tile.text = value.toString()
                this@Tile.setBackgroundColor(Color.YELLOW)
            }
            Status.FOUND -> {
                this@Tile.text = "😊"
                this@Tile.setBackgroundColor(Color.GREEN)
            }
        }
    }
}