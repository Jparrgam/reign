package co.reign.datasource

import co.reign.model.NewsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDataSource {
    @GET("search_by_date") suspend fun getNewsByFilter(
        @Query("query") query: String = "mobile",
        @Query("page") page: Int = 0
    ): ApiResponse<NewsResponse>
}