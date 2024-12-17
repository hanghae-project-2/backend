plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "1.9.25"
    kotlin("kapt") version "1.9.25"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.springframework.boot") version "3.4.0"
}

group = "com.sparta"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2024.0.0"

dependencies {
    // web
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // queryDsl
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    // redis
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // resilience4j
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")

    // eureka
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    // feign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // json serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    // zipkin
    implementation("io.micrometer:micrometer-tracing-bridge-brave")
    implementation("io.zipkin.reporter2:zipkin-reporter-brave")

    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
