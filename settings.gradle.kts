pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NetworQ"
include(":app")
include(":modules-ui")
include(":modules-ui:common")
include(":modules-ui:design")
include(":modules-ui:resources")
include(":core")
include(":core:utils")
include(":modules-features")
include(":modules-features:add-event")
include(":domain")
