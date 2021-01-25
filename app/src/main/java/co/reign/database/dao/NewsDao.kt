package co.reign.database.dao

import androidx.room.*
import co.reign.model.NewsItem

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<NewsItem>)

    @Update
    fun updateNews(news: NewsItem): Int

    @get:Query("SELECT * FROM news where isNewsDelete = 0")
    val allNews: List<NewsItem>

    @get:Query("SELECT * from news where isNewsDelete = 1")
    val deleteNews: List<NewsItem>
}