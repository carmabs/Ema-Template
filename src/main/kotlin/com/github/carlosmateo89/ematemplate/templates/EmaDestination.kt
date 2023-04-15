package com.github.carlosmateo89.ematemplate.templates

fun addDestination(packageName: String,
                 featureName: String
) = """package $packageName

import com.carmabs.ema.core.navigator.EmaDestination

sealed class ${featureName}Destination : EmaDestination() {

    ///Replace with your destination. It can be an object or a data class if you need parameters.
    object SampleDestination : ${featureName}Destination()
}
"""