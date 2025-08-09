pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(":rococoa-core")
include(":rococoa-cocoa")
include(":rococoa-contrib")

if (System.getenv("JITPACK") != "true") {
    include(":librococoa")
}