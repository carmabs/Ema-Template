package com.github.carlosmateo89.ematemplate.config

import com.android.tools.idea.wizard.template.*
import org.jetbrains.android.dom.manifest.getPackageName

/**
 * Created by Carlos Mateo Benito on 4/13/21
 *
 * <p>
 * Copyright (c) 2021 by AtSistemas. All rights reserved
 * </p>
 *
 * @author <a href="mailto: cmateo.benito@atsistemas.com">Carlos Mateo Benito</a>
 */

val emaSetupTemplate
    get() = template {
        revision = 1
        name = "EMA feature"
        description = "Creates a new EMA feature with the following classes - ViewModel, Activity/Fragment, Navigator  and State"
        minApi = 21
        minBuildApi = 21
        category = Category.Other// Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
                WizardUiContext.NewProject, WizardUiContext.NewModule)

        val featureName = stringParameter {
            name = "Feature Name"
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
            default = ""
            help = "The name of the feature that requires EMA views"
        }

        val useCreatedNavigator = booleanParameter {
            name = "Use created Navigator"
            default = true
            help = "Set true to use a defined ema navigator. False to create a new one for the feature"
        }

        val navigator = stringParameter {
            name = "Navigator Name"
            constraints = listOf(Constraint.NONEMPTY, Constraint.CLASS)
            visible = { useCreatedNavigator.value }
            default = ""
            help = "Add the navigator class name for functionality navigation"
        }

        val addActivity = booleanParameter {
            name = "Add activity to functionality"
            default = false
            help = "Add activity container to EMA View"
        }

        widgets(
                TextFieldWidget(featureName),
                TextFieldWidget(navigator),
                CheckBoxWidget(useCreatedNavigator),
                CheckBoxWidget(addActivity)
        )

        recipe = { data: TemplateData ->
            emaRecipeSetup(
                    moduleData = data as ModuleTemplateData,
                    packageName = data.packageName,
                    featureName = featureName.value,
                    addActivity = addActivity.value,
                    navigatorName = navigator.value
            )
        }
    }

