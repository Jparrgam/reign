package co.reign.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsItem(
    @field:Json(name = "comment_text")
    val commentText: String? = null,
    @field:Json(name = "story_text")
    val storyText: String? = null,
    @field:Json(name = "story_id")
    val storyId: String? = null,
    @field:Json(name = "_tags")
    val tags: List<String>?,
    @field:Json(name = "created_at")
    val createdAt: String = "",
    @field:Json(name = "created_at_i")
    val createdAtI: Int? = null,
    @field:Json(name = "url")
    val url: String? = "",
    @field:Json(name = "_highlightResult")
    val HighlightResult: HighlightResult,
    @field:Json(name = "story_title")
    val storyTitle: String? = null,
    @field:Json(name = "parent_id")
    val parentId: String? = null,
    @field:Json(name = "story_url")
    val storyUrl: String? = null,
    @field:Json(name = "objectID")
    val newsID: String? = "",
    @field:Json(name = "title")
    val title: String? = "",
    @field:Json(name = "author")
    var author: String? = ""
) : Parcelable