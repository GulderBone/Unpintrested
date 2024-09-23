plugins {
    alias(libs.plugins.unpintrested.android.feature.ui)
}

android {
    namespace = "com.gulderbone.auth.presentation"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}