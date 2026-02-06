plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // SLF4J API (abstraction layer)
    implementation("org.slf4j:slf4j-api:2.0.17") // Use the latest version
    runtimeOnly("org.slf4j:slf4j-simple:2.0.17") // Use the latest version
}

tasks.test {
    useJUnitPlatform()
}