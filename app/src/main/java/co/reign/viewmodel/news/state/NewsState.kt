package co.reign.viewmodel.news.state

import co.reign.model.NewsItem
import co.reign.model.NewsResponse
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import java.lang.Exception

data class NewsState(
    val request: Async<NewsResponse> = Uninitialized,
    val news: List<NewsItem> = emptyList(),
): MavericksState