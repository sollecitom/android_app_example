import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.0-rc01")
        classpath(kotlin("gradle-plugin", version = "1.3.61"))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.0.0")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.5.2.0")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }

    tasks.withType<Test>() {
        testLogging {
            exceptionFormat = TestExceptionFormat.FULL
            events = setOf(TestLogEvent.STARTED, TestLogEvent.SKIPPED, TestLogEvent.PASSED, TestLogEvent.FAILED)
            showStandardStreams = true
        }
    }
}

tasks.register<Exec>("instrumentedTest") {
    group = "Verification"
    description = "Starts a virtual device in the background, runs task 'connectedAndroidTest' and then stops the device."

//    TODO see if you can accept config for Android SDK and device model(s)
    // TODO see if you can turn this into a Gradle plugin (https://docs.gradle.org/current/userguide/custom_tasks.html)
//    TODO avoid hardcoding the path for the Android SDK
    environment("ANDROID_SDK", "/home/michele/Android/Sdk")
//    TODO see if you can put the shell scripts in before/after the custom gradle task
    commandLine = listOf("./androidtests")
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
