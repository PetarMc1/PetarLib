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
```kotlin
implementation("com.petarmc:lib:2.x.x")
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
```groovy
implementation "com.petarmc:lib:2.x.x"
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
.\gradlew.bat --tests com.petarmc.lib.chat.ChatPatternMatcherTest test
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