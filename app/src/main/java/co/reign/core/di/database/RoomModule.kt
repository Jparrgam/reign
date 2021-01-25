package co.reign.core.di.database

import android.content.Context
import androidx.room.Room
import co.reign.database.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providerNewsDatabase(@ApplicationContext context: Context): NewsDataBase =
        Room.databaseBuilder(context, NewsDataBase::class.java, "news_database")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesNewsDao(dataBase: NewsDataBase) = dataBase.newsDao()
}