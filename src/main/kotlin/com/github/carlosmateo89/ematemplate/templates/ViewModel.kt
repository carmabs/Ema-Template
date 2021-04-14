package com.github.carlosmateo89.ematemplate.templates

fun addViewModel(packageName: String,
                 featureName: String,
                 navigatorName: String?
): String {
    val navigator = if (navigatorName.isNullOrEmpty()) "${featureName}Navigator" else navigatorName
    return """package $packageName

import com.carmabs.ema.core.viewmodel.EmaViewModel


class ${featureName}ViewModel: EmaViewModel<${featureName}State,$navigator.Navigation>(){
	
	override fun onStartFirstTime(statePreloaded: Boolean) {
    
    }

    override fun onResume(firstTime:Boolean){

    }

   override val initialViewState: ${featureName}State = ${featureName}State()
   
}
"""
}