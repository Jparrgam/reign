package co.reign.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class HighlightResult(
    @field:Json(name = "author")
    val author: Author? = null,
    @field:Json(name = "title")
    val title: Title? = null,
    @field:Json(name = "url")
    val url: Url? = null
): Parcelable