plugins {
    alias(libs.plugins.unpintrested.android.feature.ui)
}

android {
    namespace = "com.gulderbone.pin.presentation"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.pin.domain)
}