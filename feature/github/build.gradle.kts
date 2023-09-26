@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.season.winter.feature.github"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    dataBinding {
        enable = true
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":appcore:domain:github"))
    implementation(project(":appcore:repository:github"))

    implementation(libs.bundles.default)
    implementation(libs.bundles.default.screen)
    testImplementation(libs.bundles.default.test.implementation)
    androidTestImplementation(libs.bundles.default.test.androidTestImplementation)
    implementation(libs.bundles.kotlinx.serialization)

    implementation(libs.bundles.navigation)

    implementation(libs.bundles.hilt)
    kapt(libs.bundles.hilt.compiler.kapt)

    annotationProcessor(libs.bundles.glide.compiler.annotationProcessor)
    implementation(libs.bundles.glide)

}