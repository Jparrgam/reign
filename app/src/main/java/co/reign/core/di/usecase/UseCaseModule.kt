package co.reign.core.di.usecase

import co.reign.repository.NewsRepository
import co.reign.usecase.DeleteNewsUseCase
import co.reign.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun providerGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase =
        GetNewsUseCase(newsRepository)

    @Provides
    @ViewModelScoped
    fun providerDeleteNewsUseCase(newsRepository: NewsRepository): DeleteNewsUseCase =
        DeleteNewsUseCase(newsRepository)
}