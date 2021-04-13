package com.github.carlosmateo89.ematemplate.templates

fun addViewActivity(
        packageName: String,
        featureName: String,
        navigatorName: String?
): String {
    val navigator = if (navigatorName.isNullOrEmpty()) "${featureName}Navigator" else navigatorName
    return """package $packageName

import com.carmabs.ema.core.navigator.EmaNavigator
import com.carmabs.ema.android.ui.EmaActivity
import com.carmabs.ema.core.state.EmaExtraData

import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class ${featureName}Activity : EmaActivity<${featureName}State, ${featureName}ViewModel, ${navigator}.Navigation>() {

	override fun injectActivityModule(kodein: Kodein.MainBuilder): Kodein.Module? = null

	override fun provideFixedToolbarTitle(): String? = null

    override val androidViewModelSeed: ${featureName}AndroidViewModel by instance()

    override val navigator: EmaNavigator<${navigator}.Navigation> by instance<${navigator}>()

    override fun onStateNormal(data: ${featureName}State) {

    }

    override fun onStateAlternative(data: EmaExtraData) {

    }

    override fun onStateError(error: Throwable) {

    }

    override fun onSingleEvent(data: EmaExtraData) {

    }

    override val navGraph : Int = R.navigation.graphID
}
"""
}