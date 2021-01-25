package co.reign.usecase

import co.reign.core.base.usecase.UseCase
import co.reign.model.NewsItem
import co.reign.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
): UseCase<NewsItem, Boolean>() {
    override fun perform(params: NewsItem): Flow<Boolean> =
        newsRepository.updateNews(params)
}