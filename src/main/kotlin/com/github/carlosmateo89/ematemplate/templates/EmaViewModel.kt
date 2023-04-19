package com.github.carlosmateo89.ematemplate.templates

fun addViewModel(
    packageName: String,
    featureName: String,
    hasNavigation: Boolean
): String {
    val imports = if (hasNavigation) {
        "import com.bimbaylola.architecture_core.initializer.ArcInitializer\n" +
                "import com.bimbaylola.architecture_core.viewmodel.ArcViewModel"
    } else {
        "import com.bimbaylola.architecture_core.initializer.ArcInitializer\n" +
                "import com.bimbaylola.architecture_core.navigator.ArcEmptyDestination\n" +
                "import com.bimbaylola.architecture_core.viewmodel.ArcViewModel"
    }
    val navigation = if (hasNavigation) {
        "${featureName}Destination"
    } else {
        "ArcEmptyDestination"
    }
    return """package $packageName

$imports

class ${featureName}ViewModel: ArcViewModel<${featureName}State,$navigation>(){
	
	override suspend fun onCreateState(initializer: ArcInitializer?): ${featureName}State {
        return ${featureName}State()
    }
   
}
"""
}