plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}
apply(from = "$rootDir/plugins/android-build.gradle")

android {
    namespace = "${Config.NAMESPACE}.sample"

    defaultConfig {
        applicationId = "${Config.APPLICATION_ID}.sample"
        versionCode = Config.versionCode
        versionName = Config.versionName

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(Modules.tourtip))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
