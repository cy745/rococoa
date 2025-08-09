plugins {
    `java-library`
}

dependencies {
    api("net.java.dev.jna:jna:5.17.0")
    api("net.bytebuddy:byte-buddy:1.17.5")

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
//
//tasks.getByName("compileJava") {
//    dependsOn(":librococoa:copyDylibTest")
//}