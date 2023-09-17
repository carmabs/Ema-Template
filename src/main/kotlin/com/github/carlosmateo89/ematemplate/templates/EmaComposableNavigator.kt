package com.github.carlosmateo89.ematemplate.templates

fun addComposableNavigator(
    packageName: String,
    featureName: String
): String {
    return """package $packageName

import android.content.Context
import androidx.navigation.NavController
import com.carmabs.ema.compose.navigation.EmaComposableNavigator

class ${featureName}Navigator(
    context: Context,
    navController: NavController
) : EmaComposableNavigator(
    context = context,
    navController = navController
) {
   //Handle navigation events of ComposableScreen
}
"""
}