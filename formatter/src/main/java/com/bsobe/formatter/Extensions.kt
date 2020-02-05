package com.bsobe.formatter

import android.graphics.Paint

internal fun CharSequence.measure(paint : Paint): Int {
    return paint.measureText(this, 0, length).toInt()
}