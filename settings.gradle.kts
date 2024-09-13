pluginManagement {
    includeBuild("build-logic")
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

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "Unpintrested"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":pin:domain")
include(":pin:presentation")
include(":pin:data")
include(":core:presentation:designsystem")
