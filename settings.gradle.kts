rootProject.name = "T_Rex_LDTS"
include("src:main:test")
findProject(":src:main:test")?.name = "test"
include("src:test")
findProject(":src:test")?.name = "test"
