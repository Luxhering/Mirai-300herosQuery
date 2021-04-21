plugins {
    val kotlinVersion = "1.4.30"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("net.mamoe.mirai-console") version "2.0-RC"
}

group = "org.example"
version = "1.0-SNAPSHOT"
dependencies {
    val miraiVersion = "2.0-RC"
    api("net.mamoe", "mirai-core-api", miraiVersion)     // 编译代码使用
    runtimeOnly("net.mamoe", "mirai-core", miraiVersion)// 运行时使用
    runtimeOnly("net.mamoe:mirai-login-solver-selenium:1.0-dev-16")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}
repositories {
    jcenter()
    maven { url = uri("https://dl.bintray.com/karlatemp/misc") }
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}