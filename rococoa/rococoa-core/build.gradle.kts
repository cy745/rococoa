plugins {
    `java-library`
    `maven-publish`
}

dependencies {
    api("net.java.dev.jna:jna:5.17.0")
    api("net.bytebuddy:byte-buddy:1.17.5")
    
    // Native library dependencies
    implementation(project(path = ":librococoa", configuration = "dylib"))
    testImplementation(project(path = ":librococoa", configuration = "dylibTest"))
    
    testImplementation("junit:junit:4.13.2")
}

//tasks.test {
//    jvmArgs("-Djava.library.path=build/libs")
//    systemProperty("jna.library.path", "../build/Release")
//    environment("DYLD_FALLBACK_FRAMEWORK_PATH", "../build/Release")
//
//    // Configure fork behavior
//    forkCount = 1
//    reuseForks = false
//}

// Create a test-jar for other modules to depend on
val testJar by tasks.registering(Jar::class) {
    archiveClassifier.set("tests")
    from(sourceSets.test.get().output)
}

configurations {
    create("testArtifacts")
}

artifacts {
    add("testArtifacts", testJar)
}