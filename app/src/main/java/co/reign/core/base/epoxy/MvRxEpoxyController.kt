package co.reign.core.base.epoxy

import co.reign.core.base.BaseMvRxEpoxyFragment
import co.reign.core.viewmodel.MvRxViewModel
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.*

/**
 * For use with [BaseMvRxEpoxyFragment.epoxyController].
 *
 * This builds Epoxy models in a background thread.
 */
open class MvRxEpoxyController(
        val buildModelsCallback: EpoxyController.() -> Unit = {}
) : AsyncEpoxyController() {

    override fun buildModels() {
        buildModelsCallback()
    }
}


/**
 * Create a [MvRxEpoxyController] that builds models with the given callback.
 */
fun BaseMvRxEpoxyFragment.simpleController(
        buildModels: EpoxyController.() -> Unit
) = MvRxEpoxyController {
    // Models are built asynchronously, so it is possible that this is called after the fragment
    // is detached under certain race conditions.
    if (view == null || isRemoving) return@MvRxEpoxyController
    buildModels()
}

/**
 * Create a [MvRxEpoxyController] that builds models with the given callback.
 * When models are built the current state of the viewmodel will be provided.
 */
fun <S : MavericksState, A : MvRxViewModel<S>> BaseMvRxEpoxyFragment.simpleController(
        viewModel: A,
        buildModels: EpoxyController.(state: S) -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController
    withState(viewModel) { state ->
        buildModels(state)
    }
}

/**
 * Create a [MvRxEpoxyController] that builds models with the given callback.
 * When models are built the current state of the viewmodels will be provided.
 */
fun <A : MavericksViewModel<B>, B : MavericksState, C : MavericksViewModel<D>, D : MavericksState> BaseMvRxEpoxyFragment.simpleController(
        viewModel1: A,
        viewModel2: C,
        buildModels: EpoxyController.(state1: B, state2: D) -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController
    withState(viewModel1, viewModel2) { state1, state2 ->
        buildModels(state1, state2)
    }
}

/**
 * Create a [MvRxEpoxyController] that builds models with the given callback.
 * When models are built the current state of the viewmodels will be provided.
 */
fun <A : MavericksViewModel<B>, B : MavericksState, C : MavericksViewModel<D>, D : MavericksState, E : MavericksViewModel<F>, F : MavericksState> BaseMvRxEpoxyFragment.simpleController(
        viewModel1: A,
        viewModel2: C,
        viewModel3: E,
        buildModels: EpoxyController.(state1: B, state2: D, state3: F) -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController
    withState(viewModel1, viewModel2, viewModel3) { state1, state2, state3 ->
        buildModels(state1, state2, state3)
    }
}

