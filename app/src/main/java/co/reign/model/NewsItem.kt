package co.reign.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "news",
    indices = [Index("newsID")]
)
data class NewsItem(
    @field:Json(name = "comment_text")
    var commentText: String? = "",
    @field:Json(name = "story_text")
    var storyText: String? = "",
    @field:Json(name = "story_id")
    var storyId: String? = "",
    @field:Json(name = "created_at")
    var createdAt: String = "",
    @field:Json(name = "created_at_i")
    var createdAtI: Int? = 0,
    @field:Json(name = "url")
    var url: String? = "",
    @field:Json(name = "story_title")
    var storyTitle: String? = "",
    @field:Json(name = "parent_id")
    var parentId: String? = "",
    @field:Json(name = "story_url")
    var storyUrl: String? = "",
    @field:Json(name = "objectID")
    @PrimaryKey
    var newsID: String = "",
    @field:Json(name = "title")
    var title: String? = "",
    @field:Json(name = "author")
    var author: String? = "",
    var isNewsDelete: Boolean = false,
) : Parcelable