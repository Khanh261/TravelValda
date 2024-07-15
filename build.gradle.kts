buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
        classpath("com.android.tools.build:gradle:7.0.2")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
}
