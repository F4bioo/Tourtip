plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("maven-publish")
    id("signing")
}
apply(from = "$rootDir/plugins/android-build.gradle")

fun findPropertyByName(name: String): String {
    return (project.findProperty(name) as? String).orEmpty()
}

android {
    namespace = Config.NAMESPACE

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
            artifactId = Config.ARTEFACT_ID
            groupId = Config.GROUP_ID
            version = Config.VERSION

            pom {
                name.set(findPropertyByName("POM_PROJECT_NAME"))
                description.set(findPropertyByName("POM_PROJECT_DESCRIPTION"))
                url.set(findPropertyByName("POM_PROJECT_URL"))

                licenses {
                    license {
                        name.set(findPropertyByName("POM_LICENSE_NAME"))
                        url.set(findPropertyByName("POM_LICENSE_URL"))
                    }
                }
                developers {
                    developer {
                        id.set(findPropertyByName("POM_DEVELOPER_ID"))
                        name.set(findPropertyByName("POM_DEVELOPER_NAME"))
                        email.set(findPropertyByName("POM_DEVELOPER_EMAIL"))
                    }
                }
                issueManagement {
                    system.set("GitHub")
                    url.set(findPropertyByName("POM_PROJECT_URL") + "/issues")
                }
                scm {
                    connection.set(findPropertyByName("POM_SCM_CONNECTION"))
                    developerConnection.set(findPropertyByName("POM_SCM_DEVELOPER_CONNECTION"))
                    url.set(findPropertyByName("POM_SCM_URL"))
                }
            }
        }
    }
    repositories {
        maven {
            name = "OSSRH"
            url = uri(findPropertyByName("OSSRH_URL"))
            credentials {
                username = System.getenv("OSSRH_USERNAME")
                password = System.getenv("OSSRH_PASSWORD")
            }
        }
    }
    repositories {
        maven {
            name = "sonatype"

            val url = if (Config.VERSION.endsWith("SNAPSHOT")) {
                findPropertyByName("OSSRH_SNAPSHOT_URL")
            } else findPropertyByName("OSSRH_RELEASE_URL")
            setUrl(url)

            credentials {
                username = System.getenv("OSSRH_USERNAME")
                password = System.getenv("OSSRH_PASSWORD")
            }
        }
        maven {
            name = "local"
            url = uri(layout.buildDirectory.dir("test-repo"))
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
