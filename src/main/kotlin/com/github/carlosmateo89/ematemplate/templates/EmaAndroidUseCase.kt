package com.github.carlosmateo89.ematemplate.templates

fun addUseCase(
    packageName: String,
    featureName: String,
    output: String,
    hasInput: Boolean
): String {
    val usecaseName = "${featureName}UseCase"
    val dataInput = if (hasInput) {
        "data class Input(\n" +
                "                val default:Boolean //Replace with your required attributes\n" +
                "        )"
    } else {
        ""
    }
    val inputTemplate = if (hasInput) {
        "$usecaseName.Input"
    } else {
        "Unit"
    }

    val inputMethod = if (hasInput) {
        "Input"
    } else {
        "Unit"
    }

    return """package $packageName

import com.bimbaylola.architecture_core.usecase.ArcUseCase

class $usecaseName : ArcUseCase<$inputTemplate, $output>() {

        override suspend fun useCaseFunction(input: $inputMethod): $output {

        }

        $dataInput
}

"""
}