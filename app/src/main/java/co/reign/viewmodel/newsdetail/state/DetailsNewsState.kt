package co.reign.viewmodel.newsdetail.state

import co.reign.model.NewsItem
import co.reign.viewmodel.newsdetail.args.DetailNewsArgs
import com.airbnb.mvrx.MavericksState

data class DetailsNewsState(
    val movie: NewsItem
): MavericksState {
    constructor(args: DetailNewsArgs) : this(movie = args.movie)
}