plugins {
    `java-library`
}

group = "com.lalilu.rococoa"
version = "0.10.1"

// Task to build all native libraries first
tasks.register("buildNativeLibs") {
    dependsOn(":librococoa:buildNative")
    description = "Build all native libraries"
}