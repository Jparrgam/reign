@file:Suppress("unused")

package co.reign.core.base

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.MavericksView

abstract class BaseMvRxFragment(@LayoutRes containerLayoutId: Int = 0): Fragment(containerLayoutId),
    MavericksView {

    abstract fun initUi()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    protected fun navigateTo(@IdRes actionId: Int, arg: Parcelable? = null) {
        val bundle = arg?.let { Bundle().apply { putParcelable(Mavericks.KEY_ARG, it) } }
        findNavController().navigate(actionId, bundle)
    }
}