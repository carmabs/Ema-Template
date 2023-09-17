package com.github.carlosmateo89.ematemplate.templates

fun addViewModel(
    packageName: String,
    featureName: String,
    hasNavigation: Boolean
): String {
    val imports = if (hasNavigation) {
        "import com.carmabs.ema.core.initializer.EmaInitializer\n" +
                "import com.carmabs.ema.core.viewmodel.EmaViewModel"
    } else {
        "import com.carmabs.ema.core.initializer.EmaInitializer\n" +
                "import com.carmabs.ema.core.navigator.EmaEmptyNavigationEvent\n" +
                "import com.carmabs.ema.core.viewmodel.EmaViewModel"
    }
    val navigation = if (hasNavigation) {
        "${featureName}NavigationEvent"
    } else {
        "EmaEmptyNavigationEvent"
    }
    return """package $packageName

$imports

class ${featureName}ViewModel(
    initialState: ${featureName}State
): EmaViewModel<${featureName}State,$navigation>(initialState){
	
	override fun onStateCreated(initializer: EmaInitializer?) {
        
    }
   
}
"""
}