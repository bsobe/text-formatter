package com.bsobe.formatter

import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.widget.TextView

class TextFormatter private constructor(
    textView: TextView,
    decodedPatternMap: MutableMap<Int, CharSequence>
) : TextWatcher {

    private val drawableCharactersMap: MutableMap<Int, DrawableCharacter> = mutableMapOf()
    private var internalStopFormatFlag: Boolean = false

    init {
        //region measure characters
        val measuredWidths: MutableMap<CharSequence, Int> = mutableMapOf()
        decodedPatternMap.forEach {
            val index = it.key
            val charSequence = it.value
            val currentCharactersWidth: Int = if (measuredWidths.containsKey(charSequence)) {
                measuredWidths[charSequence]!!
            } else {
                val width = charSequence.measure(textView.paint)
                measuredWidths[charSequence] = width
                width
            }
            drawableCharactersMap[index] = DrawableCharacter(charSequence, currentCharactersWidth)
        }
        //endregion
        //region attach
        textView.addTextChangedListener(this)
        textView.text = textView.text
        //endregion
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    override fun afterTextChanged(editable: Editable?) {
        if (internalStopFormatFlag || editable.isNullOrEmpty()) {
            return
        }
        internalStopFormatFlag = true
        format(editable)
        internalStopFormatFlag = false
    }

    private fun format(editable: Editable) {
        //region remove old spans
        val oldSpans: Array<CharacterSpan> =
            editable.getSpans(0, editable.length, CharacterSpan::class.java)
        oldSpans.forEach { editable.removeSpan(it) }
        //endregion
        //region add new spans
        drawableCharactersMap.forEach {
            val startIndex: Int = it.key
            val drawableCharacter: DrawableCharacter = it.value
            val endIndex = startIndex + 1
            if (endIndex > editable.length) {
                return
            }
            val characterSpan = CharacterSpan(drawableCharacter = drawableCharacter)
            editable.setSpan(
                characterSpan,
                startIndex,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
        //endregion
    }

    class Builder {

        private var patternMap = mutableMapOf<Int, CharSequence>()

        fun customizeIndex(index: Int, customizedCharacter: CharSequence) = apply {
            patternMap[index] = customizedCharacter
        }

        fun addSpaceAt(index: Int) = apply {
            customizeIndex(index, " ")
        }

        fun setPattern(pattern: String) = apply {
            patternMap = PatternSolver(pattern).solve()
        }

        fun attach(textView: TextView) {
            TextFormatter(textView = textView, decodedPatternMap = patternMap)
        }
    }

    companion object {
        const val DIVIDER = "#"
    }
}