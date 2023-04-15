package com.github.carlosmateo89.ematemplate.templates

fun addActions(
    packageName: String,
    featureName: String
): String {
    return """package $packageName

import com.carmabs.ema.core.action.EmaAction

interface ${featureName}Actions : EmaAction {

    ///Replace with yoour screen action. It can be an object or a data class if you need parameters
    object SampleAction : ${featureName}Actions()
}
"""
}