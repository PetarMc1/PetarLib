# PetarLib

A utility library, providing essential tools. Its used in my mods like [the Fish Tracker mod](https://github.com/PetarMc1/change)

## Features

PetarLib includes several modules to simplify mod development:

- **Chat Module**: Advanced chat pattern matching and handling utilities
- **Log Module**: Flexible logging system with configurable levels and outputs
- **Net Module**: HTTP client wrapper for network operations
- **Task Module**: Task scheduling and management utilities
- **Notifications Module**: In-game notification system using the action bar

## Usage

### Versioning note

As of v2.0.0-alpha.3 the library's package/coordinate structure was changed. Below are examples for both the newer (v2.0.0-alpha.3 and above) and the legacy (older) versions so you can pick the right dependency and imports.

- New (v2.0.0-alpha.3+):
  - Maven coordinate: groupId `com.petarmc.petarlib`, artifactId `petarlib-fabric`.
  - Typical import (Java): `import com.petarmc.petarlib.chat.ChatPatternMatcher;`
  - Mod entrypoint (fabric.mod.json): `com.petarmc.petarlib.Petarlib`

- Legacy (before v2.0.0-alpha.3):
  - Maven coordinate : groupId `com.petarmc`, artifactId `lib`.
  - Typical import (Java): `import com.petarmc.lib.chat.ChatPatternMatcher;`
  - Mod entrypoint (fabric.mod.json): `com.petarmc.lib.Petarlib`

### Maven
Add the repo:
```xml
<repository>
  <id>petarmc-releases</id>
  <name>Maven Repository</name>
  <url>https://maven.petarmc.com/releases</url>
</repository>
```

Add dependency:

- For v2.0.0-alpha.3 and above (new structure):
```xml
<dependency>
  <groupId>com.petarmc.petarlib</groupId>
  <artifactId>petarlib-fabric</artifactId>
  <version>2.x.x</version>
</dependency>
```

- For older versions (legacy coordinates):
```xml
<dependency>
  <groupId>com.petarmc</groupId>
  <artifactId>lib</artifactId>
  <version>2.x.x</version>
</dependency>
```


### Gradle
#### Kotlin
Add the repo:
```kotlin
maven {
    name = "petarmcReleases"
    url = uri("https://maven.petarmc.com/releases")
}
```
Add dependency:

- For v2.0.0-alpha.3 and above (new structure):
```kotlin
implementation("com.petarmc.petarlib:petarlib-fabric:2.x.x")
```

- For older versions (legacy coordinates):
```kotlin
implementation("com.petarmc:lib:1.x.x")
```

#### Groovy
Add the repo:
```groovy
maven {
    name "petarmcReleases"
    url "https://maven.petarmc.com/releases"
}
```

Add dependency:

- For v2.0.0-alpha.3 and above (new structure):
```groovy
implementation "com.petarmc.petarlib:petarlib-fabric:2.x.x"
```

- For older versions (legacy coordinates):
```groovy
implementation "com.petarmc:lib:1.x.x"
```

## Building

This project uses Gradle. To build the library:

```bash
./gradlew build
```

To build for multiple Minecraft versions:

```bash
./gradlew buildAllVers
```

This will generate versioned JARs in `build/libs-versioned/`.

## Running tests

Unit tests are configured with JUnit 5 (Jupiter).

- Run the full test suite:
```bash
.\gradlew.bat test
```

- Run a single test class or method (example):
```bash
.\gradlew.bat --tests com.petarmc.petarlib.chat.ChatPatternMatcherTest test
```

Test reports are generated after a test run at:

- HTML report: `build/reports/tests/test/index.html`
- Raw results: `build/test-results/test/`

Notes:
- The project already includes the JUnit 5 dependency in `build.gradle`. If you encounter issues running tests, ensure your Gradle cache is healthy and dependencies resolve (`./gradlew --refresh-dependencies`).

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.