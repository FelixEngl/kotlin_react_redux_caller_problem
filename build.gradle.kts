plugins {
    kotlin("multiplatform") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.21"
}

group = "de.uniba.minf.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

kotlin {

    js {
        binaries.executable()

        browser {

            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
                sourceMaps = true
            }

            distribution {
                directory = File(buildDir, "jsDistribution")
            }

            runTask {
                devServer = devServer?.copy(
                    port = 8088,
                    proxy = mutableMapOf(
                        "/api" to "http://localhost:8080",
                    )
                )
                outputFileName = "tool_test.js"

            }

            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }

    //react
    val kotlinWrappersVersion: String by project
    val kotlinSerializationVersion: String by project
    val kotlinLoggingVersion: String by project

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("reflect"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinSerializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

                implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(enforcedPlatform(kotlinw("wrappers-bom:$kotlinWrappersVersion")))
                implementation(kotlinw("react"))
                implementation(kotlinw("react-dom"))
                implementation(kotlinw("react-router-dom"))
                implementation(kotlinw("redux"))
                implementation(kotlinw("react-redux"))
                implementation(kotlinw("csstype"))
                implementation(kotlinw("cssom-core"))
                implementation(kotlinw("browser"))
                implementation(kotlinw("typescript"))
                implementation(kotlinw("emotion"))
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}


kotlin {
    js {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
}

