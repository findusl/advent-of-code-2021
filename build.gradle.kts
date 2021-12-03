plugins {
	kotlin("multiplatform") version "1.6.0"
}

group = "de.lehrbaum"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
	mavenLocal()
}

kotlin {
	val hostOs = System.getProperty("os.name")
	val isMingwX64 = hostOs.startsWith("Windows")
	val nativeTarget = when {
		hostOs == "Mac OS X" -> macosX64("native")
		hostOs == "Linux" -> linuxX64("native")
		isMingwX64 -> mingwX64("native")
		else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
	}

	nativeTarget.apply {
		binaries {
			executable {
				entryPoint = "main"
			}
		}
	}
	sourceSets {
		val okioVersion = "3.0.0"
		val nativeMain by getting {
			dependencies {
				// locally published sample from jetbrains
				// https://github.com/JetBrains/kotlin/tree/master/kotlin-native/samples/libcurl
				implementation("org.jetbrains.kotlin.sample.native:libcurl:+")

				implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

				// for file io
				implementation("com.squareup.okio:okio:$okioVersion")
				implementation("com.squareup.okio:okio-macosx64:$okioVersion")
			}
		}
		val nativeTest by getting
	}
}

tasks {
	val assembleWithResources = register("assembleWithResources", Copy::class) {
		group = "build"
		description = "Generates and copies the release exe and resources into one directory"

		from("$buildDir/processedResources/native/main") {
			include("**/*")
		}

		from("$buildDir/bin/native/releaseExecutable") {
			include("*.*")
		}

		into("$buildDir/ready")
		includeEmptyDirs = false
		dependsOn("nativeProcessResources")
		dependsOn("assemble")
	}
}
