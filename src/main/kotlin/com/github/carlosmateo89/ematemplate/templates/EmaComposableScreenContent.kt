package com.github.carlosmateo89.ematemplate.templates

fun addComposableScreenContent(
    packageName: String,
    featureName: String,
    hasActions:Boolean
): String {

    val imports = if(hasActions){
        ""
    }else{
        "import com.carmabs.ema.android.compose.action.EmaComposableScreenActionsEmpty"
    }

    val actions = if(hasActions){
        "${featureName}ScreenActions"
    }
    else{
        "EmaComposableScreenActionsEmpty"
    }

    return """package $packageName

import androidx.compose.runtime.Composable
import com.carmabs.ema.android.compose.ui.EmaComposableScreenContent
$imports

class ${featureName}ScreenContent :
    EmaComposableScreenContent<${featureName}State, ${actions}> {

    @Composable
    override fun onStateNormal(
        state: ${featureName}State,
        actions: ${featureName}ScreenActions
        ){
    
    }

}
"""
}