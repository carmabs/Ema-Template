package com.github.carlosmateo89.ematemplate.templates

import android.databinding.tool.ext.toCamelCase

fun addViewFragment(
    packageName: String,
    modulePackageName:String?,
    featureName: String,
    layoutBinding:String,
    hasNavigator: Boolean
): String {
    val layoutBindingName = "${layoutBinding.toCamelCase().replaceFirstChar { it.uppercaseChar() }}Binding"
    val rImports = modulePackageName?.let {
         "import $it.databinding.$layoutBindingName"
    }?:""
    val navigator =
        if (hasNavigator) "override val navigator: EmaNavigator<${featureName}Destination> = ${featureName}Navigator(" +
                "this" +
                ")" else "override val navigator: EmaNavigator<EmaEmptyDestination>? = null"
    val navigatorName = if (hasNavigator) "${featureName}Destination" else "EmaEmptyDestination"
    val navigatorImport =
        if (hasNavigator) "" else "import com.carmabs.ema.core.navigator.EmaEmptyDestination"
    return """package $packageName

import android.view.LayoutInflater
import android.view.ViewGroup
import com.carmabs.ema.android.di.injectDirect
import com.carmabs.ema.android.ui.EmaFragment
import com.carmabs.ema.android.viewmodel.EmaAndroidViewModel
import com.carmabs.ema.core.navigator.EmaNavigator
$navigatorImport
$rImports

class ${featureName}Fragment :
    EmaFragment<${layoutBindingName},${featureName}State, ${featureName}ViewModel, $navigatorName>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): $layoutBindingName {
        return ${layoutBindingName}.inflate(inflater,container,false)
    }

    override fun provideAndroidViewModel(): EmaAndroidViewModel {
        return ${featureName}AndroidViewModel(injectDirect())
    }

    override fun ${layoutBindingName}.onStateNormal(data: ${featureName}State){
    
    }

    ${navigator}
}
"""
}