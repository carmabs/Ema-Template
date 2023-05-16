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
                "import com.bimbaylola.architecture_core.navigator.ArcEmptyNavigationEvent\n" +
                "import com.bimbaylola.architecture_core.viewmodel.ArcViewModel"
    }
    val navigation = if (hasNavigation) {
        "${featureName}NavigationEvent"
    } else {
        "ArcEmptyNavigationEvent"
    }
    return """package $packageName

$imports

class ${featureName}ViewModel(
    initialState: ${featureName}State
): ArcViewModel<${featureName}State,$navigation>(initialState){
	
	override fun onStateCreated(initializer: ArcInitializer?) {
   
    }
   
}
"""
}