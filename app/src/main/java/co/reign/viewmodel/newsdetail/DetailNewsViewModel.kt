package co.reign.viewmodel.newsdetail

import co.reign.core.viewmodel.MvRxViewModel
import co.reign.viewmodel.newsdetail.state.DetailsNewsState

class DetailNewsViewModel constructor(initialState: DetailsNewsState) : MvRxViewModel<DetailsNewsState>(initialState)