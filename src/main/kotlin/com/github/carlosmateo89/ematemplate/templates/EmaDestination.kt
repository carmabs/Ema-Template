package com.github.carlosmateo89.ematemplate.templates

fun addDestination(packageName: String,
                 featureName: String
) = """package $packageName

import com.bimbaylola.architecture_core.navigator.ArcDestination

sealed class ${featureName}Destination : ArcDestination() {

    ///Replace with your destination. It can be an object or a data class if you need parameters.
    object SampleDestination : ${featureName}Destination()
}
"""