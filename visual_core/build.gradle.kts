plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.71"
    `java-library`
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation (project(":engine_player"))
    api ("com.badlogicgames.gdx:gdx:${project.extra["gdxVersion"]}")
    api ("com.badlogicgames.gdx:gdx-box2d:${project.extra["gdxVersion"]}")
    api ("com.badlogicgames.box2dlights:box2dlights:${project.extra["box2DLightsVersion"]}")
    api ("com.badlogicgames.ashley:ashley:${project.extra["ashleyVersion"]}")
    api ("com.badlogicgames.gdx:gdx-freetype:${project.extra["gdxVersion"]}")
    api ("com.badlogicgames.gdx:gdx-ai:${project.extra["aiVersion"]}")
    api ("com.badlogicgames.gdx:gdx-controllers:${project.extra["gdxVersion"]}")
    api ("com.badlogicgames.gdx:gdx-bullet:${project.extra["gdxVersion"]}")
    api ("com.google.code.gson:gson:${project.extra["gsonVersion"]}")

    api ("com.kotcrab.vis:vis-ui:1.4.2")
    api ("io.github.libktx:ktx-app:1.9.10-b5")
    api ("io.github.libktx:ktx-actors:1.9.10-b5")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}