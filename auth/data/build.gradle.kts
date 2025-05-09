plugins {
    alias(libs.plugins.unpintrested.android.library)
    alias(libs.plugins.unpintrested.android.hilt)
}

android {
    namespace = "com.gulderbone.auth.data"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)

    implementation(projects.core.data)
}