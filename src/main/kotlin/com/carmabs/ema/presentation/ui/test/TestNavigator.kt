package com.carmabs.ema.presentation.ui.test

import com.carmabs.ema.core.navigator.EmaNavigator
import com.carmabs.ema.core.navigator.EmaNavigationState

interface TestNavigator : EmaNavigator<TestNavigator.Navigation> {

    sealed class Navigation : EmaNavigationState {

        object Destination : TestNavigator.Navigation() {
            override fun navigateWith(navigator: EmaNavigator<out EmaNavigationState>) {
              (navigator as? TestNavigator)?.toDestination()
            }
        }
    }

    fun toDestination() {

    }
}
