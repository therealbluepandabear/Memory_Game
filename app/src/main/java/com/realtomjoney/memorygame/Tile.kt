package com.realtomjoney.memorygame

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
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
        val objectAnimator1 = ObjectAnimator.ofFloat(this, "scaleX", 1f, 0f)
        val objectAnimator2 = ObjectAnimator.ofFloat(this, "scaleX", 0f, 1f)

        objectAnimator1.duration = 100
        objectAnimator2.duration = 100

        objectAnimator1.interpolator = DecelerateInterpolator()
        objectAnimator2.interpolator = AccelerateInterpolator()

        objectAnimator1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)

                objectAnimator2.start()

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
        })

        objectAnimator1.start()
    }
}