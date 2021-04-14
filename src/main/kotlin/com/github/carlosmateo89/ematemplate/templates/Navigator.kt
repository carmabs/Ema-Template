package com.github.carlosmateo89.ematemplate.templates

fun addNavigator(packageName: String,
                 featureName: String
) = """package $packageName

import com.carmabs.ema.core.navigator.EmaNavigator
import com.carmabs.ema.core.navigator.EmaNavigationState

interface ${featureName}Navigator : EmaNavigator<${featureName}Navigator.Navigation> {

    sealed class Navigation : EmaNavigationState {

        object Destination : ${featureName}Navigator.Navigation() {
            override fun navigateWith(navigator: EmaNavigator<out EmaNavigationState>) {
              (navigator as? ${featureName}Navigator)?.toDestination()
            }
        }
    }

    fun toDestination() {

    }
}
"""