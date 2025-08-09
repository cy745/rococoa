import com.vanniktech.maven.publish.JavaLibrary
import com.vanniktech.maven.publish.JavadocJar

plugins {
    `java-library`
    id("com.vanniktech.maven.publish") version "0.28.0"
}

group = "com.lalilu.rococoa"
version = "0.10.1"

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

mavenPublishing {
    coordinates(group.toString(), "core", version.toString())
    configure(
        JavaLibrary(
            javadocJar = JavadocJar.Empty(),
            sourcesJar = true,
        )
    )

    pom {
        name.set("Rococoa-core")
        description.set("Rococoa-core")
        url.set("https://github.com/cy745/rococoa")

        scm {
            url.set("https://github.com/cy745/rococoa")
        }
    }
}