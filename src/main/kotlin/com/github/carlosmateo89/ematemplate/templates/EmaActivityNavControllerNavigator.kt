package com.github.carlosmateo89.ematemplate.templates

fun addActivityNavigator(
    packageName: String,
    featureName: String
) = """package $packageName

import android.app.Activity
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import com.bimbaylola.architecture_android.navigation.ArcActivityNavControllerNavigator

class ${featureName}Navigator(
    activity: Activity,
    @IdRes navHostId: Int,
    @NavigationRes graphId: Int
) : ArcActivityNavControllerNavigator<${featureName}Destination>(
    activity = activity,
    navHostId = navHostId,
    graphId = graphId
) {

    override fun navigate(navigationTarget: ${featureName}Destination){
    
    }
}
"""