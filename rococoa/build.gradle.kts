plugins {
    `java-library`
    `maven-publish`
    signing
}

group = "org.rococoa"
version = "0.10.1-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://oss.sonatype.org/content/groups/public")
        }
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        withSourcesJar()
        withJavadocJar()
    }

    dependencies {
        testImplementation("junit:junit:4.13.2")
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
                
                pom {
                    url.set("https://github.com/iterate-ch/rococoa")
                    licenses {
                        license {
                            name.set("LGPL 3")
                            url.set("http://www.gnu.org/licenses/lgpl-3.0.txt")
                            distribution.set("repo")
                        }
                    }
                    developers {
                        developer {
                            id.set("oneeyedmen")
                            name.set("Duncan McGregor")
                            email.set("duncan@oneeyedmen.com")
                            roles.set(listOf("Owner"))
                        }
                        developer {
                            id.set("lordpixel")
                            name.set("Andrew Thompson")
                            email.set("lordpixel@mac.com")
                            roles.set(listOf("Owner"))
                        }
                        developer {
                            id.set("dkocher")
                            name.set("David Kocher")
                            email.set("post@iterate.ch")
                            organization.set("iterate GmbH")
                            roles.set(listOf("Committer"))
                        }
                        developer {
                            id.set("keteracel")
                            name.set("Paul Loy")
                            email.set("keteracel@gmail.com")
                            roles.set(listOf("Committer"))
                        }
                        developer {
                            id.set("ochafik")
                            name.set("Olivier Chafik")
                            email.set("olivier.chafik@gmail.com")
                            roles.set(listOf("Committer"))
                        }
                    }
                    scm {
                        connection.set("scm:git:git@github.com:iterate-ch/rococoa.git")
                        developerConnection.set("scm:git:git@github.com:iterate-ch/rococoa.git")
                        url.set("https://github.com/iterate-ch/rococoa")
                    }
                }
            }
        }
        
        repositories {
            maven {
                name = "MavenCyberduckIORelease"
                url = uri("s3://repo.maven.cyberduck.io/releases/")
                credentials {
                    username = findProperty("maven.cyberduck.io.username") as String?
                    password = findProperty("maven.cyberduck.io.password") as String?
                }
            }
            maven {
                name = "MavenCyberduckIOSnapshot"
                url = uri("s3://repo.maven.cyberduck.io/snapshots/")
                credentials {
                    username = findProperty("maven.cyberduck.io.username") as String?
                    password = findProperty("maven.cyberduck.io.password") as String?
                }
            }
        }
    }
    
    signing {
        isRequired = false
        useGpgCmd()
        sign(publishing.publications["maven"])
    }
}

// Task to build all native libraries first
tasks.register("buildNativeLibs") {
    dependsOn(":librococoa:buildNative")
    description = "Build all native libraries"
}