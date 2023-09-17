package com.github.carlosmateo89.ematemplate.templates

fun addAndroidViewModel(
        packageName: String,
        featureName: String,
        hasNavigationEvents: Boolean
) : String {
        val imports = if(hasNavigationEvents){
                ""
        }
        else{
                "import com.carmabs.ema.core.navigator.EmaEmptyNavigationEvent"
        }
        val navigationEvent = if(hasNavigationEvents){
                "${featureName}NavigationEvent"
        }else{
                "EmaEmptyNavigationEvent"
        }
        return """package $packageName

import com.carmabs.ema.android.viewmodel.EmaAndroidViewModel
$imports

class ${featureName}AndroidViewModel(viewModel: ${featureName}ViewModel) :
    EmaAndroidViewModel<${featureName}State,$navigationEvent>(viewModel)

"""
}