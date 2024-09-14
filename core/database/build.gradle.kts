plugins {
    alias(libs.plugins.unpintrested.android.library)
    alias(libs.plugins.unpintrested.android.room)
    alias(libs.plugins.unpintrested.android.hilt)
}

android {
    namespace = "com.gulderbone.core.database"
}

dependencies {
    implementation(projects.core.domain)
}