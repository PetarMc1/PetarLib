# PetarLib
Library for all my minecraft mods.

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