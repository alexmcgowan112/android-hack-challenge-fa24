plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.example.a6starter"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.a6starter"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.retrofit)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.kotlinx.serialization.json)
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")
    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation ("androidx.compose.material:material:1.5.0")  // For PullRefresh integration


}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}