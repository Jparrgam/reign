package co.reign.core.di.viewmodel

import co.reign.core.viewmodel.AssistedViewModelFactory
import co.reign.core.viewmodel.MavericksViewModelComponent
import co.reign.core.viewmodel.ViewModelKey
import co.reign.viewmodel.news.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    fun newsViewModelFactory(factory: NewsViewModel.Factory): AssistedViewModelFactory<*, *>
}