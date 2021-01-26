package co.reign.repository

import co.reign.core.either.ApiResult
import co.reign.core.interceptor.NoConnectivityException
import co.reign.database.dao.NewsDao
import co.reign.datasource.NewsDataSource
import co.reign.model.NewsItem
import co.reign.model.NewsResponse
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
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
                    val newsDataToEmit: MutableList<NewsItem> = mutableListOf()
                    response.hits?.forEach { hit ->
                        if(!isDeleteNews(hit)) {
                            newsDataToEmit.add(hit)
                        }
                    }
                    response.hits = newsDataToEmit
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
                data?.hits.whatIfNotNull { hits ->
                    newsDao.insertNews(hits)
                    newsDao.deleteNews.forEach {
                        updateNews(it.newsID)
                    }
                }
            }
    }.flowOn(Dispatchers.IO)

    override fun updateNews(newsId: String): Flow<Boolean> = flow {
        val result = newsDao.updateNews(deleteNews = 1, newsId = newsId)
        emit(result > 1)
    }.flowOn(Dispatchers.IO)

    private fun isDeleteNews(hit: NewsItem): Boolean {
        newsDao.deleteNews.forEach {
            if (it.newsID == hit.newsID)
                return true
        }

        return false
    }
}
