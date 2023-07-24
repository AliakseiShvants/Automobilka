package by.shvants.avtomobilka.utils

import android.content.Context
import kotlin.math.roundToInt

fun Context.dpToPx(dp: Int): Int {
    val density = this.resources
        .displayMetrics
        .density
    return (dp.toFloat() * density).roundToInt()
}