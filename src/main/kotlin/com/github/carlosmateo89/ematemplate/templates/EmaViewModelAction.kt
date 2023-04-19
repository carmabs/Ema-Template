package com.github.carlosmateo89.ematemplate.templates

fun addViewModelAction(
    packageName: String,
    featureName: String,
    hasNavigation: Boolean
): String {
    val imports = if (hasNavigation) {
        "import com.bimbaylola.architecture_core.initializer.ArcInitializer\n" +
                "import com.bimbaylola.presentation.base.BaseViewModel"
    } else {
        "import com.bimbaylola.architecture_core.initializer.ArcInitializer\n" +
                "import com.bimbaylola.architecture_core.navigator.ArcEmptyDestination\n" +
                "import com.bimbaylola.presentation.base.BaseViewModel"
    }
    val navigation = if (hasNavigation) {
        "${featureName}Destination"
    } else {
        "ArcEmptyDestination"
    }
    return """package $packageName

$imports

class ${featureName}ViewModel: BaseViewModelAction<${featureName}Actions,${featureName}State,$navigation>(){
	
	override suspend fun onCreateState(initializer: ArcInitializer?): ${featureName}State {
        return ${featureName}State()
    }
    
    override fun onAction(action: ${featureName}Actions) {
       
    }
   
}
"""
}