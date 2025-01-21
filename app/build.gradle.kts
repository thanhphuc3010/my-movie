import java.util.Properties

plugins {
    id("kotlin-kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.phucpt.mymovie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.phucpt.mymovie"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "BASE_URL", "\"${properties.getProperty("BASE_URL")}\"")
        buildConfigField(
            "String",
            "ACCESS_TOKEN_AUTH",
            "\"${properties.getProperty("ACCESS_TOKEN_AUTH")}\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    android {
        buildFeatures {
            viewBinding = true
            buildConfig = true
        }
    }
}

dependencies {
    implementation(project(":core-domain"))
    implementation(project(":di"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.google.android.material)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)

    // okhttpclient
    implementation(libs.okhttp)
    implementation(libs.okhttp3.logging.interceptor)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // RecyclerView
    implementation(libs.androidx.recyclerview)

    // Paging 3
    implementation(libs.androidx.paging.runtime)

    // Glide
    implementation(libs.glide)

    // Room database
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    implementation("androidx.core:core-splashscreen:1.0.1")
//    implementation("com.github.gcacace:signature-pad:1.3.1")
    implementation("se.warting.signature:signature-view:0.1.2")

//    api('io.jsonwebtoken:jjwt-api:0.12.3')
//    runtimeOnly('io.jsonwebtoken:jjwt-impl:0.12.3')
//    runtimeOnly('io.jsonwebtoken:jjwt-orgjson:0.12.3') {
//        exclude(group: 'org.json', module: 'json') //provided by Android natively
//    }

//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
}

kapt {
    correctErrorTypes = true
}
