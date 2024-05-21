plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.protobuf)
}

kotlin {
    jvmToolchain(8)
}

dependencies {

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.grpc.stub)
    implementation(libs.grpc.protobuf)
    implementation(libs.protobuf.java.util)
    implementation(libs.protobuf.kotlin)
    implementation(libs.grpc.kotlin.stub)
    runtimeOnly(libs.grpc.netty)
}

protobuf {
    protoc {
        artifact = libs.protoc.asProvider().get().toString()
    }
    plugins {
        create("grpc") {
            artifact = libs.protoc.gen.grpc.java.get().toString()
        }
        create("grpckt") {
            artifact = libs.protoc.gen.grpc.kotlin.get().toString() + ":jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
                create("grpckt")
            }
            it.builtins {
                create("kotlin")
            }
        }
    }
}

tasks.register<JavaExec>("LocationServer") {
    dependsOn("classes")
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.example.realtimelocation.LastKnownLocationServerKt")
}

val lastKnownLocationServerStartScripts =
    tasks.register<CreateStartScripts>("lastKnownLocationServerStartScripts") {
        mainClass.set("com.example.realtimelocation.LastKnownLocationServerKt")
        applicationName = "location-server"
        outputDir = tasks.named<CreateStartScripts>("startScripts").get().outputDir
        classpath = tasks.named<CreateStartScripts>("startScripts").get().classpath
    }


tasks.named("startScripts") {
    dependsOn(lastKnownLocationServerStartScripts)
}
