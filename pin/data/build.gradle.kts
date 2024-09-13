plugins {
    alias(libs.plugins.unpintrested.android.library)
}

android {
    namespace = "com.gulderbone.pin.data"
}

dependencies {

    implementation(projects.pin.domain)
}