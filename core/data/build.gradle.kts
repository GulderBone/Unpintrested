plugins {
    alias(libs.plugins.unpintrested.android.library)
}

android {
    namespace = "com.gulderbone.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
}