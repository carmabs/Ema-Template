package com.github.carlosmateo89.ematemplate.templates

fun addViewModel(
    packageName: String,
    featureName: String
): String {
    return """package $packageName

import com.carmabs.ema.core.initializer.EmaInitializer
import com.carmabs.ema.core.viewmodel.EmaViewModel

class ${featureName}ViewModel: EmaViewModel<${featureName}State,${featureName}Destination>(){
	
	override suspend fun onCreateState(initializer: EmaInitializer?): ${featureName}State {
        return ${featureName}State()
    }
   
}
"""
}