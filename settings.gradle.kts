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

rootProject.name = "Job Search TW"
include(":app")
include(":core")
include(":favorites")
include(":main")
include(":messages")
include(":profile")
include(":responses")
include(":search")
include(":uikit")
include(":base")
include(":network")
include(":common")
