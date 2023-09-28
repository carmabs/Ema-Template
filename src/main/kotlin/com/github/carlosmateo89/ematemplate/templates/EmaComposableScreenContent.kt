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
        "${featureName}Action"
    }
    else{
        "ArcActionEmpty"
    }
    return """package $packageName


import androidx.compose.runtime.Composable
import com.bimbaylola.presentation.base.BaseComposableScreenContent
import com.bimbaylola.architecture_compose.action.ArcImmutableActionDispatcher
import com.bimbaylola.architecture_compose.action.ArcImmutableActionDispatcherEmpty
import com.bimbaylola.domainandroid.theme.Bimbaylola2020droidTheme
import com.bimbaylola.domainandroid.preview.PreviewDevices
$imports

class ${featureName}ScreenContent :
    BaseComposableScreenContent<${featureName}State, ${actions}>() {

    @Composable
    override fun onNormal(
        state: ${featureName}State,
        actions: ArcImmutableActionDispatcher<$actions>
        ){
    
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //region Previews for $featureName
    
    @PreviewDevices
    @Composable
    private fun Preview${featureName}ScreenContent() {
        Bimbaylola2020droidTheme {
            onStateNormal(
                state = ${featureName}State.DEFAULT,
                actions = ArcImmutableActionDispatcherEmpty()
            )
        }
    }
    
    //endregion

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
"""
}