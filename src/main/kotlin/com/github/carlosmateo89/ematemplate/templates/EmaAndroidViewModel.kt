package com.github.carlosmateo89.ematemplate.templates

fun addAndroidViewModel(
        packageName: String,
        featureName: String
) = """package $packageName

import com.carmabs.ema.android.viewmodel.EmaAndroidViewModel

class ${featureName}AndroidViewModel(viewModel: ${featureName}ViewModel) :
    EmaAndroidViewModel(viewModel)

"""