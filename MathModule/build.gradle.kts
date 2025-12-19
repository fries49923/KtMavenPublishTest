import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.example.mathmodule"
    compileSdk = 36

    defaultConfig {
        minSdk = 31
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    publishing {
        singleVariant("release")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// 從專案根目錄讀取 secrets.properties 檔案
// 該檔案用於存放敏感資訊（例如 GitHub Packages 的帳號與 Token
val secretsProperties = Properties().apply {
    val file = File(rootDir, "secrets.properties")
    if (file.exists()) {
        load(file.inputStream())
    }
}

val gprUser: String? = secretsProperties.getProperty("gpr.user")
val gprKey: String? = secretsProperties.getProperty("gpr.key")

afterEvaluate {
    publishing {

        // 設定要發佈的內容，定義 MavenPublication 的相關資訊
        publications {
            create<MavenPublication>("release") {
                // groupId: 用來標示組織或專案群組，通常採用反轉域名形式
                groupId = "dev.someone.libs"

                // artifactId: 單一 Library 的名稱，建議用短橫線分隔
                artifactId = "utils-math"
                version = "0.0.2"

                // 指定要發佈的組件來源，這裡使用 release build 的 component
                from(components["release"])
            }
        }

        repositories {
            maven {
                name = "GitHubPackages"

                // 專案對應的 GitHub Packages URL
                url = uri("https://maven.pkg.github.com/fries49923/KtMavenPublishTest")

                // 設定憑證資訊
                credentials {
                    username = gprUser ?: System.getenv("USERNAME")
                    password = gprKey ?: System.getenv("TOKEN")
                }
            }
        }
    }
}