import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.composeMultiplatform)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.kotlin.serializable)
  alias(libs.plugins.hot.reload)
}

kotlin {
  @OptIn(ExperimentalWasmDsl::class)
  wasmJs {
    outputModuleName.set("composeApp")
    browser {
      val rootDirPath = project.rootDir.path
      val projectDirPath = project.projectDir.path
      commonWebpackConfig {
        outputFileName = "composeApp.js"
        devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
          static = (static ?: mutableListOf()).apply {
            // Serve sources to debug inside browser
            add(rootDirPath)
            add(projectDirPath)
          }
        }
      }
    }
    binaries.executable()
  }

  jvm("desktop")

  sourceSets {
    val desktopMain by getting

    desktopMain.dependencies {
      implementation(compose.desktop.currentOs)
      implementation(libs.ktor.client.cio)
      implementation(compose.preview)
      implementation(compose.uiTooling)
    }

    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.materialIconsExtended)
      implementation(compose.components.uiToolingPreview)
      implementation(compose.components.resources)
      implementation(libs.androidx.lifecycle.viewmodel)
      implementation(libs.androidx.lifecycle.runtimeCompose)
      implementation(libs.decompose.decompose)
      implementation(libs.decompose.extensionsComposeJetbrains)
      implementation(libs.lifecycle.coroutines)
      implementation(libs.orbital)
      implementation(libs.haze)
      implementation(libs.haze.materials)
      implementation(libs.compose.webview)
      implementation(libs.ktor.client.core)
      implementation(libs.ktor.client.content.negotiation)
      implementation(libs.ktor.server.serialization.json)
      implementation("io.coil-kt.coil3:coil-compose:3.2.0")
    }
    commonTest.dependencies {
      implementation(libs.kotlin.test)
    }
  }
}

compose.desktop {
  application {
    mainClass = "tech.vivienne.v_page2.MainKt"
    
    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
    }
  }
}

compose.resources {
  publicResClass = true
  generateResClass = always
  packageOfResClass = "tech.vivienne.composeapp.generated.resources"
}
