allprojects {
    extra["gdxVersion"] = "1.9.10"
    extra["box2DLightsVersion"] = "1.4"
    extra["ashleyVersion"] = "1.7.0"
    extra["aiVersion"] = "1.8.0"
    extra["gsonVersion"] = "2.8.6"

    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven ( url="https://plugins.gradle.org/m2/" )
        maven (url="https://oss.sonatype.org/content/repositories/snapshots/" )
        maven ( url="https://oss.sonatype.org/content/repositories/releases/" )
        jcenter()
    }
}
