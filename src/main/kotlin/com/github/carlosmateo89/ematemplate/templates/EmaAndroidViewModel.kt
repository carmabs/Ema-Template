package com.github.carlosmateo89.ematemplate.templates

fun addAndroidViewModel(
        packageName: String,
        featureName: String
) = """package $packageName

import com.bimbaylola.architecture_android.viewmodel.ArcAndroidViewModel

class ${featureName}AndroidViewModel(viewModel: ${featureName}ViewModel) :
    ArcAndroidViewModel(viewModel)

"""