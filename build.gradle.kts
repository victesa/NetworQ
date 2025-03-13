// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.ksp) apply false

}

allprojects{
    configurations.all{
        resolutionStrategy{
            force("org.jetbrains:annotations:23.0.0")
        }
    }
}

configurations.all {
    exclude(group = "com.intellij", module = "annotations")
}

