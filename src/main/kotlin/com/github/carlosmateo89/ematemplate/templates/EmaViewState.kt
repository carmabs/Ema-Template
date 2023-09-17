package com.github.carlosmateo89.ematemplate.templates

fun addViewState(packageName: String,
                 featureName: String
) = """package $packageName

import com.carmabs.ema.core.state.EmaDataState

data class ${featureName}State(
    //Replace sampleValue with your state values
    val sampleValue: Boolean
): EmaDataState{

    companion object {
        val DEFAULT = ${featureName}State(sampleValue = true)
    }
    
}

"""