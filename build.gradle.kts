plugins {
    id("java")
}

group = "com.jdbc.app"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("org.flywaydb:flyway-core:10.18.2")

    // compile dependencies
    compileOnly("org.projectlombok:lombok:1.18.34")

    // runtime dependencies
    runtimeOnly("org.flywaydb:flyway-database-postgresql:10.18.2")

    // processors
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}