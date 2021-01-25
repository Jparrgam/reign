package co.reign.repository

import androidx.annotation.WorkerThread
import co.reign.core.either.ApiResult
import co.reign.datasource.NewsDataSource
import co.reign.model.NewsResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val dataSource: NewsDataSource) :
    NewsRepository {
    @WorkerThread
    override fun getNewsByFilter(page: Int): Flow<ApiResult<NewsResponse>> = flow {
        dataSource.getNewsByFilter(page = page)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    emit(ApiResult.Success(it))
                }
            }.suspendOnException {
                throw Throwable(message())
            }.suspendOnFailure {
                throw Throwable()
            }
    }.flowOn(Dispatchers.IO)
}