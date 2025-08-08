plugins {
    `java-library`
    `maven-publish`
}

dependencies {
    api(project(":rococoa-core"))
    api(project(":rococoa-cocoa"))
    
    // Test dependencies
    testImplementation(project(":rococoa-core"))
    testImplementation(project(path = ":librococoa", configuration = "dylibTest"))
    
    testImplementation("junit:junit:4.13.2")
}

//publishing {
//    publications {
//        create<MavenPublication>("maven") {
//            pom {
//                name.set("Rococoa Contrib")
//            }
//        }
//    }
//}