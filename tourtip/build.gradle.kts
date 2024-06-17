import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.configurationcache.extensions.capitalized

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.vanniktechMavenPublish)
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

mavenPublishing {
    coordinates(
        artifactId = findPropertyByName("POM_ARTEFACT_ID"),
        groupId = findPropertyByName("POM_GROUP_ID"),
        version = Config.VERSION
    )

    pom {
        name.set(findPropertyByName("POM_ARTEFACT_ID").capitalized())
        description.set(findPropertyByName("POM_PROJECT_DESCRIPTION"))
        inceptionYear.set(findPropertyByName("POM_PROJECT_INCEPTION_YEAR"))
        url.set(findPropertyByName("POM_PROJECT_URL"))
        licenses {
            license {
                name.set(findPropertyByName("POM_LICENSE_NAME"))
                url.set(findPropertyByName("POM_LICENSE_URL"))
                distribution.set(findPropertyByName("POM_LICENSE_URL"))
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
            url.set(findPropertyByName("POM_PROJECT_URL") + "issues")
        }
        scm {
            url.set(findPropertyByName("POM_PROJECT_URL") + "tree/master")
            connection.set(findPropertyByName("POM_SCM_CONNECTION"))
            developerConnection.set(findPropertyByName("POM_SCM_DEVELOPER_CONNECTION"))
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = false)
    signAllPublications()
}

signing {
    useInMemoryPgpKeys(
        System.getenv("PGP_KEY_ID"),
        System.getenv("PGP_PRIVATE_KEY"),
        System.getenv("PGP_PASSPHRASE")
    )
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
