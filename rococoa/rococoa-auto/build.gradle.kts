plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://oss.sonatype.org/content/groups/public")
    }
}

dependencies {
    api("com.nativelibs4java:jnaerator-runtime:0.12")
    api(project(":rococoa-core"))
    
    testImplementation("junit:junit:4.13.2")
}