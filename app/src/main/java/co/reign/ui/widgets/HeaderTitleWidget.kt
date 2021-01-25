package co.reign.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import co.reign.R
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HeaderTitleWidget @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val subTitle: TextView by lazy { findViewById(R.id.subTitle) }

    init {
        inflate(context, R.layout.title_header_widget, this)
        orientation = VERTICAL
    }

    @ModelProp
    fun subTitle(title: String) {
        subTitle.text = title
    }
}