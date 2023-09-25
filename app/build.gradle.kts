plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}
android {
    namespace = "com.abc.demo"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.watchvideo.dailyrewards.earnmoney"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
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
//        compose = true
//    }
    buildFeatures {
        viewBinding = true
    }
    buildFeatures {
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
    buildFeatures {
        buildConfig = true
    }
    dexOptions {
        incremental = true
        javaMaxHeapSize = "4g"
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.3.2"
//    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")
    implementation("com.onesignal:OneSignal:[5.0.0, 5.99.99]")
    implementation("androidx.biometric:biometric:1.1.0")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.airbnb.android:lottie:6.1.0")
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.google.android.play:app-update-ktx:2.1.0")
    implementation("com.google.android.gms:play-services-ads:22.4.0")
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("androidx.palette:palette-ktx:1.0.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.wang.avi:library:2.1.3")
    implementation("com.facebook.android:audience-network-sdk:6.14.0")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.27")

    implementation("com.luolc:emoji-rain:0.1.1")
    implementation("com.github.FireZenk:BubbleEmitter:-SNAPSHOT")
    implementation("com.github.AtifSayings:Animatoo:1.0.1")
    implementation("tyrantgit:explosionfield:1.0.1")
    implementation("uk.co.androidalliance:edgeeffectoverride:1.0.2")
    implementation("com.fujiyuu75:sequent:0.2.1")
    implementation("com.github.sarnavakonar:TextWriter:v1.0")
    implementation("com.github.rommansabbir:AnimationX:2.0")
    implementation("com.github.skydoves:transformationlayout:1.1.2")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.27")
    implementation("com.github.HamidrezaAmz:MagicalExoPlayer:3.0.8")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.github.AnupKumarPanwar:ScratchView:1.9.1")
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    //noinspection LifecycleAnnotationProcessorWithJava8
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.6.2")
}