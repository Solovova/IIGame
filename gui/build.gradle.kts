import org.gradle.internal.os.OperatingSystem

plugins {
    kotlin("jvm") version "1.3.72"
    application
}

group = "com.solovova.iigame.gui"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val os: OperatingSystem = OperatingSystem.current()
val platform = when { os.isWindows -> "win"; os.isLinux-> "linux"; os.isMacOsX -> "mac"; else -> error("Unknown OS") }

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.openjfx:javafx-fxml:14:$platform")
    implementation("org.openjfx:javafx-web:14:$platform")
    implementation("org.openjfx:javafx-media:14:$platform")
    implementation("org.openjfx:javafx-swing:14:$platform")
    implementation("org.openjfx:javafx-base:14:$platform")
    implementation("org.openjfx:javafx-graphics:14:$platform")
    implementation("org.openjfx:javafx-controls:14:$platform")
    testCompile("junit", "junit", "4.12")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}


