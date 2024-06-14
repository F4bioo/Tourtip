plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("maven-publish")
}
apply(from = "$rootDir/plugins/android-build.gradle")

android {
    namespace = Config.namespace

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
            artifactId = Config.artifactId
            groupId = Config.groupId
            version = Config.version
        }
    }
    repositories {
        maven {
            name = "local"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

val resolvedImplementation: Configuration by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false
    extendsFrom(configurations.implementation.get())
}

// Task to copy dependencies into libs folder
tasks.register<Copy>("copyLibs") {
    from(resolvedImplementation.resolvedConfiguration.resolvedArtifacts.map { it.file })
    into(layout.buildDirectory.dir("libs"))
}

// Ensure copyLibs task runs during the build process
tasks.named("build") {
    dependsOn("copyLibs")
}
