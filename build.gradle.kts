import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	id("org.asciidoctor.jvm.convert") version "3.3.2"
	kotlin("jvm") version "1.9.10"
	kotlin("plugin.spring") version "1.7.0"
	kotlin("plugin.jpa") version "1.7.0"
}

repositories {
	mavenCentral()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "18"
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlin:kotlin-reflect:")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.seleniumhq.selenium:htmlunit-driver")
    testImplementation("org.seleniumhq.selenium:selenium-support")
    testImplementation("au.com.dius:pact-jvm-consumer-junit_2.11:3.5.24")
}

tasks.test {
    systemProperties["pact.rootDir"] = "$buildDir/pacts"
}
