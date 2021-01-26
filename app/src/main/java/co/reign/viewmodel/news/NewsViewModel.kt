package co.reign.viewmodel.news

import android.content.Context
import co.reign.R
import co.reign.core.viewmodel.AssistedViewModelFactory
import co.reign.core.viewmodel.MvRxViewModel
import co.reign.core.viewmodel.hiltMavericksViewModelFactory
import co.reign.usecase.DeleteNewsUseCase
import co.reign.usecase.GetNewsUseCase
import co.reign.viewmodel.news.state.NewsState
import com.airbnb.mvrx.*
import com.skydoves.whatif.whatIfNotNull
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext

class NewsViewModel @AssistedInject constructor(
    @Assisted initialState: NewsState,
    @ApplicationContext val context: Context,
    private val getNewsUseCase: GetNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase,
) : MvRxViewModel<NewsState>(initialState) {

    private var page: Int = -1
    private var maxViewNews = 20

    init {
        fetchHackerNews()
    }

    fun fetchHackerNews() = withState { state ->
        if (state.request is Loading) return@withState
        if (state.news.size < maxViewNews) {
            page += 1
            getNewsUseCase.perform(params = page)
                .execute {
                    val response = it()
                    response.whatIfNotNull { maxItems ->
                        maxViewNews = maxItems.nbHits
                    }
                    copy(
                        request = it,
                        news = news + (response?.hits ?: mutableListOf()),
                        countNews = context.getString(
                            R.string.home_subtitle_count_news,
                            response?.nbHits ?: ""
                        )
                    )
                }
        }
    }

    fun clearSearch() {
        setState {
            page = -1
            copy(news = emptyList(), request = Uninitialized)
        }
    }

    fun deleteNews(newsId: String) = withState { state ->
        if (state.request is Success) {
            deleteNewsUseCase.perform(newsId)
                .execute {
                    if (it is Success) {
                        val updateNews =
                            state.news.filter { filter ->
                                filter.newsID != newsId
                            }
                        copy(
                            news = updateNews
                        )
                    } else {
                        copy()
                    }
                }
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<NewsViewModel, NewsState> {
        override fun create(state: NewsState): NewsViewModel
    }

    companion object :
        MavericksViewModelFactory<NewsViewModel, NewsState> by hiltMavericksViewModelFactory()
}