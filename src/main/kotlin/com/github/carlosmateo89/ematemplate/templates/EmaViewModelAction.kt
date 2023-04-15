package com.github.carlosmateo89.ematemplate.templates

fun addViewModelAction(
    packageName: String,
    featureName: String,
    hasNavigation: Boolean
): String {
    val imports = if (hasNavigation) {
        "import com.carmabs.ema.core.initializer.EmaInitializer\n" +
                "import com.carmabs.ema.core.viewmodel.EmaViewModelAction"
    } else {
        "import com.carmabs.ema.core.initializer.EmaInitializer\n" +
                "import com.carmabs.ema.core.navigator.EmaEmptyDestination\n" +
                "import com.carmabs.ema.core.viewmodel.EmaViewModelAction"
    }
    val navigation = if (hasNavigation) {
        "${featureName}Destination"
    } else {
        "EmaEmptyDestination"
    }
    return """package $packageName

$imports

class ${featureName}ViewModel: EmaViewModelAction<${featureName}Action,${featureName}State,$navigation>(){
	
	override suspend fun onCreateState(initializer: EmaInitializer?): ${featureName}State {
        return ${featureName}State()
    }
   
}
"""
}