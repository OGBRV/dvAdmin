plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

repositories {
    jcenter()
    maven("https://jitpack.io")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://www.uskyblock.ovh/maven/dependencies/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("http://dl.bintray.com/ipgeolocation/ipgeolocation")
}

val bancoDeDados = false

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    compileOnly("com.github.MilkBowl:VaultAPI:1.7")

    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

    compileOnly("mysql:mysql-connector-java:5.1.48")

    if(bancoDeDados) {
        implementation("org.jetbrains.exposed:exposed-core:0.29.1")
        implementation("org.jetbrains.exposed:exposed-dao:0.29.1")
        implementation("org.jetbrains.exposed:exposed-jdbc:0.29.1")
    }

    implementation("io.ipgeolocation:ipgeolocation:1.0.12")
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.useIR = true
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.time.ExperimentalTime,kotlin.ExperimentalStdlibApi"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.time.ExperimentalTime,kotlin.ExperimentalStdlibApi"
    }
    shadowJar {
        destinationDirectory.set(File("$buildDir/../../../Desktop/Server/plugins"))
        dependencies {
            exclude(dependency("org.apache.logging.log4j:log4j-core"))
        }
    }
}
configurations.all {
    resolutionStrategy.cacheChangingModulesFor(120, "seconds")
}