package com.github.carlosmateo89.ematemplate.templates

fun addComposableScreenActions(
    packageName: String,
    featureName: String
): String {
    return """package $packageName

import com.carmabs.ema.android.compose.action.EmaComposableScreenActions

interface ${featureName}ScreenActions : EmaComposableScreenActions {

}
"""
}