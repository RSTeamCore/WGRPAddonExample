import xyz.jpenilla.runpaper.task.RunServer

plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "net.ritasister"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        name = "PaperMC"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "CodeMC"
        url = uri("https://repo.codemc.org/repository/maven-public/")
    }
    maven {
        name = "EngineHub"
        url = uri("https://maven.enginehub.org/repo/")
    }
    mavenCentral()
}

dependencies {
    //Paper
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")

    //WGRP api
    compileOnly("net.ritasister:wgrp-api:1.7.21")

    //Plugins
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.1.0-SNAPSHOT")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.named<RunServer>("runServer") {
    minecraftVersion("1.21.1")
    jvmArgs("-Xms4G", "-Xmx4G")
}

tasks.test {
    useJUnitPlatform()
}