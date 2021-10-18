package com.realtomjoney.memorygame

import android.content.Context
import android.widget.TextView

data class Tile(var myContext: Context, var value: Int) : androidx.appcompat.widget.AppCompatTextView(myContext) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        setMeasuredDimension(width, width)
    }
}