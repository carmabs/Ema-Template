package com.github.carlosmateo89.ematemplate.templates

fun addViewState(packageName: String,
                 featureName: String
) = """package $packageName

import com.carmabs.ema.core.state.EmaDataState

data class ${featureName}State(val defaultValue: Boolean = true): EmaDataState

"""