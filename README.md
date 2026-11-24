# Banglish Data Generator

[![Java Version](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://openjdk.org/projects/jdk/17/)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](LICENSE)
[![Build Tool](https://img.shields.io/badge/Build-Gradle%20%7C%20Maven-orange.svg)](pom.xml)

A lightweight, zero-dependency Java 17+ library designed for generating realistic **Banglish text** (Romanized conversational Bengali) and synthetic **Bangladesh-specific test data** (names, phone numbers, postal codes, and administrative locations).

Built specifically for QA automation engineers, software developers, API test payload generation, database seeders, and load testing frameworks.

---

## Features

- **Banglish Text Generation**: Produces natural-sounding Banglish words, sentences, titles, and paragraphs using Roman characters.
- **Bangladeshi Names**: Generates authentic-style Bangladeshi male and female first names, last names, and full names.
- **Mobile Number Generation**: Generates 11-digit local (`01XXXXXXXXX`) and international (`+8801XXXXXXXXX`) phone numbers with validated operator prefixes (`013`, `014`, `015`, `016`, `017`, `018`, `019`).
- **Verified Postal Codes**: Four-digit postal codes verified from official Bangladesh Post Office records across all 64 districts.
- **Administrative Location Data**: Complete coverage of all 8 divisions, 64 districts, and major cities/localities with hierarchical filtering.
- **Citizen Identification & Documents**: Standardized generators for National ID (NID, 17/13/10 digits), Birth Registration (17 digits), Smart NID Card (10 digits), Bangladesh Passport (9 chars), and BRTA Driving Licence (9 chars).
- **Zero Runtime Dependencies**: Ultra-lightweight footprint with zero external runtime dependencies.
- **Thread-Safe & Deterministic**: Safe for concurrent test execution and supports configurable seeds for reproducible test runs.
- **Dual Build Support**: Fully configured for both Gradle and Maven.

---

## Installation

### Gradle

Add the dependency to your `build.gradle`:

```groovy
dependencies {
    implementation 'com.hiromia:banglish-data-generator:1.0.0'
}
```

### Maven

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.hiromia</groupId>
    <artifactId>banglish-data-generator</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## Quick Start

```java
import com.hiromia.banglish.Banglish;
import com.hiromia.banglish.BanglishGenerator;

public class QuickStart {
    public static void main(String[] args) {
        Banglish banglish = BanglishGenerator.getInstance();

        // Banglish text
        String word = banglish.getWord();
        String words = banglish.getWords(5, 10);
        String sentence = banglish.getSentence();
        String paragraph = banglish.getParagraph();
        String title = banglish.getTitle(3, 6);

        // Bangladeshi names
        String fullName = banglish.getName();
        String maleName = banglish.getMaleName();
        String femaleName = banglish.getFemaleName();

        // Phone numbers
        String mobile = banglish.getMobileNumber();             // e.g. "01712345678"
        String international = banglish.getPhoneWithCountryCode(); // e.g. "+8801712345678"
        String gpNumber = banglish.getMobileNumber("017");       // specific operator

        // Postal codes
        String postalCode = banglish.getZipCode();              // e.g. "1205"
        String dhakaZip = banglish.getZipCode("Dhaka");          // e.g. "1000"

        // Locations
        String city = banglish.getCity();                       // e.g. "Dhanmondi"
        String district = banglish.getDistrict();               // e.g. "Bogura"
        String division = banglish.getDivision();               // e.g. "Rajshahi"
        String country = banglish.getCountry();                 // "Bangladesh"

        // Citizen Identification & Document Numbers (Standardized Field Names)
        String nid = banglish.getNationalIdNumber();             // e.g. "19881234567890123" (17 digits)
        String brn = banglish.getBirthRegistrationNumber();      // e.g. "19952601011234567" (17 digits)
        String smartCard = banglish.getSmartCardNumber();        // e.g. "1928374650" (10 digits)
        String passport = banglish.getPassportNumber();          // e.g. "A01234567" (9 characters)
        String drivingLicence = banglish.getDrivingLicenseNumber(); // e.g. "DK1234567" (9 characters)
    }
}
```

---

## API Capabilities & Usage

### 1. Banglish Text Generation

Generates conversational Banglish text using Roman alphabet representations of spoken Bengali.

| Method | Return Type | Description |
|---|---|---|
| `getWord()` | `String` | Returns a single random Banglish word |
| `getWords(int min, int max)` | `String` | Returns between `min` and `max` words separated by spaces |
| `getSentence()` | `String` | Returns a capitalized sentence ending with `.` or `?` |
| `getSentences(int min, int max)` | `String` | Returns multiple sentences separated by spaces |
| `getParagraph()` | `String` | Returns a single paragraph (3–6 sentences) |
| `getParagraphs(int min, int max)`| `String` | Returns paragraphs separated by `\n\n` |
| `getTitle(int min, int max)` | `String` | Returns a Title-Cased string of words |

```java
Banglish banglish = BanglishGenerator.getInstance();

// "Ami ajke office e jabo."
System.out.println(banglish.getSentence());

// "Kalke Amader Meeting Ache"
System.out.println(banglish.getTitle(3, 5));
```

### 2. Bangladeshi Name Generation

Curated collections of common Bangladeshi first names and surnames.

| Method | Return Type | Description |
|---|---|---|
| `getName()` | `String` | Random male or female full name |
| `getFirstName()` | `String` | Random first name (male or female) |
| `getLastName()` | `String` | Random family surname |
| `getMaleName()` | `String` | Full male name (e.g. "Tanvir Ahmed") |
| `getFemaleName()` | `String` | Full female name (e.g. "Sadia Rahman") |

### 3. Mobile and Phone Generation

Generates authentic 11-digit numbers conforming to Bangladesh telecommunication standards.

| Method | Format | Example |
|---|---|---|
| `getMobileNumber()` | `01XXXXXXXXX` (11 digits) | `01712345678` |
| `getPhone()` | Alias for `getMobileNumber()` | `01898765432` |
| `getPhoneWithCountryCode()` | `+8801XXXXXXXXX` (14 chars) | `+8801712345678` |
| `getMobileNumber("017")` | Starts with specific prefix | `01784920183` |

Supported operator prefixes:
- `013`, `017` : Grameenphone
- `014`, `019` : Banglalink
- `015` : Teletalk
- `016` : Airtel
- `018` : Robi

### 4. Verified Bangladesh Postal Codes

All postal codes are strictly verified against official Bangladesh Post Office (BPO) datasets covering all 64 districts. The library never fabricates random 4-digit codes.

```java
// Random verified postal code from across Bangladesh
String code = banglish.getZipCode();

// District-specific verified code
String sylhetZip = banglish.getZipCode("Sylhet");       // e.g. "3100"
String chattogramZip = banglish.getZipCode("Chattogram"); // e.g. "4000"
```

### 5. Administrative Locations

Full support for all 8 administrative divisions and 64 districts.

```java
// Divisions: Dhaka, Chattogram, Rajshahi, Khulna, Barishal, Sylhet, Rangpur, Mymensingh
String division = banglish.getDivision();

// Random district among all 64 districts
String district = banglish.getDistrict();

// Filter districts by division
String chittagongDistrict = banglish.getDistrict("Chattogram");
List<String> dhakaDistricts = banglish.getDistrictsByDivision("Dhaka");

// Filter cities by district
String cityInBogura = banglish.getCity("Bogura");
List<String> citiesInDhaka = banglish.getCitiesByDistrict("Dhaka");
```

### 6. Bangladesh Citizen Identification & Document Numbers

Generates standardized and authentic synthetic identification numbers and official government document formats for QA automation, payload testing, and database seeding.

#### Standardized Field Mapping

| Your field name | Recommended Java field name | Description | Format / Specification |
|---|---|---|---|
| `nationalVoterNo` | `nationalIdNumber` | National ID (NID) Number | 17 digits (computerized: `YYYY` + 13d), 13 digits, or 10 digits |
| `JonmnibondonNo` | `birthRegistrationNumber` | Birth Registration Number | 17 digits (BDRIS format: `YYYY` + 2d geo + 2d upazila + 2d union + 7d serial) |
| `smartCardNo` | `smartCardNumber` | Smart NID Card Number | 10 digits (first digit 1–9, followed by 9 digits) |
| `passordNo` | `passportNumber` | Bangladesh Passport Number | 9 characters (1 uppercase series letter + 8 digits) |
| `driving lincenceId` | `drivingLicenseNumber` | BRTA Driving Licence Number | 9 characters (2-letter circle code + 7 digits) |

```java
Banglish banglish = BanglishGenerator.getInstance();

// 1. National ID (NID) Number
String nid17 = banglish.getNationalIdNumber();       // e.g. "19881234567890123" (17 digits)
String nid10 = banglish.getNationalIdNumber(10);     // e.g. "1928374650" (10 digits)
String nid13 = banglish.getNationalIdNumber(13);     // e.g. "1234567890123" (13 digits)
String voterNo = banglish.getNationalVoterNo();      // alias for getNationalIdNumber()

// 2. Birth Registration Number
String brn = banglish.getBirthRegistrationNumber();  // e.g. "19952601011234567" (17 digits)
String jonmoNo = banglish.getJonmnibondonNo();       // alias for getBirthRegistrationNumber()

// 3. Smart NID Card Number
String smartCard = banglish.getSmartCardNumber();    // e.g. "1928374650" (10 digits)
String smartCardAlias = banglish.getSmartCardNo();   // alias for getSmartCardNumber()

// 4. Bangladesh Passport Number
String passport = banglish.getPassportNumber();      // e.g. "A01234567" (9 characters)
String offPassport = banglish.getPassportNumber('E');// e.g. "E01234567" (Official)
String passAlias = banglish.getPassordNo();          // alias for getPassportNumber()

// 5. BRTA Driving Licence Number
String licence = banglish.getDrivingLicenseNumber(); // e.g. "DK1234567" (9 characters)
String dhakaLicence = banglish.getDrivingLicenseNumber("DK"); // Dhaka circle
String dlAlias = banglish.getDrivingLincenceId();    // alias for getDrivingLicenseNumber()

// 6. CitizenIdentification POJO
CitizenIdentification citizen = banglish.getCitizenIdentification();
System.out.println(citizen.getNationalIdNumber());
System.out.println(citizen.getBirthRegistrationNumber());
System.out.println(citizen.getSmartCardNumber());
System.out.println(citizen.getPassportNumber());
System.out.println(citizen.getDrivingLicenseNumber());
```

---

## Deterministic Seeding & Thread Safety

### Deterministic Seeding

For repeatable test fixtures, instantiate `Banglish` with a fixed seed:

```java
Banglish gen1 = BanglishGenerator.getInstance(42L);
Banglish gen2 = BanglishGenerator.getInstance(42L);

// Guaranteed to produce identical test data sequences
assert gen1.getSentence().equals(gen2.getSentence());
assert gen1.getMobileNumber().equals(gen2.getMobileNumber());
```

### Thread Safety

`BanglishGenerator.getInstance()` is thread-safe and can be safely shared across concurrent execution threads in parallel JUnit, TestNG, or Cucumber runners without locking contention.

---

## Test Automation Integration Examples

### REST Assured API Payload

```java
import io.restassured.RestAssured;
import com.hiromia.banglish.Banglish;
import com.hiromia.banglish.BanglishGenerator;
import org.testng.annotations.Test;
import java.util.Map;

class UserApiTest {
    private final Banglish banglish = BanglishGenerator.getInstance();

    @Test
    void testCreateCustomerProfile() {
        Map<String, Object> payload = Map.of(
            "fullName", banglish.getName(),
            "phone", banglish.getPhoneWithCountryCode(),
            "district", banglish.getDistrict(),
            "postalCode", banglish.getZipCode(),
            "notes", banglish.getSentence()
        );

        RestAssured.given()
            .header("Content-Type", "application/json")
            .body(payload)
            .when()
            .post("/api/v1/customers")
            .then()
            .statusCode(201);
    }
}
```

### Selenium / Playwright Web Form

```java
import com.microsoft.playwright.*;
import com.hiromia.banglish.Banglish;
import com.hiromia.banglish.BanglishGenerator;

public class RegistrationE2ETest {
    public static void main(String[] args) {
        Banglish banglish = BanglishGenerator.getInstance();

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate("https://example.com/bd/signup");

            page.fill("#name", banglish.getName());
            page.fill("#phone", banglish.getMobileNumber("017"));
            page.fill("#city", banglish.getCity("Dhaka"));
            page.fill("#zip", banglish.getZipCode("Dhaka"));
            page.fill("#bio", banglish.getParagraph());

            page.click("#submit");
        }
    }
}
```

---

## Sample Generated Data

```text
Sentence:        Ami ajke office e jabo.
Sentence:        Kalke amader meeting ache.
Sentence:        Apni kemon achen?
Words:           khub sundor ebong shohoj ekta system
Title:           Ajker Office Meeting Plan
Male Name:       Tanvir Ahmed
Female Name:     Sadia Rahman
Mobile (Local):  01784920183
Mobile (Intl):   +8801837492014
Postal Code:     1205
Division:        Chattogram
District:        Cox's Bazar
City:            Teknaf
Country:         Bangladesh
National ID:     19840443056966558
Birth Reg No:    20015711267684637
Smart Card No:   2404229740
Passport No:     A59382716
Driving Licence: DK0368352
```

---

## Build and Testing

### Prerequisites

- **Java JDK**: 17 or higher
- **Gradle**: 8.0+ (or use included `./gradlew`)
- **Maven**: 3.8+

### Build with Gradle

```bash
# Compile and run TestNG tests
./gradlew clean test

# Build JAR, Sources JAR, and Javadoc JAR
./gradlew build
```

### Build with Maven

```bash
# Compile, run tests, and package JARs
mvn clean test package
```

---

## Publishing Guide

### 1. Local Publication

#### Using Gradle:
```bash
./gradlew publishToMavenLocal
```

#### Using Maven:
```bash
mvn clean install
```

### 2. GitHub Packages

To publish to GitHub Packages:

1. Configure authentication in `~/.m2/settings.xml` or environment variables (`GITHUB_ACTOR` and `GITHUB_TOKEN`).
2. Add the repository to `pom.xml` or `build.gradle`:
   ```xml
   <distributionManagement>
       <repository>
           <id>github</id>
           <name>GitHub Packages</name>
           <url>https://maven.pkg.github.com/OWNER/REPOSITORY</url>
       </repository>
   </distributionManagement>
   ```
3. Run:
   ```bash
   mvn deploy -DaltDeploymentRepository=github::https://maven.pkg.github.com/OWNER/REPOSITORY
   ```

### 3. Maven Central Checklist

Before publishing to Maven Central via Sonatype OSSRH / Central Portal:

- [x] Java 17+ compliant bytecode.
- [x] Complete Javadoc and Source JAR generation.
- [x] Standard POM metadata: Group ID (`com.hiromia`), Artifact ID (`banglish-data-generator`), Version, Name, Description, URL, License, Developers, SCM.
- [x] Zero unapproved third-party dependencies.
- [ ] GPG signature verification: configure `maven-gpg-plugin` or `gradle-signing-plugin` with valid signing key.
- [ ] Sonatype Central Portal account & namespace verification for `com.hiromia`.

---

## Dataset References & Disclaimers

### Data Sources
- **Administrative Divisions and Districts**: Local Government Engineering Department (LGED), Bangladesh National Portal.
- **Postal Codes**: Bangladesh Post Office (BPO) verified postal code records (2026 dataset).

### Safety Disclaimer
All generated personal names, telephone numbers, and addresses are strictly synthetic test records. Generated phone numbers may coincidentally resemble active subscriber numbers; never use generated phone numbers to contact real persons.

---

## License

This library is distributed under the terms of the **Apache License, Version 2.0**. See [LICENSE](LICENSE) for details.
