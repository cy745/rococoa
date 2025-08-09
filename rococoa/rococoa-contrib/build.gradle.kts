plugins {
    `java-library`
}

dependencies {
    api(project(":rococoa-core"))
    api(project(":rococoa-cocoa"))

    // Test dependencies
    testImplementation(project(":rococoa-core"))
    testImplementation("junit:junit:4.13.2")
}