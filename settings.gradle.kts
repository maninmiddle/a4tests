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

rootProject.name = "a4tests"
include(":app")
include(":core")
include(":core:common")
include(":core:ui")
include(":features")
include(":features:home")
include(":features:tests_list")
include(":features:test_solve")
include(":features:test_result")
include(":features:test_create")
