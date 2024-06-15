plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("maven-publish")
    id("signing")
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

            pom {
                name.set("Tourtip")
                description.set("A guided tour library for Android applications, built using Jetpack Compose.")
                url.set("https://github.com/F4bioo/Tourtip")

                licenses {
                    license {
                        name.set("The MIT License")
                        url.set("https://mit-license.org/")
                    }
                }
                developers {
                    developer {
                        id.set("F4bioo")
                        name.set("Fabio Marinho")
                        email.set("costa.fbo@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/F4bioo/Tourtip.git")
                    developerConnection.set("scm:git:ssh://github.com/F4bioo/Tourtip.git")
                    url.set("https://github.com/F4bioo/Tourtip")
                }
            }
        }
    }
    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.findProperty("ossrhUsername") as String?
                    ?: System.getenv("OSSRH_USERNAME")
                password = project.findProperty("ossrhPassword") as String?
                    ?: System.getenv("OSSRH_PASSWORD")
            }
        }
        maven {
            name = "local"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

signing {
    useInMemoryPgpKeys(
        System.getenv("PGP_PRIVATE_KEY"),
        System.getenv("PGP_PASSPHRASE")
    )
    sign(publishing.publications["release"])
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
