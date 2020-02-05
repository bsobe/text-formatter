package com.bsobe.formatter

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.ReplacementSpan

internal class CharacterSpan(
    private val drawableCharacter: DrawableCharacter,
    private val spanDirection: SpanDirection = SpanDirection.START
) : ReplacementSpan() {

    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        val widths = FloatArray(end - start)
        paint.getTextWidths(text, start, end, widths)
        var sum = paint.measureText(drawableCharacter.text.toString())
        widths.forEach { sum += it.toInt() }
        return sum.toInt()
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        canvas.save()
        if (spanDirection == SpanDirection.START) {
            //region special drawableCharacter
            canvas.drawText(
                drawableCharacter.text,
                0,
                drawableCharacter.text.length,
                x,
                y.toFloat(),
                paint
            )
            //endregion
            canvas.translate(drawableCharacter.measuredWidth + x, 0F)
            //region original text
            canvas.drawText(
                text ?: "",
                start,
                end,
                0F,
                y.toFloat(),
                paint
            )
            //endregion
        } else {
            //region original text
            canvas.drawText(
                text ?: "",
                start,
                end,
                x,
                y.toFloat(),
                paint
            )
            //endregion
            //region special drawableCharacter
            val originalText = text!![start].toString()
            val originalTextWidth = paint.measureText(originalText)
            canvas.drawText(
                drawableCharacter.text.toString(),
                0,
                1,
                x + originalTextWidth,
                y.toFloat(),
                paint
            )
            //endregion
        }
        canvas.restore()
    }
}