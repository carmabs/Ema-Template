package com.github.carlosmateo89.ematemplate.templates

fun addViewState(packageName: String,
                 featureName: String
) = """package $packageName

import com.bimbaylola.architecture_core.state.ArcDataState

data class ${featureName}State(
    //Replace sampleValue with your state values
    val sampleValue: Boolean
): ArcDataState{

    companion object {
        val DEFAULT = ${featureName}State(sampleValue = true)
    }

}

"""