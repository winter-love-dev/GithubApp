@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.season.winter.githubapp.appcore.domain.github"
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
}

dependencies {

    implementation(libs.bundles.default)
    testImplementation(libs.bundles.default.test.implementation)
    androidTestImplementation(libs.bundles.default.test.androidTestImplementation)

    implementation(libs.bundles.rest.api)

    ksp(libs.bundles.room.compiler.ksp)
    ksp(libs.bundles.room.compiler.annotationProcessor)
    testImplementation(libs.bundles.room.testing.testImplementation)
    implementation(libs.bundles.room)
}