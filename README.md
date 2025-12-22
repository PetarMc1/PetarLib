# PetarLib

A utility library, providing essential tools for chat handling, logging, networking, and task scheduling.
Its used in my mods like [the Fish Tracker mod](https://github.com/PetarMc1/change)

## Features

PetarLib includes several modules to simplify mod development:

- **Chat Module**: Advanced chat pattern matching and handling utilities
- **Log Module**: Flexible logging system with configurable levels and outputs
- **Net Module**: HTTP client wrapper for network operations
- **Task Module**: Task scheduling and management utilities

## Usage

### Maven
Add the repo:
```xml
<repository>
  <id>petarmc-releases</id>
  <name>Reposilite Repository</name>
  <url>https://maven.petarmc.com/releases</url>
</repository>
```

Add dependency:
```xml
<dependency>
  <groupId>com.petarmc</groupId>
  <artifactId>lib</artifactId>
  <version>x.x.x</version>
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
implementation("com.petarmc:lib:1.0.0")
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
implementation "com.petarmc:lib:1.0.0"
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

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```