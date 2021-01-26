package co.reign.ui.fragment

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.navigation.ui.setupWithNavController
import co.reign.R
import co.reign.core.base.BaseMvRxFragment
import co.reign.core.base.viewbinding.viewBinding
import co.reign.databinding.HackerNewsDetailFragmentBinding
import androidx.navigation.fragment.findNavController
import co.reign.viewmodel.newsdetail.DetailNewsViewModel
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.skydoves.whatif.whatIfNotNullOrEmpty

class HackerNewsDetailFragment : BaseMvRxFragment(R.layout.hacker_news_detail_fragment) {

    private val viewBinding: HackerNewsDetailFragmentBinding by viewBinding()
    private val detailNewsViewModel: DetailNewsViewModel by fragmentViewModel()

    override fun initUi() {
        viewBinding.detailNewsToolbar.setupWithNavController(findNavController())
    }

    override fun invalidate() = withState(detailNewsViewModel) {
        viewBinding.detailNewsTitle.text = it.movie.storyTitle ?: it.movie.title

        it.movie.storyUrl.whatIfNotNullOrEmpty { url ->
            viewBinding.detailsNewsWebView.loadUrl(url)
        }

        viewBinding.detailsNewsWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                viewBinding.detailNewsProgress.isVisible = false
            }
        }
    }
}