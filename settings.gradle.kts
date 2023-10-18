pluginManagement {
    repositories {
        google()
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

rootProject.name = "GithubApp"
include(":app")
include(":core")
include(":core:common")

include(":feature")

include(":feature:github")
include(":core:data")
include(":core:domain")
