package co.reign.model

import com.squareup.moshi.Json

data class NewsResponse(
        @field:Json(name = "hits")
        var hits: List<NewsItem>?,
        @field:Json(name = "query")
        val query: String = "",
        @field:Json(name = "nbHits")
        val nbHits: Int = 0,
        @field:Json(name = "page")
        val page: Int = 0
)