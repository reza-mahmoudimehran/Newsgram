package ir.reza_mahmoudi.newsgram.core.util.time

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toLocalTime(): String {
    if (this.isNotEmpty()) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        return try {
            val date = dateFormat.parse(this)
            val formattedDate =
                SimpleDateFormat("d MMM, HH:mm", Locale.getDefault()).format(date ?: Date())
            formattedDate
        } catch (e: ParseException) {
            throw IllegalArgumentException("Not a valid datetime format")
        }
    }

    return ""
}