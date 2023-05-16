package com.github.carlosmateo89.ematemplate.templates

fun addDestination(packageName: String,
                 featureName: String
) = """package $packageName

import com.bimbaylola.architecture_core.navigator.ArcNavigationEvent

sealed class ${featureName}NavigationEvent : ArcNavigationEvent() {

    ///Add your navigation event. It can be an object or a data class if you need parameters. Ex:
    ///object SampleNavigationEvent : ${featureName}NavigationEvent()
}
"""