package com.github.carlosmateo89.ematemplate.config

import com.android.tools.idea.wizard.template.*

/**
 * Created by Carlos Mateo Benito on 2022
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved
 * </p>
 *
 * @author <a href="mailto: cmateo.benito@atsistemas.com">Carlos Mateo Benito</a>
 */

val emaSetupFragmentTemplate
    get() = template {
        name = "EMA fragment architecture"
        description =
            "Creates a new EMA fragment feature with the following classes - ViewModel, Fragment, Navigator and State"
        minApi = 21
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule
        )

        val featureName = stringParameter {
            name = "Feature Name"
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
            default = ""
            help = "The name of the feature that requires EMA views"
        }

        val layoutBinding = stringParameter {
            name = "Layout binding for fragment"
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${featureName.value}_fragment" }
            help = "The binding layout generated class to represent the fragment view"
        }

        val hasNavigator = booleanParameter {
            name = "Create Navigator"
            default = true
            help = "Set true to use a defined ema navigator. False if screen has no navigation"
        }

        val addActivity = booleanParameter {
            name = "Add an activity container for fragment"
            default = false
            help = "Add an activity that acts as container"
        }

        val hasToolbar = booleanParameter {
            name = "Create the activity with toolbar support"
            default = false
            visible = { addActivity.value }
            help = "Set true if the activity has toolbar support. False if is a normal activity"
        }

        val navigationActivityGraph = stringParameter {
            name = "Navigation graph for container activity"
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${featureName.value}_graph" }
            visible = { addActivity.value }
            help = "The activity navigation graph to define the destinations"
        }

        widgets(
            TextFieldWidget(featureName),
            TextFieldWidget(layoutBinding),
            CheckBoxWidget(hasNavigator),
            CheckBoxWidget(addActivity),
            CheckBoxWidget(hasToolbar),
            TextFieldWidget(navigationActivityGraph),
        )

        recipe = { data: TemplateData ->
            emaRecipeFragmentSetup(
                moduleData = data as ModuleTemplateData,
                packageName = data.packageName,
                featureName = featureName.value,
                layoutBinding = layoutBinding.value,
                addActivityContainer = addActivity.value,
                hasNavigator = hasNavigator.value,
                navigationGraph = navigationActivityGraph.value,
                isToolbarActivity = hasToolbar.value
            )
        }
    }
