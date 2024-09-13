plugins {
    alias(libs.plugins.unpintrested.android.feature.ui)
}

android {
    namespace = "com.gulderbone.pin.presentation"
}

dependencies {

    implementation(projects.pin.domain)
}