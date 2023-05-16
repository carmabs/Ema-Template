package com.github.carlosmateo89.ematemplate.templates

fun addComposableNavigator(
    packageName: String,
    featureName: String
): String {
    return """package $packageName

import android.content.Context
import androidx.navigation.NavController
import com.bimbaylola.architecture_compose.navigation.ArcComposableNavigator

class ${featureName}Navigator(
    context: Context,
    navController: NavController
) : ArcComposableNavigator(
    context = context,
    navController = navController
) {
   //Handle navigation events of ComposableScreen
}
"""
}