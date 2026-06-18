package com.example.test0914

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GraphicView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
    ) : View(context, attrs) {

        private var bitmap = BitmapFactory.decodeResource(resources, R.drawable.sampel_image)

        var scaleFactor = 1f
        var rotationDeg = 0f
        var dx = 0f
        var dy = 0f
        var skewX = 0f
        var gray = false
        var brightness = 1f

        fun setBitmap(newBitmap: Bitmap) {
            bitmap = newBitmap
            invalidate()
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                colorFilter = buildColorFilter()
            }

            val save = canvas.save()
            canvas.translate(width / 2f + dx, height / 2f + dy)
            canvas.rotate(rotationDeg)
            canvas.scale(scaleFactor, scaleFactor)
            canvas.skew(skewX, 0f)

            canvas.drawBitmap(
                bitmap,
                -bitmap.width / 2f,
                -bitmap.height / 2f,
                paint
            )

            canvas.restoreToCount(save)
        }

        private fun buildColorFilter(): ColorFilter {
            val matrix = ColorMatrix()

            if (gray) {
                matrix.setSaturation(0f)
            }

            val brightnessMatrix = ColorMatrix(
                floatArrayOf(
                    brightness, 0f, 0f, 0f, 0f,
                    0f, brightness, 0f, 0f, 0f,
                    0f, 0f, brightness, 0f, 0f,
                    0f, 0f, 0f, 1f, 0f
                )
            )

            matrix.postConcat(brightnessMatrix)
            return ColorMatrixColorFilter(matrix)
        }
    }