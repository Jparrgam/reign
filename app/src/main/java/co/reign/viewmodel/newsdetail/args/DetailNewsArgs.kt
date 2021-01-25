package co.reign.viewmodel.newsdetail.args

import android.os.Parcelable
import co.reign.model.NewsItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailNewsArgs(val movie: NewsItem) : Parcelable
