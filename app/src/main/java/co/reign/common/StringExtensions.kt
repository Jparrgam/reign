package co.reign.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
internal fun String.formatDate(): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S").parse(this)
    return SimpleDateFormat("MMM d, h:mm a").format(date)
}