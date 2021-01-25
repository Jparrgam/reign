package co.reign.repository

import co.reign.core.either.ApiResult
import co.reign.core.interceptor.NoConnectivityException
import co.reign.database.dao.NewsDao
import co.reign.datasource.NewsDataSource
import co.reign.model.NewsItem
import co.reign.model.NewsResponse
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullOrEmpty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val dataSource: NewsDataSource,
    private val newsDao: NewsDao,
) :
    NewsRepository {
    override fun getNewsByFilter(page: Int): Flow<ApiResult<NewsResponse>> = flow {
        dataSource.getNewsByFilter(page = page)
            .suspendOnSuccess {
                data.whatIfNotNull { response ->
                    emit(ApiResult.Success(response))
                }
            }.suspendOnException {
                if (exception is NoConnectivityException) {
                    val hits = newsDao.allNews
                    emit(
                        ApiResult.Success(
                            NewsResponse(
                                hits = hits,
                                nbHits = hits.size
                            )
                        )
                    )
                } else {
                    throw exception
                }
            }.onSuccess {
                data?.hits.whatIfNotNullOrEmpty {
                    newsDao.insertNews(it)
                }
            }
    }.flowOn(Dispatchers.IO)

    override fun updateNews(newsUpdate: NewsItem): Flow<Boolean> = flow {
        val result = newsDao.updateNews(newsUpdate)
        emit(result > 1)
    }.flowOn(Dispatchers.IO)
}