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
include(":rococoa-functionaltest")
include(":librococoa")