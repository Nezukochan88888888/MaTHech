plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mathech"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mathech"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            // Enable R8 / ProGuard for release
            isMinifyEnabled = true
            isShrinkResources = true

            // Use ProGuard rules + default optimization
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            // Keep minification off for easier debugging
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.drawerlayout)
    implementation(libs.appcompat)        // version catalog reference
    implementation(libs.material)        // version catalog reference
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
