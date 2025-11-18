plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt)
	kotlin("kapt")
}

android {
	namespace = "cz.mendelu.pef.petstore"
	compileSdk = 36

	defaultConfig {
		applicationId = "cz.mendelu.pef.petstore"
		minSdk = 29
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"
		//testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		testInstrumentationRunner = "cz.mendelu.pef.petstore.HiltTestRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions { jvmTarget = "17" }

	buildFeatures { compose = true }

	// Compose compiler provided by compose plugin (ensure AGP + Kotlin match)
	packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
}

dependencies {
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.compose.ui.graphics)
	implementation(libs.androidx.compose.ui.tooling.preview)
	implementation(libs.androidx.compose.material3)
	implementation(libs.androidx.compose.material.icons.extended)


	// Hilt
	implementation(libs.hilt.android)
	implementation(libs.androidx.hilt.navigation.compose)
	kapt(libs.hilt.android.compiler)

	// Networking / JSON
	implementation(libs.retrofit)
	implementation(libs.retrofit.moshi)
	implementation(libs.moshi)
	implementation(libs.moshi.kotlin)

	// DataStore
	implementation(libs.datastore.core)
	implementation(libs.datastore.prefs)

	// Navigation
	implementation(libs.androidx.navigation.compose)

	// Coil
	implementation(libs.coil.compose)

	// Timber
	implementation(libs.timber)

	// Tests
	testImplementation(libs.junit)
	androidTestImplementation(libs.ui.test.junit4)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.junit)
	debugImplementation(libs.androidx.compose.ui.tooling)
	debugImplementation(libs.androidx.compose.ui.test.manifest)

	// Hilt testing
	androidTestImplementation(libs.hilt.android.testing)
	kaptAndroidTest(libs.hilt.android.compiler)
}

kapt {
	correctErrorTypes = true
}