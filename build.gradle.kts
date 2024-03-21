// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.googleServices) apply false
    alias(libs.plugins.jetbrainsKotlinJvm) apply false
    alias(libs.plugins.firebaseCrashlytics) apply false
}

buildscript {


    dependencies {

        classpath(libs.kotlin.gradle)
        classpath (libs.google.dagger)
        classpath(libs.kotlin.serialization)
    }

}
