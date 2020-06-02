plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.71"
    application
}


dependencies {
    
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation (project(":visual_core"))


    api ("com.badlogicgames.gdx:gdx-backend-lwjgl:${project.extra["gdxVersion"]}")
    api ("com.badlogicgames.gdx:gdx-platform:${project.extra["gdxVersion"]}:natives-desktop")
    api ("com.badlogicgames.gdx:gdx-box2d-platform:${project.extra["gdxVersion"]}:natives-desktop")
    api ("com.badlogicgames.gdx:gdx-freetype-platform:${project.extra["gdxVersion"]}:natives-desktop")
    api ("com.badlogicgames.gdx:gdx-tools:${project.extra["gdxVersion"]}")
    api ("com.badlogicgames.gdx:gdx-controllers-desktop:${project.extra["gdxVersion"]}")
    api ("com.badlogicgames.gdx:gdx-controllers-platform:${project.extra["gdxVersion"]}:natives-desktop")
    api ("com.badlogicgames.gdx:gdx-bullet-platform:${project.extra["gdxVersion"]}:natives-desktop")
    api ("com.google.code.gson:gson:${project.extra["gsonVersion"]}")
    api ("io.github.libktx:ktx-app:1.9.10-b5")
    api ("com.kotcrab.vis:vis-ui:1.4.2")



    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "com.solovova.iigame.visual.desktop.DesktopStarter"
}
