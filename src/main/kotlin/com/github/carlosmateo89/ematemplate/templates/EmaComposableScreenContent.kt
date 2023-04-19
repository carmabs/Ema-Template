package com.github.carlosmateo89.ematemplate.templates

fun addComposableScreenContent(
    packageName: String,
    featureName: String,
    hasActions:Boolean
): String {

    val imports = if(hasActions){
        ""
    }else{
        "import com.bimbaylola.architecture_core.action.ArcActionEmpty"
    }

    val actions = if(hasActions){
        "${featureName}Actions"
    }
    else{
        "ArcActionEmpty"
    }
    return """package $packageName


import androidx.compose.runtime.Composable
import com.bimbaylola.presentation.base.BaseComposableScreenContent
import com.bimbaylola.architecture_core.action.ArcActionDispatcher
$imports

class ${featureName}ScreenContent :
    BaseComposableScreenContent<${featureName}State, ${actions}>() {

    @Composable
    override fun onNormal(
        state: ${featureName}State,
        actions: ArcActionDispatcher<$actions>
        ){
    
    }
}
"""
}