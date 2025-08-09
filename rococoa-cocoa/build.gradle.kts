import com.vanniktech.maven.publish.JavaLibrary
import com.vanniktech.maven.publish.JavadocJar

plugins {
    `java-library`
    id("com.vanniktech.maven.publish") version "0.28.0"
}

group = "com.lalilu.rococoa"
version = "0.10.1"

dependencies {
    api(project(":rococoa-core"))

    // Test dependencies
    testImplementation(project(":rococoa-core"))
    testImplementation("junit:junit:4.13.2")
}

mavenPublishing {
    coordinates(group.toString(), "cocoa", version.toString())
    configure(
        JavaLibrary(
            javadocJar = JavadocJar.Empty(),
            sourcesJar = true,
        )
    )

    pom {
        name.set("Rococoa-cocoa")
        description.set("Rococoa-cocoa")
        url.set("https://github.com/cy745/rococoa")

        scm {
            url.set("https://github.com/cy745/rococoa")
        }
    }
}