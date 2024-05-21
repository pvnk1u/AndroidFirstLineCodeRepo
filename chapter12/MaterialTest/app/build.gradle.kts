plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "io.github.pvnk1u"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.github.pvnk1u"
        minSdk = 29
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    /*添加了两行依赖关系：第一行就是Material库，第二行是一个开源项目CircleImageView，它可以用来轻松实现图片圆形化的功能*/
    implementation("com.google.android.material:material:1.1.0")
    implementation("de.hdodenhof:circleimageview:3.0.1")
    /*添加RecyclerView实现列表效果*，添加了Glide库的依赖。Glide是一个超级强大的开源图片加载库*/
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("com.github.bumptech.glide:glide:4.9.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}