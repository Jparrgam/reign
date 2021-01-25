package co.reign.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Title(
    @Json(name = "matchLevel")
    val matchLevel: String = "",
    @Json(name = "fullyHighlighted")
    val fullyHighlighted: Boolean = false,
    @Json(name = "value")
    val value: String = "",
    @Json(name = "matchedWords")
    val matchedWords: List<String>?
): Parcelable