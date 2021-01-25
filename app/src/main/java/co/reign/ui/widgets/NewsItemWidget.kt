package co.reign.ui.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import co.reign.R
import co.reign.common.formatDate
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class NewsItemWidget @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val newsInformationTitle: TextView by lazy { findViewById(R.id.newsTitle) }
    private val newsAdditionalInformation: TextView by lazy { findViewById(R.id.newsAdditionalInformation) }
    private val containerNewsItem: ConstraintLayout by lazy { findViewById(R.id.containerNewsItem) }

    init {
        inflate(context, R.layout.news_row_widget, this)
        orientation = VERTICAL
    }

    var clickListener: OnClickListener? = null
        @CallbackProp set

    @ModelProp lateinit var author: String

    @ModelProp lateinit var createdAt: String

    @ModelProp
    fun setNewsTitle(newsTitle: String?) {
        newsInformationTitle.text = newsTitle
                ?: context.getString(R.string.news_item_title_not_found)
    }

    @SuppressLint("SetTextI18n")
    @AfterPropsSet
    fun useValueProps() {
        containerNewsItem.setOnClickListener(clickListener)
        newsAdditionalInformation.text = "$author - ${createdAt.formatDate()}"
    }
}