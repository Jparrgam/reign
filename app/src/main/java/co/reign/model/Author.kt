package co.reign.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Author(
    @Json(name = "matchLevel")
    val matchLevel: String = "",
    @Json(name = "value")
    val value: String = ""
): Parcelable