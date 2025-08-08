rootProject.name = "rococoa-parent"

include(":rococoa-core")
include(":rococoa-cocoa")
include(":rococoa-contrib")
include(":rococoa-functionaltest")
include(":rococoa-auto")
include(":librococoa")

project(":librococoa")
    .projectDir = file("rococoa-core/dylib")