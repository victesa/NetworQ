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
        maven{
            url = uri("https://repo1.maven.org/maven2/")
        }
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
include(":data")
include(":modules-sources")
include(":modules-sources:local")
include(":modules-features:cards")
