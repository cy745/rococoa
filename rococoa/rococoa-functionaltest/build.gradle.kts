plugins {
    `java-library`
}

dependencies {
    implementation(project(":rococoa-core"))
    implementation(project(":rococoa-cocoa"))
    implementation(project(":rococoa-contrib"))

    // Test dependencies
    testImplementation(project(":rococoa-core"))

    testImplementation("junit:junit:4.13.2")
}