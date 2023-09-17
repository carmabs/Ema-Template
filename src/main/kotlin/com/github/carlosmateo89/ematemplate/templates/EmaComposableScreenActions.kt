package com.github.carlosmateo89.ematemplate.templates

fun addActions(
    packageName: String,
    featureName: String
): String {
    return """package $packageName

import com.carmabs.ema.core.action.EmaAction

sealed interface ${featureName}Actions : EmaAction {

    ///Replace with your screen action. It can be an object or a data class if you need parameters. Ex
    ///object SampleAction : ${featureName}Actions
}
"""
}