plugins {
    `java-library`
}

group = "org.rococoa"
version = "0.10.1-SNAPSHOT"

// Task to build all native libraries first
tasks.register("buildNativeLibs") {
    dependsOn(":librococoa:buildNative")
    description = "Build all native libraries"
}