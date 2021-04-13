package com.github.carlosmateo89.ematemplate.templates

fun addViewFragment(
        packageName: String,
        featureName: String,
        addActivity:Boolean,
        navigatorName: String?
): String {
    val navigator = if (navigatorName.isNullOrEmpty()) "${featureName}Navigator" else navigatorName
    //This is to define if viewmodel will have activity scope or fragment scope
    val fragmentScope = if(addActivity) "false" else "true"
    return """package $packageName

import com.carmabs.ema.android.ui.EmaFragment
import com.carmabs.ema.core.navigator.EmaNavigator
import com.carmabs.ema.core.state.EmaExtraData

import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class ${featureName}Fragment : EmaFragment<${featureName}State, ${featureName}ViewModel,${navigator}.Navigation>() {

    override fun injectFragmentModule(kodein: Kodein.MainBuilder): Kodein.Module? = null

    override val fragmentViewModelScope: Boolean = $fragmentScope

    override val androidViewModelSeed: ${featureName}AndroidViewModel by instance()

    override val navigator: EmaNavigator<${navigator}.Navigation> by instance<${navigator}>()

    override fun onStateNormal(data: ${featureName}State) {

    }

    override fun onStateAlternative(data: EmaExtraData) {

    }

    override fun onSingleEvent(data: EmaExtraData) {

    }

    override fun onStateError(error: Throwable) {

    }

    override val layoutId: Int = R.layout.fragmentID

}
"""
}