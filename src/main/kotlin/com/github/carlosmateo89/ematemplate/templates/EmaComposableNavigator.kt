package com.github.carlosmateo89.ematemplate.templates

fun addComposableNavigator(
    packageName: String,
    featureName: String
): String {
    return """package $packageName

import androidx.activity.ComponentActivity
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.bimbaylola.architecture_compose.navigation.ArcComposableNavigator

class ${featureName}Navigator(
    activity: ComponentActivity,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry
) : ArcComposableNavigator<${featureName}Destination>(
    activity = activity,
    navController = navController,
    navBackStackEntry = navBackStackEntry
) {
    override fun navigate(destination: ${featureName}Destination) {
       
    }
}
"""
}