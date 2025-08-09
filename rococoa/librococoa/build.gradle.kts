plugins {
    `java-library`
}

// Execute Xcode build for native libraries
tasks.register<Exec>("buildNative") {
    workingDir = File(projectDir, "./")
    commandLine(
        "xcodebuild",
        "-project",
        "rococoa.xcodeproj",
        "-target",
        "librococoa-test",
        "-configuration",
        "Release"
    )

    // Only run this task if the output files don't exist or are older than the sources
    outputs.file(File(workingDir, "build/Release/librococoa.dylib"))
    outputs.file(File(workingDir, "build/Release/librococoa-test.dylib"))

    doFirst {
        println("Building native libraries...")
    }
}

tasks.register<Exec>("cleanNative") {
    workingDir = File(projectDir, "./")
    commandLine(
        "xcodebuild",
        "-project",
        "rococoa.xcodeproj",
        "-target",
        "librococoa-test",
        "-configuration",
        "Release",
        "clean"
    )

    doFirst {
        println("Cleaning native libraries...")
    }
}

tasks.named("clean") {
    dependsOn("cleanNative")
}

// Configure the build to depend on the native library build
tasks.named("build") {
    dependsOn("buildNative")
}
