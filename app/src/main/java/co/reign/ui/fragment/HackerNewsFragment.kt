package co.reign.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import co.reign.R
import co.reign.core.base.BaseMvRxEpoxyFragment
import co.reign.core.base.epoxy.simpleController
import co.reign.core.base.viewbinding.viewBinding
import co.reign.databinding.HackerNewsFragmentBinding
import co.reign.ui.widgets.headerTitleWidget
import co.reign.ui.widgets.loadingWidget
import co.reign.ui.widgets.newsItemWidget
import co.reign.viewmodel.news.NewsViewModel
import co.reign.viewmodel.news.state.NewsState
import co.reign.viewmodel.newsdetail.args.DetailNewsArgs
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel

class HackerNewsFragment : BaseMvRxEpoxyFragment(R.layout.hacker_news_fragment) {
    private val viewBinding: HackerNewsFragmentBinding by viewBinding()
    private val newViewModel: NewsViewModel by fragmentViewModel()

    override fun initUi() {
        initView()
        newViewModel.onAsync(NewsState::request, onFail = {
            newViewModel.fetchHackerNews()
        }, onSuccess = {
            invalidate()
        })
    }

    override fun invalidate() {
        viewBinding.rvcHackerNews.requestModelBuild()
    }

    override fun epoxyController() = simpleController(newViewModel) { state ->
        if (state.request is Success) {
            val data = state.request()
            headerTitleWidget {
                id("headerTitle")
                subTitle(getString(R.string.home_subtitle_count_news, data?.nbHits.toString()))
            }
        }

        state.news.forEach {
            newsItemWidget {
                id(it.newsID)
                newsTitle(it.storyTitle ?: it.title)
                author(it.author ?: getString(R.string.news_item_author_not_found))
                createdAt(it.createdAt)
                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
                clickListener { _ ->
                    navigateTo(R.id.action_hackerNewsFragment_to_hackerNewsDetailsFragment,
                        DetailNewsArgs(movie = it))
                }
            }
        }

        loadingWidget {
            visibility(state.request is Loading)
            id("loading${state.news.size}")
            onBind { _, _, _ -> newViewModel.fetchHackerNews() }
        }
    }

    private fun initView() {
        viewBinding.rvcHackerNews.layoutManager = LinearLayoutManager(context)
        viewBinding.rvcHackerNews.setController(epoxyController)
    }
}