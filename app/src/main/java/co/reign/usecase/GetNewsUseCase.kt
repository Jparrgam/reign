package co.reign.usecase

import co.reign.core.base.usecase.UseCase
import co.reign.core.either.ApiResult
import co.reign.model.NewsResponse
import co.reign.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : UseCase<Int, NewsResponse>() {
    override fun perform(params: Int): Flow<NewsResponse> = flow {
       newsRepository.getNewsByFilter(page = params).collect { result ->
           when(result) {
               is ApiResult.Success -> emit(result.data)
               is ApiResult.Error -> throw result.exception
           }
       }
    }
}