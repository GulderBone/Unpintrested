plugins {
    alias(libs.plugins.unpintrested.android.library)
    alias(libs.plugins.unpintrested.android.hilt)
}

android {
    namespace = "com.gulderbone.pin.data"
}

dependencies {

    implementation(projects.pin.domain)
}