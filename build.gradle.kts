plugins {
    id("java")
    id("application")
    id("jacoco")
    id("info.solidsoft.pitest") version("1.15.0")
}

group = "feup.ldts.trex"
version = "1.0"

repositories {
    mavenCentral()
}
application{
    mainClass.set("feup.ldts.trex.Main")
}
jacoco{
    toolVersion = "0.8.11"
}
pitest{
    junit5PluginVersion = "1.1.1"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.googlecode.lanterna:lanterna:3.1.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core:3.7.7")
}

tasks.test {
    minHeapSize = "128m" // initial heap size
    maxHeapSize = "4096m" // maximum heap size
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport{
    dependsOn(tasks.test)
}

