plugins {
    alias(libs.plugins.unpintrested.android.library)
    alias(libs.plugins.unpintrested.android.hilt)
}

android {
    namespace = "com.gulderbone.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
}