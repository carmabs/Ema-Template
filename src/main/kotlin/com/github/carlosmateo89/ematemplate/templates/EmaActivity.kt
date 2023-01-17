package com.github.carlosmateo89.ematemplate.templates

import android.databinding.tool.ext.toCamelCase

fun addViewActivity(
    packageName: String,
    modulePackageName: String?,
    featureName: String,
    layoutBinding: String,
    navigationGraph: String,
    hasNavigator: Boolean,
    hasToolbar: Boolean,
    isContainer: Boolean
): String {
    val layoutBindingName =
        "${layoutBinding.toCamelCase().replaceFirstChar { it.uppercaseChar() }}Binding"
    val rImports = modulePackageName?.let {
        var import = "import $it.databinding.$layoutBindingName"
        if (isContainer) {
            import += "\nimport $modulePackageName.R"
        }
    } ?: ""

    val navigationGraphName = "R.navigation.${navigationGraph}"
    val containerImports = if (isContainer) {
        "import com.carmabs.ema.android.viewmodel.EmaAndroidEmptyViewModel\n" +
                "import com.carmabs.ema.core.state.EmaEmptyState\n" +
                "import com.carmabs.ema.core.viewmodel.EmaEmptyViewModel"
    } else
        "import com.carmabs.ema.android.di.injectDirect"

    val viewmodel = if (isContainer) {
        "EmaEmptyViewModel"
    } else
        "${featureName}ViewModel"

    val androidViewModel = if (isContainer) {
        "EmaAndroidEmptyViewModel()"
    } else
        "${featureName}AndroidViewModel(injectDirect())"

    val state = if (isContainer) {
        "EmaEmptyState"
    } else
        "${featureName}State"

    val navigator =
        if (hasNavigator) "override val navigator: EmaNavigator<${featureName}Destination> = ${featureName}Navigator(\n" +
                "       this,\n" +
                "       R.id.navHostFragment,\n" +
                "       $navigationGraphName\n" +
                "   )" else "override val navigator: EmaNavigator<EmaEmptyDestination> = EmaActivityNavControllerHost(\n" +
                "       this,\n" +
                "       R.id.navHostFragment,\n" +
                "       $navigationGraphName\n" +
                "   )"
    val navigatorName = if (hasNavigator) "${featureName}Destination" else "EmaEmptyDestination"
    val activityImports = (if (hasToolbar) "import androidx.appcompat.widget.Toolbar\n" +
            "import com.carmabs.ema.android.ui.EmaToolbarActivity\n" +
            "import com.google.android.material.appbar.AppBarLayout"
    else
        "import com.carmabs.ema.android.ui.EmaActivity")


    val activityToolbar = if (hasToolbar) "EmaToolbarActivity" else "EmaActivity"
    val toolbar =
        if (hasToolbar) "\n    override fun ${layoutBindingName}.provideToolbar(): Toolbar {\n" +
                "       return tb$featureName\n" +
                "    }\n" +
                "\n" +
                "    override fun ${layoutBindingName}.provideToolbarLayout(): AppBarLayout {\n" +
                "       return abl$featureName\n" +
                "    }\n" else ""
    val navigatorImport =
        if (hasNavigator) "" else "import com.carmabs.ema.android.navigation.EmaActivityNavControllerHost\n" +
                "import com.carmabs.ema.core.navigator.EmaEmptyDestination"
    return """package $packageName

import android.view.LayoutInflater
$activityImports
$containerImports
import com.carmabs.ema.android.viewmodel.EmaAndroidViewModel
import com.carmabs.ema.core.navigator.EmaNavigator
$navigatorImport
$rImports

class ${featureName}Activity :
    ${activityToolbar}<${layoutBindingName},${state}, ${viewmodel}, $navigatorName>() {

    override fun createViewBinding(inflater: LayoutInflater): ${layoutBindingName} {
        return ${layoutBindingName}.inflate(inflater)
    }
    $toolbar
    override fun provideAndroidViewModel(): EmaAndroidViewModel {
        return $androidViewModel
    }

    override fun ${layoutBindingName}.onStateNormal(data: ${state}){
    
    }

    ${navigator}
}
"""
}