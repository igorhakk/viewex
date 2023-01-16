buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-allopen:${Deps.Version.Kotlin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Deps.Version.Kotlin}")
        classpath("com.github.node-gradle:gradle-node-plugin:${Deps.Version.NodePlugin}")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${Deps.Version.SpringBoot}")
    }
}

allprojects {
    group = "app.viewex"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }

    buildDir = File(
        "${rootProject.rootDir}${File.separator}" +
                "build" + File.separator +
                name
    )

    val kotlinSrc = File("$projectDir${File.separator}src${File.separator}main${File.separator}kotlin")

    if (kotlinSrc.exists()) {

        plugins.apply("org.jetbrains.kotlin.jvm")

        dependencies {
            add("api", platform("org.jetbrains.kotlin:kotlin-bom:${Deps.Version.Kotlin}"))
            add("implementation", "org.jetbrains.kotlin:kotlin-reflect")
            add("implementation", "org.junit.jupiter:junit-jupiter:${Deps.Version.JUnitJupiter}")
        }

        tasks.named<Test>("test") {
            useJUnitPlatform()
        }
        
    }

}
