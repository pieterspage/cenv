# Cenv

A lightweight Java library for resolving configuration values from JVM system properties and OS environment variables through a single, consistent API.

When you look up a key, Cenv first checks JVM system properties (`-Dkey=value`), then falls back to OS environment variables. Instead of writing this everywhere:

```java
String value = System.getProperty("MY_KEY");
if (value == null || value.isEmpty()) {
    value = System.getenv("MY_KEY");
}
```

You write:

```java
String value = new Cenv("MY_KEY").getValue();
```

This makes your application config portable across local development, CI pipelines, and containerised environments where config may be injected as either a JVM flag or an environment variable.

Published to Maven Central under `page.pieters:cenv`.

## Requirements

- Java 11+
- Gradle 8.14.4

## Installation

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("page.pieters:cenv:0.1.0")
}
```

### Gradle (Groovy DSL)

```groovy
dependencies {
    implementation 'page.pieters:cenv:0.1.0'
}
```

### Maven

```xml
<dependency>
    <groupId>page.pieters</groupId>
    <artifactId>cenv</artifactId>
    <version>0.1.0</version>
</dependency>
```

---

## Building

Clone the repository and build with the Gradle wrapper:

```bash
./gradlew build
```

This compiles the source, runs all tests, and produces the JAR under `build/libs/`.

To build without running tests:

```bash
./gradlew assemble
```

---

## Testing

Run the full test suite:

```bash
./gradlew test
```

Test reports are written to `build/reports/tests/test/index.html`.

---

## Publishing to Maven Central

Add the following to `~/.gradle/gradle.properties`:

```properties
centralUsername=<portal-token-username>
centralPassword=<portal-token-password>
signingKey=-----BEGIN PGP PRIVATE KEY BLOCK-----\n...\n-----END PGP PRIVATE KEY BLOCK-----
signingPassword=<gpg-key-passphrase>
```

Then run:

```bash
./gradlew publishToMavenSona


```

Log in to [central.sonatype.com](https://central.sonatype.com), go to **Deployments**, and click **Release**.

---

## License

Apache License, Version 2.0 — see [LICENSE](LICENSE) for details.
