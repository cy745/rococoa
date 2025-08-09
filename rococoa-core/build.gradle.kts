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

tasks.test {
    val libraryPath = File(rootProject.projectDir, "librococoa/libs").absolutePath
    jvmArgs("-Djava.library.path=$libraryPath")
    systemProperty("jna.library.path", libraryPath)
    environment("DYLD_FALLBACK_FRAMEWORK_PATH", libraryPath)
}

if (System.getenv("JITPACK") != "true") {
    // 在开始编译前将，确保librococoa已经编译完成
    tasks.getByName("compileJava") {
        dependsOn(":librococoa:build")
    }
}

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