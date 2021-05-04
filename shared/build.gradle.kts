import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
    id("com.android.library")
}

version = "1.0"

sqldelight {
    database("Database") {
        packageName = "de.johnki.shared"
    }
}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {

                //sqldelight
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.0")

                //ktor
                implementation("io.ktor:ktor-client-core:1.5.4")
                implementation("io.ktor:ktor-client-serialization:1.5.4")

                //kotlinx-serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.0")

                //coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3-native-mt")
            }
        }

        val androidMain by getting {
            dependencies {
                //sqldelight
                implementation("com.squareup.sqldelight:android-driver:1.5.0")

                //ktor
                implementation("io.ktor:ktor-client-android:1.5.4")

                //coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3-native-mt")

            }
        }

        val iosMain by getting {
            dependencies {
                //sqldelight
                implementation("com.squareup.sqldelight:native-driver:1.5.0")

                //ktor
                implementation("io.ktor:ktor-client-ios:1.5.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        val iosTest by getting
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }
}