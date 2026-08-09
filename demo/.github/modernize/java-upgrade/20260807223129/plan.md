# Upgrade Plan: demo (20260807223129)

- **Generated**: 2026-08-07 22:31:29
- **HEAD Branch**: main
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 26.0.1: /usr/lib/jvm/jdk-26.0.1-oracle-x64/bin (current system JDK, used by Step 1 and verification)
- JDK 21: **<TO_BE_INSTALLED>** (required by Step 1)

**Build Tools**
- Maven Wrapper: 3.9.16: `.mvn/wrapper/maven-wrapper.properties`

## Guidelines

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260807223129
- Run tests before and after the upgrade: true

## Upgrade Goals

- Upgrade Java runtime to Java 21

## Technology Stack

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
| --------------------- | ------- | -------------- | ---------------- |
| Java | 17 | 21 | User requested latest LTS runtime upgrade |
| Spring Boot | 4.1.0 | 4.1.0 | Already compatible with Java 21 |
| Maven Wrapper | 3.9.16 | 3.9.0 | Compatible with Java 21 |
| spring-boot-maven-plugin | managed by parent | managed by parent | No direct change needed |

## Derived Upgrades

- Java 21 → update `<java.version>` to `21` in `pom.xml`.
- Maven Wrapper 3.9.16 is already compatible; no wrapper upgrade required.
- No additional Spring Boot or plugin upgrades are required because Spring Boot 4.1.0 already supports Java 21.

## Impact Analysis

### Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|------------|---------|--------|--------|--------|
| pom.xml | `<java.version>` | 17 | upgrade | 21 | User requested latest LTS runtime |

### Source Code Changes

No source code changes are required for this runtime upgrade. The current Spring Boot 4.1.0 application should remain compatible when compiled for Java 21.

### Configuration Changes

No application or config file changes are required for this upgrade.

### CI/CD Changes

No CI/CD configuration files were detected that require updates for Java 21 in this scope.

### Risks & Warnings

- **JDK 21 installation required**: The workspace currently does not have JDK 21 installed. Step 1 must install it or use the available JDK 26 as a fallback in degraded mode.
- **Baseline JDK unavailable**: Current project JDK 17 is not present on the system. Baseline verification will be skipped if JDK 17 is not installed.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Ensure the required Java runtime is available before changing project configuration.
  - **Changes to Make**: Install JDK 21 if needed, verify Maven wrapper availability.
  - **Verification**: `./mvnw -version` with JDK 21 installed and available.

- Step 2: Setup Baseline
  - **Rationale**: Capture current build/test baseline only if current Java 17 is available, otherwise skip.
  - **Changes to Make**: Skip if JDK 17 unavailable.
  - **Verification**: `./mvnw clean compile test-compile -q && ./mvnw clean test -q` with current JDK 17 (if available).

- Step 3: Apply Java 21 Runtime Upgrade
  - **Rationale**: Update the project runtime target to the requested latest LTS version.
  - **Changes to Make**: Update `pom.xml` `<java.version>` from `17` to `21`.
  - **Verification**: `./mvnw clean test-compile -q` using JDK 21.

- Step 4: CVE Validation & Fix
  - **Rationale**: Scan direct dependencies for known vulnerabilities and fix any issues after the runtime upgrade.
  - **Changes to Make**: Run direct dependency CVE scan, apply patch upgrades as needed.
  - **Verification**: `./mvnw clean test-compile -q` and `#appmod-validate-cves-for-java` re-scan.

- Step 5: Final Validation
  - **Rationale**: Confirm the upgrade is complete and the project builds and tests successfully on Java 21.
  - **Changes to Make**: Resolve any test failures or remaining issues.
  - **Verification**: `./mvnw clean test -q` with JDK 21.
