package com.rsf.challengeapp.util

import android.view.View
import android.view.ViewGroup

/**
 * Split the String using " " as separator and returns the word in position [index]
 * if [index] is invalid returns the word at position 0
 * @param index the word position required
 */
fun String.word(index: Int): String {
    val words = this.split(" ")
    return if (index < words.size) words[index] else words[0]
}

fun ViewGroup.show() {
    visibility = View.VISIBLE
}

fun ViewGroup.hide() {
    visibility = View.GONE
}
