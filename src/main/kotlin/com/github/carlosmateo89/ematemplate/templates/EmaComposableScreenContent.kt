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
import com.carmabs.ema.compose.ui.EmaComposableScreenContent
import com.carmabs.ema.compose.action.EmaImmutableActionDispatcher
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
$imports

class ${featureName}ScreenContent :
    EmaComposableScreenContent<${featureName}State, ${actions}> {

    @Composable
    override fun onStateNormal(
        state: ${featureName}State,
        actions: EmaImmutableActionDispatcher<$actions>
    ) {
    
    }
    
    @Preview(showBackground = true, device = Devices.DEFAULT)
    @Composable
    private fun Preview${featureName}ScreenContent {
            onStateNormal(
                state = ${featureName}State.DEFAULT,
                actions = EmaImmutableActionDispatcherEmpty()
            )
    }

}
"""
}