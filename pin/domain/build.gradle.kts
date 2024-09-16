plugins {
    alias(libs.plugins.unpintrested.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}