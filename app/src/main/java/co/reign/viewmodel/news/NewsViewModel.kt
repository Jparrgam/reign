package co.reign.viewmodel.news

import co.reign.core.viewmodel.AssistedViewModelFactory
import co.reign.core.viewmodel.MvRxViewModel
import co.reign.core.viewmodel.hiltMavericksViewModelFactory
import co.reign.usecase.GetNewsUseCase
import co.reign.viewmodel.news.state.NewsState
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModelFactory
import com.skydoves.whatif.whatIfNotNull
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class NewsViewModel @AssistedInject constructor(
        @Assisted initialState: NewsState,
        private val getNewsUseCase: GetNewsUseCase
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
                        copy(request = it, news = news + (response?.hits ?: emptyList()))
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