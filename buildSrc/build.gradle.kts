//@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `kotlin-dsl` // enable the Kotlin-DSL
    // `kotlin-dsl-precompiled-script-plugins`
//    alias(libs.plugins.android.application) apply false
//    alias(libs.plugins.android.library) apply false
//    alias(libs.plugins.kotlin.android) apply false
//    alias(libs.plugins.kotlin.kapt) apply false
//    alias(libs.plugins.dagger.hilt) apply false
//    alias(libs.plugins.devtools.ksp) apply false
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

//fun Project.`androidSrc`(configure: Action<com.android.build.gradle.internal.dsl.BaseAppModuleExtension>): Unit =
//    (this as ExtensionAware).extensions.configure("android", configure)

//androidSrc {
//    compileSdk = libs.versions.compileSdk.get().toInt()
//}

dependencies {

//    implementation(libs.plugin.agp)
//    implementation(libs.plugin.kgp)
//    implementation(libs.bundles.default)
//    implementation(libs.bundles.default.screen)
//    testImplementation(libs.bundles.default.test.implementation)
//    androidTestImplementation(libs.bundles.default.test.androidTestImplementation)
//    org.gradle.api.artifacts.dsl.DependencyHandler.`androidTestImplementation`(libs.bundles.default.test.androidTestImplementation)

}

