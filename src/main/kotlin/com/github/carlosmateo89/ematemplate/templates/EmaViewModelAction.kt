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
                "import com.carmabs.ema.core.navigator.EmaEmptyNavigationEvent\n" +
                "import com.carmabs.ema.core.viewmodel.EmaViewModelAction"
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
): EmaViewModelAction<${featureName}State, $navigation, ${featureName}Action>(
    initialState
) {
	
	override fun onStateCreated(initializer: EmaInitializer?) {
        
    }
    
    override fun onViewAction(action: ${featureName}Action) {
       /*
       when(action){
           
       }
       */
    }
      
}
"""
}