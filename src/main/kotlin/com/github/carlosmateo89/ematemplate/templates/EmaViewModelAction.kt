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
        "${featureName}NavigationEvent"
    } else {
        "ArcEmptyNavigationEvent"
    }
    return """package $packageName

$imports

class ${featureName}ViewModel(
    initialState: ${featureName}State
): BaseViewModel<${featureName}Action, ${featureName}State, $navigation>(initialState){
	
	override fun onStateCreated(initializer: ArcInitializer?) {
        
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