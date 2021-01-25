package co.reign.core.base

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.reign.core.base.epoxy.MvRxEpoxyController
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.MavericksView

abstract class BaseMvRxEpoxyFragment(@LayoutRes containerLayoutId: Int = 0) : Fragment(containerLayoutId),
    MavericksView {

    protected val epoxyController by lazy { epoxyController() }

    abstract fun epoxyController(): MvRxEpoxyController

    abstract fun initUi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        epoxyController.onRestoreInstanceState(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        epoxyController.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        epoxyController.cancelPendingModelBuild()
        super.onDestroyView()
    }

    protected fun navigateTo(@IdRes actionId: Int, arg: Parcelable? = null) {
        val bundle = arg?.let { Bundle().apply { putParcelable(Mavericks.KEY_ARG, it) } }
        findNavController().navigate(actionId, bundle)
    }
}