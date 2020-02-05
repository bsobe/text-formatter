package com.bsobe.formatter

import com.bsobe.formatter.TextFormatter.Companion.DIVIDER

internal class PatternSolver(private val encodedPattern: String) {

    fun solve(): MutableMap<Int, CharSequence> {
        var startIndex = -1
        val decoded = mutableMapOf<Int, CharSequence>()

        val builder = StringBuilder(encodedPattern)
        builder.forEachIndexed { index, c ->
            if (c.toString() == DIVIDER) {
                if (startIndex != -1) {
                    decoded[startIndex] = builder.substring(startIndex, index)
                    builder.delete(startIndex, index)
                    startIndex = -1
                }
            } else {
                if (startIndex == -1) {
                    startIndex = index
                }
            }
        }
        return decoded
    }

}