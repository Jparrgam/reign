package co.reign.repository

import androidx.annotation.WorkerThread
import co.reign.core.either.ApiResult
import co.reign.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    @WorkerThread
    fun getNewsByFilter(page: Int): Flow<ApiResult<NewsResponse>>
}