---
name: banglish-data-generator
project: Banglish Data Generator
project_name: banglish-data-generator
description: Build, test, document, run, and maintain a reusable Java library that generates Banglish text and synthetic Bangladesh-specific test data, with Gradle and Maven support.
---

# Banglish Data Generator — Development Skill

**Project Name:** `banglish-data-generator` (Display: **Banglish Data Generator**)  
**Root Folder:** `banglish-data-generator/`  
**Current Implementation Status:** Complete & Verified (v1.0.0, 114/114 Tests Passing)

## Purpose

Use this skill to develop, run, and maintain the production-quality, reusable Java library named **Banglish Data Generator** (`banglish-data-generator`).

The library provides synthetic Banglish text and Bangladesh-specific test data for QA engineers, developers, API testing, database seeding, and automation frameworks.

Reference repositories:

- https://github.com/morluto/rea
- https://github.com/mdeanda/lorem

Use `mdeanda/lorem` to study Java library API design, project structure, generator patterns, build configuration, and documentation. Use `morluto/rea` as an investigation aid only if its tools or integration are available. Do not modify either reference repository. Do not copy implementation code without checking and complying with its license.

## Working principles

1. Inspect the current workspace before making changes.
2. If the workspace is empty, scaffold the project. If it already contains work, preserve it and adapt the existing structure.
3. Prepare a concise implementation plan before coding.
4. Prefer Java 17 as the minimum supported version.
5. Keep the library lightweight. Zero runtime dependencies: avoid Spring Boot, databases, and third-party runtime libraries.
6. Use curated, maintainable datasets for Bangladesh-specific information.
7. Never claim a generated phone number is unassigned or safe to contact.
8. Do not invent postal codes. Select postal codes only from the verified Bangladesh Post Office (BPO) dataset.
9. Do not commit credentials, tokens, private keys, or personal data.
10. Implement the project fully; do not stop after creating a skeleton.

## Verified project coordinates

- Project name: `banglish-data-generator`
- Group ID: `com.hiromia`
- Artifact ID: `banglish-data-generator`
- Current version: `1.0.0`
- Base package: `com.hiromia.banglish`
- Java compatibility: 17+
- Main Class: `com.hiromia.banglish.Main`

## Implemented capabilities

### 1. Banglish text generation

Generates natural-looking Banglish text using Roman characters to represent spoken Bengali.

Implemented in `TextGenerator.java`:

- `getWord()` : Returns a single lowercase Banglish word.
- `getWords(int min, int max)` : Returns between `min` and `max` words without immediate consecutive repetition.
- `getSentence()` : Returns a capitalized sentence ending with punctuation (`.` or `?`).
- `getSentences(int min, int max)` : Returns between `min` and `max` sentences separated by a space.
- `getParagraph()` : Returns a paragraph (3–6 sentences).
- `getParagraphs(int min, int max)` : Returns between `min` and `max` paragraphs separated by `\n\n`.
- `getTitle(int min, int max)` : Returns title-cased words.

Validation rules:
- Validates all range arguments (`min >= 0`, `max >= min`), throwing `IllegalArgumentException` with clear messages.
- Deterministic output supported via `BanglishGenerator.getInstance(seed)`.

### 2. Bangladeshi name generation

Implemented in `NameGenerator.java`:

- `getName()` : Returns full name (male or female).
- `getFirstName()` : Returns common first name.
- `getLastName()` : Returns common family surname.
- `getMaleName()` : Returns male full name (first + surname).
- `getFemaleName()` : Returns female full name (first + surname).

Disclaimer: Generated names are purely synthetic test records.

### 3. Bangladesh mobile and phone generation

Implemented in `PhoneGenerator.java`:

- `getMobileNumber()` : Generates 11-digit local format (`01XXXXXXXXX`).
- `getPhone()` : Alias for `getMobileNumber()`.
- `getPhoneWithCountryCode()` : Generates international format (`+8801XXXXXXXXX`, 14 chars).
- `getMobileNumber(String operatorPrefix)` : Generates 11-digit number with specified prefix.

Supported operator prefixes:
- `013`, `017` (Grameenphone)
- `014`, `019` (Banglalink)
- `015` (Teletalk)
- `016` (Airtel)
- `018` (Robi)

Throws `IllegalArgumentException` for invalid or unknown operator prefixes.

### 4. Bangladesh postal-code generation

Implemented in `PostalCodeGenerator.java`:

- `getZipCode()` : Returns a verified 4-digit postal code.
- `getPostalCode()` : Alias for `getZipCode()`.
- `getZipCode(String district)` : Returns a verified 4-digit code for the specified district (case-insensitive).

Dataset: Sourced from verified Bangladesh Post Office (BPO) records covering all 64 districts. Throws `IllegalArgumentException` if the district is unrecognized.

### 5. Bangladesh location generation

Implemented in `LocationGenerator.java`:

- `getCity()` : Random prominent city/locality.
- `getDistrict()` : Random district across all 64 districts.
- `getDivision()` : Random administrative division across all 8 divisions.
- `getCountry()` : Returns `"Bangladesh"`.
- `getDistrict(String division)` : Random district in the specified division.
- `getDistrictsByDivision(String division)` : List of all districts in the division.
- `getCity(String district)` : Random city/locality in the specified district.
- `getCitiesByDistrict(String district)` : List of all cities in the district.

All 8 administrative divisions:
1. Dhaka (13 districts)
2. Chattogram (11 districts)
3. Rajshahi (8 districts)
4. Khulna (10 districts)
5. Barishal (6 districts)
6. Sylhet (4 districts)
7. Rangpur (8 districts)
8. Mymensingh (4 districts)

### 6. Bangladesh citizen identification & document number generation

Generates authentic, standardized Bangladesh citizen identification numbers and official document identifiers for database seeding, test automation, and validation frameworks.

#### Standardized Field Mapping Table

| Your field name | Recommended Java field name | Description | Format & Specification |
|---|---|---|---|
| `nationalVoterNo` | `nationalIdNumber` | National ID (NID) Number | 17 digits (standard computerized: `YYYY` + 13 digits), 13 digits, or 10 digits |
| `JonmnibondonNo` | `birthRegistrationNumber` | Birth Registration Number | 17 digits (BDRIS format: `YYYY` + 2d district + 2d upazila + 2d union + 7d serial) |
| `smartCardNo` | `smartCardNumber` | Smart NID Card Number | 10 digits (first digit 1–9, followed by 9 digits) |
| `passordNo` | `passportNumber` | Bangladesh Passport Number | 9 characters (1 uppercase series letter like `A`, `B`, `E`, `P` + 8 digits) |
| `driving lincenceId` | `drivingLicenseNumber` | BRTA Driving Licence Number | 9 characters (2-letter BRTA circle code like `DK`, `CT`, `SY` + 7 digits) |

Implements in `IdentificationGenerator.java`:

- `getNationalIdNumber()` : Returns standard 17-digit computerized NID (birth year 1950–2006 + 13 numeric digits).
- `getNationalIdNumber(int length)` : Supports 10, 13, or 17 digit formats. Throws `IllegalArgumentException` for unsupported lengths.
- `getNationalVoterNo()` / `getNationalVoterNo(int length)` : Backward-compatible aliases for `getNationalIdNumber()`.
- `getBirthRegistrationNumber()` : Returns strictly 17-digit BDRIS-compliant birth certificate number.
- `getJonmnibondonNo()` : Backward-compatible alias for `getBirthRegistrationNumber()`.
- `getSmartCardNumber()` : Returns 10-digit polymer Smart NID card number (`^[1-9][0-9]{9}$`).
- `getSmartCardNo()` : Backward-compatible alias for `getSmartCardNumber()`.
- `getPassportNumber()` : Returns 9-character MRP / e-Passport number (`^[A-Z][0-9]{8}$`).
- `getPassportNumber(char series)` / `getPassportNumber(String series)` : Returns 9-character passport number for specific series letter (e.g. 'A', 'E').
- `getPassordNo()` : Backward-compatible alias for `getPassportNumber()`.
- `getDrivingLicenseNumber()` : Returns 9-character BRTA licence number (`^[A-Z]{2}[0-9]{7}$`).
- `getDrivingLicenseNumber(String circleCode)` : Returns 9-character licence for specific 2-letter BRTA circle code (e.g. "DK" for Dhaka, "CT" for Chattogram).
- `getDrivingLincenceId()` / `getDrivingLicenseId()` : Backward-compatible aliases for `getDrivingLicenseNumber()`.
- `getCitizenIdentification()` / `getIdentification()` : Returns a complete `CitizenIdentification` POJO populated with all 5 standardized fields.

#### Model Representation (`com.hiromia.banglish.model.CitizenIdentification`)

A strongly-typed, immutable record holding all standardized citizen identification numbers:
- `getNationalIdNumber()` (alias: `getNationalVoterNo()`)
- `getBirthRegistrationNumber()` (alias: `getJonmnibondonNo()`)
- `getSmartCardNumber()` (alias: `getSmartCardNo()`)
- `getPassportNumber()` (alias: `getPassordNo()`)
- `getDrivingLicenseNumber()` (alias: `getDrivingLincenceId()`)

## Architecture and File Structure

```text
banglish-data-generator/
├── src/
│   ├── main/java/com/hiromia/banglish/
│   │   ├── Banglish.java                 # Public API interface
│   │   ├── BanglishGenerator.java        # Main facade & thread-safe singleton
│   │   ├── Main.java                     # Executable demonstration entrypoint
│   │   ├── TextGenerator.java            # Text & vocabulary generation
│   │   ├── NameGenerator.java            # Bangladeshi names
│   │   ├── PhoneGenerator.java           # Bangladesh phone & mobile
│   │   ├── PostalCodeGenerator.java      # Verified 4-digit postal codes
│   │   ├── LocationGenerator.java        # Divisions, districts, and cities
│   │   ├── IdentificationGenerator.java  # Citizen identification & document numbers
│   │   ├── model/
│   │   │   ├── Division.java             # 8 administrative divisions enum
│   │   │   ├── District.java             # District entity
│   │   │   └── CitizenIdentification.java # Standardized citizen identification record
│   │   └── data/
│   │       ├── BanglishWords.java        # Vocabulary & templates
│   │       ├── BangladeshiNames.java     # Names dataset
│   │       ├── PhonePrefixes.java        # Mobile operator prefixes
│   │       ├── LocationData.java         # 64-district LGED/BPO dataset
│   │       └── IdentificationData.java   # BRTA circle codes & passport series
│   └── test/java/com/hiromia/banglish/
│       ├── BanglishGeneratorTest.java    # Integration & deterministic seeding
│       ├── TextGeneratorTest.java        # Text bounds, validation & formatting
│       ├── NameGeneratorTest.java        # Name patterns
│       ├── PhoneGeneratorTest.java       # Mobile formatting & prefixes
│       ├── PostalCodeGeneratorTest.java  # Verified postal codes & lookup
│       ├── LocationGeneratorTest.java    # Division & district relations
│       ├── IdentificationGeneratorTest.java # NID, BRN, Smart card, Passport, BRTA licence
│       └── ThreadSafetyTest.java         # Multi-threaded concurrent execution
├── build.gradle                          # Gradle build configuration
├── settings.gradle                       # Gradle project settings
├── pom.xml                               # Maven POM configuration
├── gradlew / gradlew.bat                 # Gradle wrapper
├── README.md                             # Documentation & usage guide
├── LICENSE                               # Apache 2.0 License
└── .gitignore                            # Git ignore configuration
```

## Testing Framework & TestNG Dependency Configuration

The project utilizes **TestNG** as its primary unit and integration testing framework.

### Gradle Configuration (`build.gradle`)

In `build.gradle`, configure TestNG dependencies and enable `useTestNG()`:

```groovy
dependencies {
    testImplementation 'org.testng:testng:7.10.2'
}

tasks.named('test') {
    useTestNG()
    testLogging {
        events "passed", "skipped", "failed"
    }
}
```

### Maven Configuration (`pom.xml`)

In `pom.xml`, configure TestNG under `<dependencies>`:

```xml
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.10.2</version>
    <scope>test</scope>
</dependency>
```

### TestNG Testing Guidelines
- Use `@Test(description = "...")` for test method documentation.
- Use `@BeforeMethod` for pre-test setup and deterministic random initialization.
- Use `@DataProvider(name = "...")` for parameterized input validation and negative tests.
- Use `org.testng.Assert.*` for assertions (`assertEquals`, `assertTrue`, `assertFalse`, `assertNotNull`, `assertSame`, `assertThrows`).

## Running and Testing the Project

### Execute the Demo Application

- **Using Gradle:**
  ```bash
  ./gradlew run
  ```
- **Using Maven:**
  ```bash
  mvn compile exec:java
  ```
- **Using Built JAR:**
  ```bash
  java -jar build/libs/banglish-data-generator-1.0.0.jar
  # or
  java -jar target/banglish-data-generator-1.0.0.jar
  ```

### Run Tests and Build

Both build systems are fully configured and verified independently:

- **Gradle Build & Tests:**
  ```bash
  ./gradlew clean test build
  ```
- **Maven Build & Tests:**
  ```bash
  mvn clean test package
  ```

Test suite status: **114 tests executed, 0 failures, 0 errors, 0 skipped**.

### Local Publishing

- **Publish to Local Maven Repository via Gradle:**
  ```bash
  ./gradlew publishToMavenLocal
  ```
- **Install to Local Maven Repository via Maven:**
  ```bash
  mvn clean install
  ```

Installed artifact coordinates:
```xml
<dependency>
    <groupId>com.hiromia</groupId>
    <artifactId>banglish-data-generator</artifactId>
    <version>1.0.0</version>
</dependency>
```

```groovy
implementation 'com.hiromia:banglish-data-generator:1.0.0'
```

## Maven Central Publishing Checklist

- [x] Java 17+ compliant bytecode (`release 17`).
- [x] Sources JAR and Javadoc JAR generation configured.
- [x] Complete POM metadata (URL, licenses, developers, SCM, description).
- [x] Zero external runtime dependencies.
- [ ] Sign artifacts using GPG (`maven-gpg-plugin` / `gradle-signing-plugin`).
- [ ] Sonatype Central Portal namespace claim for `com.hiromia`.
