@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    namespace = "com.season.winter.githubapp"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.season.winter.githubapp"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

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
//    implementation(project(":appcore:repository:github"))

    implementation(libs.bundles.default)
    implementation(libs.bundles.default.screen)
    testImplementation(libs.bundles.default.test.implementation)
    androidTestImplementation(libs.bundles.default.test.androidTestImplementation)
    implementation(libs.bundles.kotlinx.serialization)

    implementation(libs.bundles.navigation)

    implementation(libs.bundles.hilt)
    kapt(libs.bundles.hilt.compiler.kapt)
    ksp(libs.bundles.room.compiler.ksp)
    annotationProcessor(libs.bundles.room.compiler.annotationProcessor)
    testImplementation(libs.bundles.room.testing.testImplementation)
    implementation(libs.bundles.room)
    annotationProcessor(libs.bundles.glide.compiler.annotationProcessor)
    implementation(libs.bundles.glide)
    implementation(libs.bundles.workmanager.all)

//    implementation(platform(libs.com.google.firebase.bom))
// implementation(libs.bundles.firebase)
//    implementation(libs.bundles.compose.all)


}

kapt {
    correctErrorTypes = true
}