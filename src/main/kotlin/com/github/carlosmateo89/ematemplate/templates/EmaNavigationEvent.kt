package com.github.carlosmateo89.ematemplate.templates

fun addNavigationEvent(packageName: String,
                       featureName: String
) = """package $packageName

import com.carmabs.ema.core.navigator.EmaNavigationEvent

sealed interface ${featureName}NavigationEvent : EmaNavigationEvent {

    ///Replace with your destination. It can be an object or a data class if you need parameters.
    //object SampleNavigationEvent : ${featureName}NavigationEvent
}
"""