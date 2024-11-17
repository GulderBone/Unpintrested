plugins {
    alias(libs.plugins.unpintrested.android.library)
    alias(libs.plugins.unpintrested.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.gulderbone.core.data"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}