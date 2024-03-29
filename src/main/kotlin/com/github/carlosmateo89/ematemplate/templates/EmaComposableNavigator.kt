package com.github.carlosmateo89.ematemplate.templates

fun addComposableNavigator(
    packageName: String,
    featureName: String
): String {
    return """package $packageName

import androidx.activity.ComponentActivity
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.carmabs.ema.android.compose.navigation.EmaComposableNavigator

class ${featureName}Navigator(
    activity: ComponentActivity,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry
) : EmaComposableNavigator<${featureName}Destination>(
    activity = activity,
    navController = navController,
    navBackStackEntry = navBackStackEntry
) {
    override fun navigate(destination: ${featureName}Destination) {
       
    }
}
"""
}