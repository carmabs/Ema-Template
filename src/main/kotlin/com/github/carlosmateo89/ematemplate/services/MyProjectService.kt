package com.github.carlosmateo89.ematemplate.services

import com.github.carlosmateo89.ematemplate.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
