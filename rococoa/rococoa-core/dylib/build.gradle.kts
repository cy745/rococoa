plugins {
    `java-library`
    `maven-publish`
}

// Execute Xcode build for native libraries
tasks.register<Exec>("buildNative") {
    workingDir = File(projectDir, "..")
    commandLine("xcodebuild", "-project", "rococoa.xcodeproj", "-target", "librococoa-test", "-configuration", "Release")
    
    // Only run this task if the output files don't exist or are older than the sources
    outputs.file(File(workingDir, "build/Release/librococoa.dylib"))
    outputs.file(File(workingDir, "build/Release/librococoa-test.dylib"))
    
    doFirst {
        println("Building native libraries...")
    }
}

tasks.register<Exec>("cleanNative") {
    workingDir = File(projectDir, "..")
    commandLine("xcodebuild", "-project", "rococoa.xcodeproj", "-target", "librococoa-test", "-configuration", "Release", "clean")
    
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

// Create configurations for the native libraries
configurations.create("dylib")
configurations.create("dylibTest").extendsFrom(configurations["dylib"])

// Create tasks to copy native libraries to build directory
val copyDylib by tasks.registering(Copy::class) {
    from(File(projectDir, "../build/Release/librococoa.dylib"))
    into(File(buildDir, "libs"))
}

val copyDylibTest by tasks.registering(Copy::class) {
    from(File(projectDir, "../build/Release/librococoa-test.dylib"))
    into(File(buildDir, "libs"))
}

// Make sure the copy tasks run after buildNative
copyDylib {
    dependsOn("buildNative")
}

copyDylibTest {
    dependsOn("buildNative")
}

// Publish the native libraries as artifacts
artifacts {
    add("dylib", File(buildDir, "libs/librococoa.dylib")) {
        type = "dylib"
        builtBy(copyDylib)
    }
    add("dylibTest", File(buildDir, "libs/librococoa-test.dylib")) {
        type = "dylib"
        classifier = "test"
        builtBy(copyDylibTest)
    }
}

//publishing {
//    publications {
//        create<MavenPublication>("dylib") {
//            artifact(File(buildDir, "libs/librococoa.dylib")) {
//                type = "dylib"
//                builtBy(copyDylib)
//            }
//            artifact(File(buildDir, "libs/librococoa-test.dylib")) {
//                type = "dylib"
//                classifier = "test"
//                builtBy(copyDylibTest)
//            }
//
//            pom {
//                name.set("Rococoa Core Native Library")
//                description.set("Native libraries for Rococoa")
//                url.set("https://github.com/iterate-ch/rococoa")
//            }
//        }
//    }
//}