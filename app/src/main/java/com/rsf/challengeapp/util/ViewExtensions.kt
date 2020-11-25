package com.rsf.challengeapp.util

import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

fun TextView.textColor(textToColor: String, colorId: Int) {
    var currentText = getText()
    if (currentText !is Spannable) {
        setText(currentText, TextView.BufferType.SPANNABLE)
        currentText = getText()
    }

    if (currentText is Spannable) {
        val startIdx = currentText.indexOf(textToColor)
        val endIdx = startIdx + textToColor.length

        val color = ResourcesCompat.getColor(resources, colorId, resources.newTheme())
        currentText.setSpan(
            ForegroundColorSpan(color), startIdx, endIdx, Spannable.SPAN_INCLUSIVE_EXCLUSIVE )
    }
}