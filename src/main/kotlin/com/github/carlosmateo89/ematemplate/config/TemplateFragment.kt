package com.github.carlosmateo89.ematemplate.config

import com.android.tools.idea.wizard.template.*
import org.jetbrains.kotlin.lombok.utils.capitalize

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
        name = "ARC fragment architecture"
        description = "Creates a new ARC fragment feature with the following classes - ViewModel, Fragment, Navigator and State"
        minApi = 23
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule
        )

        val featureName = stringParameter {
            name = "Feature Name"
            default = ""
            constraints = listOf(Constraint.NONEMPTY, Constraint.UNIQUE)
            help = "The name of the feature that requires ARC views"
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

        val layoutBinding = stringParameter {
            name = "Layout binding for fragment"
            default = ""
            suggest = { "${featureName.value.lowercase()}_fragment" }
            constraints = listOf(Constraint.NONEMPTY)
            help = "The binding layout generated class to represent the fragment view"
        }

        val layoutContainerBinding = stringParameter {
            name = "Layout binding for activity container"
            default = ""
            visible = { addActivity.value }
            suggest = { "${featureName.value.lowercase()}_activity" }
            constraints = listOf(Constraint.NONEMPTY)
            help = "The binding layout generated class to represent the container activity view"
        }

        val navigationActivityGraph = stringParameter {
            name = "Navigation graph for container activity"
            default = ""
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${featureName.value.lowercase()}_graph" }
            visible = { addActivity.value }
            help = "The activity navigation graph to define the destinations"
        }

        widgets(
            TextFieldWidget(featureName),
            TextFieldWidget(layoutBinding),
            CheckBoxWidget(hasNavigator),
            CheckBoxWidget(addActivity),
            TextFieldWidget(layoutContainerBinding),
            TextFieldWidget(navigationActivityGraph),
            CheckBoxWidget(hasToolbar)
            )

        recipe = { data: TemplateData ->
            emaRecipeFragmentSetup(
                moduleData = data as ModuleTemplateData,
                featureName = featureName.value.capitalize(),
                layoutBinding = layoutBinding.value,
                layoutContainerBinding = layoutContainerBinding.value,
                addActivityContainer = addActivity.value,
                hasNavigator = hasNavigator.value,
                navigationGraph = navigationActivityGraph.value,
                isToolbarActivity = hasToolbar.value
            )
        }
    }
