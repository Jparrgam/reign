package co.reign.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.reign.database.dao.NewsDao
import co.reign.model.NewsItem

@Database(entities = [NewsItem::class], version = 1)
abstract class NewsDataBase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}