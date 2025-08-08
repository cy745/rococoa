plugins {
    `java-library`
    `maven-publish`
}

dependencies {
    implementation(project(":rococoa-core"))
    implementation(project(":rococoa-cocoa"))
    implementation(project(":rococoa-contrib"))
    
    // Test dependencies
    testImplementation(project(":rococoa-core"))
    testImplementation(project(path = ":librococoa", configuration = "dylibTest"))
    
    testImplementation("junit:junit:4.13.2")
}