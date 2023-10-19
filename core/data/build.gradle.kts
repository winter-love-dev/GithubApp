import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.season.winter.githubapp.core.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "GITHUB_REST_API_ACCESS_KEY", getGithubRestApiAccess())
        buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
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
    buildFeatures {
        buildConfig = true
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

    implementation(project(":core:domain"))

    implementation(libs.bundles.default)

    implementation(libs.bundles.rest.api)

    implementation(libs.bundles.hilt)
    kapt(libs.bundles.hilt.compiler.kapt)

    ksp(libs.bundles.room.compiler.ksp)
    ksp(libs.bundles.room.compiler.annotationProcessor)
    implementation(libs.bundles.room)

    testImplementation(libs.bundles.room.testing.testImplementation)
    testImplementation(libs.bundles.test.unit)
}

fun getGithubRestApiAccess(): String {
    return gradleLocalProperties(rootDir).getProperty("github_rest_api_access_key") ?: ""
}
