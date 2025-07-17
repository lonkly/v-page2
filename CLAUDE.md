# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Kotlin Multiplatform Compose project called `v-page2` that implements a cyberpunk-themed UI component library targeting Desktop (JVM) and Web (WASM/JS) platforms.

## Commands

### Build Commands
```bash
# Full build with tests
./gradlew build

# Build without tests
./gradlew assemble

# Clean build
./gradlew clean build
```

### Run Commands
```bash
# Run desktop application with hot reload (recommended for development)
./gradlew hotRunDesktop

# Run desktop without hot reload
./gradlew run

# Run web application in development mode
./gradlew :composeApp:wasmJsBrowserDevelopmentRun

# Build web bundle
./gradlew :composeApp:wasmJsBrowserDevelopmentWebpack
```

### Test Commands
```bash
# Run all tests
./gradlew allTests

# Run desktop tests
./gradlew desktopTest

# Run web tests
./gradlew wasmJsTest
./gradlew wasmJsBrowserTest

# Run all checks (includes tests)
./gradlew check
```

## Architecture

### Component Organization
The project follows **Atomic Design System** principles with a cyberpunk theme:

- **Atoms** (`design/atoms/`): Basic UI components
  - `CyberpunkButton`, `CyberpunkTextField`, `CyberpunkCheckbox`, `CyberpunkRadioButton`
  - `CyberpunkTitle`, `CyberpunkLink`, `CyberpunkSeparator`, `CyberpunkFieldset`
  - `CyberpunkLoadingIndicator`, `CyberpunkProgressBar`

- **Molecules** (`design/molecules/`): Composite components
  - `CyberpunkCard`, `CyberpunkDropdown`, `CyberpunkList`, `CyberpunkListItem`
  - `CyberpunkParagraph`, `CyberpunkSteps`, `CyberpunkDialogs`

- **Organisms** (`design/organisms/`): Complex UI sections
  - `CyberpunkLayouts`, `CyberpunkSection`, `CyberpunkAside`, `CyberpunkBoxTree`

### Theme System
- **CyberpunkTheme**: Main theme provider with custom colors, typography, and effects
- **CyberpunkColors**: Cyberpunk color palette (yellow, red, blue, green, neon variants)
- **CyberpunkEffects**: Visual effects including glitch animations
- **CyberpunkShapes**: Custom shape definitions for the theme

### Platform Structure
```
composeApp/src/
├── commonMain/     # Shared code across all platforms
├── desktopMain/    # Desktop-specific implementations
└── wasmJsMain/     # Web-specific implementations
```

### Key Dependencies
- **Decompose** (v3.3.0): Navigation and component lifecycle
- **Ktor** (v3.2.1): HTTP client
- **Orbital**: Animation library
- **Haze**: Blur/glassmorphism effects
- **Coil**: Image loading
- **Compose WebView**: Web content display

### Development Workflow
1. Use `./gradlew hotRunDesktop` for rapid desktop development with hot reload
2. Component previews available in `design/preview/ComponentPreviews.kt`
3. All components support `@Preview` annotation for visual development
4. Theme is applied via `CyberpunkTheme { }` wrapper

### Important Notes
- The project is undergoing a naming migration from `CP*` to `Cyberpunk*` prefix
- JVM memory is set to 20GB in gradle.properties (adjust if needed)
- Configuration cache is enabled for faster builds
- Official Kotlin code style is enforced