package com.carmabs.ema.presentation.ui.test

import com.carmabs.ema.android.ui.EmaFragment
import com.carmabs.ema.core.navigator.EmaNavigator
import com.carmabs.ema.core.state.EmaExtraData

import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class TestFragment : EmaFragment<TestState, TestViewModel,TestNavigator.Navigation>() {

    override fun injectFragmentModule(kodein: Kodein.MainBuilder): Kodein.Module? = null

    override val fragmentViewModelScope: Boolean = true

    override val androidViewModelSeed: TestAndroidViewModel by instance()

    override val navigator: EmaNavigator<TestNavigator.Navigation> by instance<TestNavigator>()

    override fun onStateNormal(data: TestState) {

    }

    override fun onStateAlternative(data: EmaExtraData) {

    }

    override fun onSingleEvent(data: EmaExtraData) {

    }

    override fun onStateError(error: Throwable) {

    }

    override val layoutId: Int = R.layout.fragmentID

}
