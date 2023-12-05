plugins {
    id("com.android.application") version "8.2.0" apply false
//    id("com.android.library") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.5.31" apply false
}

android {
    namespace = "com.example.chunga_cash_app"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.chunga_cash_app"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

//    buildFeatures {
//        viewBinding = true
//    } was getting java.lang errors with code
    viewBinding {
        enable = true;
    }

    dataBinding {
        enable = true;
    }

}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0") // Update to the latest version
    implementation("androidx.appcompat:appcompat:1.6.1") // Update to the latest version
    implementation("com.google.android.material:material:1.10.0") // Update to the latest version
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("com.google.firebase:firebase-database-ktx:24.1.0") // Update to the latest version
    implementation("com.google.firebase:firebase-common-ktx:23.0.0") // Update to the latest version
    implementation("com.google.firebase:firebase-database:24.1.0") // Update to the latest version

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.squareup.picasso:picasso:2.71828")


}


