plugins {
    `kotlin-dsl`
}

group = "com.gulderbone.unpintrested.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    compileOnly(libs.hilt.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "unpintrested.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "unpintrested.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "unpintrested.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "unpintrested.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("jvmLibrary") {
            id = "unpintrested.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("androidFeatureUi") {
            id = "unpintrested.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }
        register("androidHilt") {
            id = "unpintrested.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}