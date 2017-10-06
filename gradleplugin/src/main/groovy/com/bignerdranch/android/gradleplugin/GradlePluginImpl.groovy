package com.bignerdranch.android.gradleplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class GradlePluginImpl implements Plugin<Project> {
    @Override
    void apply(Project project) {
        System.out.println("========================")
        System.out.println("hello gradle plugin!")
        System.out.println("========================")
        project.task('testTask').doLast {
            println("Hello, test Task with plugin!")
        }
    }
}