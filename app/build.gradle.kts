plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {

    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            buildConfigField("String", "APP_MODE", "\"Ambiente Dev\"")
        }
        create("qa") {
            dimension = "environment"
            buildConfigField("String", "APP_MODE", "\"Ambiente QA\"")
        }
        create("pro") {
            dimension = "environment"
            buildConfigField("String", "APP_MODE", "\"Ambiente PRO\"")
        }
    }

    namespace = "com.example.countriesapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.countriesapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
        buildConfig = true //Nos permite trabajar con los flavors de compilación
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.coil.compose)
    implementation (libs.androidx.datastore.preferences)

    implementation (libs.ui) // Verifica que esta línea esté actualizada
    implementation (libs.material3) // También verifica esta

    //para mostrar las banderas en la trivia
    implementation(libs.coil.compose.v210)

    implementation (libs.androidx.ui.v172) // o la versión más reciente
    implementation (libs.androidx.material3.v100) // o la versión más reciente



    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.datastore.core.android)
    implementation(libs.androidx.datastore.preferences.core.jvm)
    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    // Dependencias de pruebas unitarias
    testImplementation (libs.junit)
    testImplementation (libs.mockito.core)
    testImplementation (libs.androidx.core)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation(libs.androidx.animation.core.android)
    testImplementation (libs.kotlinx.coroutines.test)


// Dependencias para pruebas instrumentadas
    androidTestImplementation (libs.androidx.junit.v115)
    androidTestImplementation (libs.androidx.espresso.core.v351)
    androidTestImplementation (libs.androidx.runner)
    androidTestImplementation (libs.androidx.rules)

    androidTestImplementation (libs.ext.junit.v113)
    androidTestImplementation (libs.androidx.espresso.core.v340)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}