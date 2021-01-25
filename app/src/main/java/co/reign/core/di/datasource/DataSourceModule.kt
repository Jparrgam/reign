package co.reign.core.di.datasource

import co.reign.datasource.NewsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideNewsDataSource(retrofit: Retrofit): NewsDataSource =
        retrofit.create(NewsDataSource::class.java)
}