package com.github.carlosmateo89.ematemplate.templates

fun addComposableScreenContent(
    packageName: String,
    featureName: String,
    hasActions:Boolean
): String {

    val imports = if(hasActions){
        ""
    }else{
        "import com.carmabs.ema.core.action.EmaActionEmpty"
    }

    val actions = if(hasActions){
        "${featureName}Actions"
    }
    else{
        "EmaActionEmpty"
    }
    return """package $packageName


import androidx.compose.runtime.Composable
import com.carmabs.ema.android.compose.ui.EmaComposableScreenContent
import com.carmabs.ema.core.action.EmaActionDispatcher
$imports

class ${featureName}ScreenContent :
    EmaComposableScreenContent<${featureName}State, ${actions}> {

    @Composable
    override fun onStateNormal(
        state: ${featureName}State,
        actions: EmaActionDispatcher<$actions>
        ){
    
    }

}
"""
}