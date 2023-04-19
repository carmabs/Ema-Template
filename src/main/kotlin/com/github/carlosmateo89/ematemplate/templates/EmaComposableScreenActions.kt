package com.github.carlosmateo89.ematemplate.templates

fun addActions(
    packageName: String,
    featureName: String
): String {
    return """package $packageName

import com.bimbaylola.architecture_core.action.ArcAction

sealed interface ${featureName}Actions : ArcAction {

    ///Replace with your screen action. It can be an object or a data class if you need parameters
    object SampleAction : ${featureName}Actions
}
"""
}