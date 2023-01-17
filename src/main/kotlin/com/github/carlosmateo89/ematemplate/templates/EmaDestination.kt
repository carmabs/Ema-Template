package com.github.carlosmateo89.ematemplate.templates

fun addDestination(packageName: String,
                 featureName: String
) = """package $packageName

import com.carmabs.ema.core.navigator.EmaDestination

sealed class ${featureName}Destination : EmaDestination() {
    object SampleDestination : ${featureName}Destination()
}
"""