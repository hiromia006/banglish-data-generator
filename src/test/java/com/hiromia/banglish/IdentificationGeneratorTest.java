package com.hiromia.banglish;

import com.hiromia.banglish.data.IdentificationData;
import com.hiromia.banglish.model.CitizenIdentification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

public class IdentificationGeneratorTest {

    private IdentificationGenerator generator;

    @BeforeMethod
    public void setUp() {
        generator = new IdentificationGenerator(new Random(42L));
    }

    // =========================================================================
    // 1. National ID (NID) Number / nationalVoterNo
    // =========================================================================

    @Test(description = "Default National ID number is 17 digits with valid birth year")
    public void testNationalIdNumberDefault() {
        for (int i = 0; i < 50; i++) {
            String nid = generator.getNationalIdNumber();
            assertNotNull(nid);
            assertEquals(nid.length(), 17, "Default NID must be 17 digits: " + nid);
            assertTrue(nid.matches("^[0-9]{17}$"), "NID must consist solely of digits: " + nid);

            int birthYear = Integer.parseInt(nid.substring(0, 4));
            assertTrue(birthYear >= 1950 && birthYear <= 2006,
                    "Birth year in 17-digit NID should be between 1950 and 2006: " + birthYear);
        }
    }

    @DataProvider(name = "validNidLengths")
    public Object[][] validNidLengths() {
        return new Object[][]{
                {10},
                {13},
                {17}
        };
    }

    @Test(dataProvider = "validNidLengths", description = "National ID number supports 10, 13, and 17 digit formats")
    public void testNationalIdNumberWithLength(int length) {
        for (int i = 0; i < 30; i++) {
            String nid = generator.getNationalIdNumber(length);
            assertNotNull(nid);
            assertEquals(nid.length(), length, "NID must have length " + length + ": " + nid);
            assertTrue(nid.matches("^[0-9]{" + length + "}$"), "NID must be purely numeric: " + nid);
        }
    }

    @DataProvider(name = "invalidNidLengths")
    public Object[][] invalidNidLengths() {
        return new Object[][]{
                {-1},
                {0},
                {9},
                {11},
                {12},
                {14},
                {16},
                {18},
                {20}
        };
    }

    @Test(dataProvider = "invalidNidLengths", expectedExceptions = IllegalArgumentException.class,
            description = "Unsupported NID lengths throw IllegalArgumentException")
    public void testNationalIdNumberInvalidLength(int length) {
        generator.getNationalIdNumber(length);
    }

    @Test(description = "Legacy nationalVoterNo alias works identically to getNationalIdNumber")
    public void testNationalVoterNoAlias() {
        IdentificationGenerator genA = new IdentificationGenerator(new Random(100L));
        IdentificationGenerator genB = new IdentificationGenerator(new Random(100L));

        assertEquals(genA.getNationalVoterNo(), genB.getNationalIdNumber());
        assertEquals(genA.getNationalVoterNo(10), genB.getNationalIdNumber(10));
        assertEquals(genA.getNationalVoterNo(13), genB.getNationalIdNumber(13));
        assertEquals(genA.getNationalVoterNo(17), genB.getNationalIdNumber(17));
    }

    // =========================================================================
    // 2. Birth Registration Number / JonmnibondonNo
    // =========================================================================

    @Test(description = "Birth Registration Number is strictly 17 digits adhering to BDRIS structure")
    public void testBirthRegistrationNumber() {
        for (int i = 0; i < 50; i++) {
            String brn = generator.getBirthRegistrationNumber();
            assertNotNull(brn);
            assertEquals(brn.length(), 17, "BRN must be exactly 17 digits: " + brn);
            assertTrue(brn.matches("^[0-9]{17}$"), "BRN must consist solely of digits: " + brn);

            int birthYear = Integer.parseInt(brn.substring(0, 4));
            assertTrue(birthYear >= 1950 && birthYear <= 2025,
                    "Birth year should be between 1950 and 2025: " + birthYear);

            int districtCode = Integer.parseInt(brn.substring(4, 6));
            assertTrue(districtCode >= 1 && districtCode <= 64,
                    "District geo-code should be between 01 and 64: " + districtCode);
        }
    }

    @Test(description = "Legacy JonmnibondonNo alias works identically to getBirthRegistrationNumber")
    public void testJonmnibondonNoAlias() {
        IdentificationGenerator genA = new IdentificationGenerator(new Random(200L));
        IdentificationGenerator genB = new IdentificationGenerator(new Random(200L));

        assertEquals(genA.getJonmnibondonNo(), genB.getBirthRegistrationNumber());
    }

    // =========================================================================
    // 3. Smart NID Card Number / smartCardNo
    // =========================================================================

    @Test(description = "Smart NID Card Number is strictly 10 digits starting with non-zero digit")
    public void testSmartCardNumber() {
        for (int i = 0; i < 50; i++) {
            String smartCard = generator.getSmartCardNumber();
            assertNotNull(smartCard);
            assertEquals(smartCard.length(), 10, "Smart card number must be exactly 10 digits: " + smartCard);
            assertTrue(smartCard.matches("^[1-9][0-9]{9}$"),
                    "Smart card number must start with 1-9 followed by 9 digits: " + smartCard);
        }
    }

    @Test(description = "Legacy smartCardNo alias works identically to getSmartCardNumber")
    public void testSmartCardNoAlias() {
        IdentificationGenerator genA = new IdentificationGenerator(new Random(300L));
        IdentificationGenerator genB = new IdentificationGenerator(new Random(300L));

        assertEquals(genA.getSmartCardNo(), genB.getSmartCardNumber());
    }

    // =========================================================================
    // 4. Bangladesh Passport Number / passordNo
    // =========================================================================

    @Test(description = "Passport number is 9 characters with uppercase letter followed by 8 digits")
    public void testPassportNumberDefault() {
        for (int i = 0; i < 50; i++) {
            String passport = generator.getPassportNumber();
            assertNotNull(passport);
            assertEquals(passport.length(), 9, "Passport number must be 9 characters: " + passport);
            assertTrue(passport.matches("^[A-Z][0-9]{8}$"),
                    "Passport number must match ^[A-Z][0-9]{8}$: " + passport);
        }
    }

    @Test(description = "Passport number supports custom valid series letter")
    public void testPassportNumberWithCustomSeries() {
        String passA = generator.getPassportNumber('A');
        assertTrue(passA.startsWith("A"));
        assertEquals(passA.length(), 9);

        String passE = generator.getPassportNumber('e'); // case-insensitive conversion
        assertTrue(passE.startsWith("E"));
        assertEquals(passE.length(), 9);

        String passStr = generator.getPassportNumber("B");
        assertTrue(passStr.startsWith("B"));
        assertEquals(passStr.length(), 9);
    }

    @DataProvider(name = "invalidPassportSeries")
    public Object[][] invalidPassportSeries() {
        return new Object[][]{
                {null},
                {""},
                {"   "},
                {"1"},
                {"AB"},
                {"@"}
        };
    }

    @Test(dataProvider = "invalidPassportSeries", expectedExceptions = IllegalArgumentException.class,
            description = "Invalid passport series strings throw IllegalArgumentException")
    public void testPassportNumberInvalidSeries(String series) {
        generator.getPassportNumber(series);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            description = "Non-letter char for passport series throws IllegalArgumentException")
    public void testPassportNumberInvalidCharSeries() {
        generator.getPassportNumber('9');
    }

    @Test(description = "Legacy passordNo alias works identically to getPassportNumber")
    public void testPassordNoAlias() {
        IdentificationGenerator genA = new IdentificationGenerator(new Random(400L));
        IdentificationGenerator genB = new IdentificationGenerator(new Random(400L));

        assertEquals(genA.getPassordNo(), genB.getPassportNumber());
    }

    // =========================================================================
    // 5. BRTA Driving Licence Number / driving lincenceId
    // =========================================================================

    @Test(description = "Driving licence number is 9 characters starting with valid BRTA circle code")
    public void testDrivingLicenseNumberDefault() {
        for (int i = 0; i < 50; i++) {
            String dl = generator.getDrivingLicenseNumber();
            assertNotNull(dl);
            assertEquals(dl.length(), 9, "Driving licence number must be 9 characters: " + dl);
            assertTrue(dl.matches("^[A-Z]{2}[0-9]{7}$"),
                    "Driving licence must match 2 uppercase letters + 7 digits: " + dl);

            String prefix = dl.substring(0, 2);
            assertTrue(IdentificationData.BRTA_CIRCLE_CODES.contains(prefix),
                    "Circle prefix should be in supported list: " + prefix);
        }
    }

    @Test(description = "Driving licence number supports specific circle code")
    public void testDrivingLicenseNumberWithCircleCode() {
        String dhakaDl = generator.getDrivingLicenseNumber("DK");
        assertTrue(dhakaDl.startsWith("DK"));
        assertEquals(dhakaDl.length(), 9);

        String ctgDl = generator.getDrivingLicenseNumber("ct"); // lower case converts to upper
        assertTrue(ctgDl.startsWith("CT"));
        assertEquals(ctgDl.length(), 9);
    }

    @DataProvider(name = "invalidCircleCodes")
    public Object[][] invalidCircleCodes() {
        return new Object[][]{
                {null},
                {""},
                {"   "},
                {"D"},
                {"DHA"},
                {"12"},
                {"D1"},
                {"@#"}
        };
    }

    @Test(dataProvider = "invalidCircleCodes", expectedExceptions = IllegalArgumentException.class,
            description = "Invalid BRTA circle codes throw IllegalArgumentException")
    public void testDrivingLicenseNumberInvalidCircleCode(String circleCode) {
        generator.getDrivingLicenseNumber(circleCode);
    }

    @Test(description = "Legacy driving lincenceId alias works identically to getDrivingLicenseNumber")
    public void testDrivingLicenseAliases() {
        IdentificationGenerator genA = new IdentificationGenerator(new Random(500L));
        IdentificationGenerator genB = new IdentificationGenerator(new Random(500L));

        assertEquals(genA.getDrivingLincenceId(), genB.getDrivingLicenseNumber());
        assertEquals(genA.getDrivingLicenseId(), genB.getDrivingLicenseNumber());
    }

    // =========================================================================
    // 6. CitizenIdentification Model POJO
    // =========================================================================

    @Test(description = "CitizenIdentification record contains all standardized fields and aliases")
    public void testCitizenIdentificationRecord() {
        CitizenIdentification id = generator.getCitizenIdentification();
        assertNotNull(id);

        // Standardized getters
        assertNotNull(id.getNationalIdNumber());
        assertEquals(id.getNationalIdNumber().length(), 17);

        assertNotNull(id.getBirthRegistrationNumber());
        assertEquals(id.getBirthRegistrationNumber().length(), 17);

        assertNotNull(id.getSmartCardNumber());
        assertEquals(id.getSmartCardNumber().length(), 10);

        assertNotNull(id.getPassportNumber());
        assertEquals(id.getPassportNumber().length(), 9);

        assertNotNull(id.getDrivingLicenseNumber());
        assertEquals(id.getDrivingLicenseNumber().length(), 9);

        // Aliases
        assertEquals(id.getNationalVoterNo(), id.getNationalIdNumber());
        assertEquals(id.getJonmnibondonNo(), id.getBirthRegistrationNumber());
        assertEquals(id.getSmartCardNo(), id.getSmartCardNumber());
        assertEquals(id.getPassordNo(), id.getPassportNumber());
        assertEquals(id.getDrivingLincenceId(), id.getDrivingLicenseNumber());

        // toString & equals/hashCode
        String str = id.toString();
        assertTrue(str.contains("nationalIdNumber"));
        assertTrue(str.contains("birthRegistrationNumber"));
        assertTrue(str.contains("smartCardNumber"));
        assertTrue(str.contains("passportNumber"));
        assertTrue(str.contains("drivingLicenseNumber"));

        CitizenIdentification identical = new CitizenIdentification(
                id.getNationalIdNumber(),
                id.getBirthRegistrationNumber(),
                id.getSmartCardNumber(),
                id.getPassportNumber(),
                id.getDrivingLicenseNumber()
        );
        assertEquals(id, identical);
        assertEquals(id.hashCode(), identical.hashCode());
    }

    @Test(expectedExceptions = NullPointerException.class,
            description = "Null nationalIdNumber throws NullPointerException")
    public void testCitizenIdentificationNullValidation() {
        new CitizenIdentification(null, "1", "2", "3", "4");
    }
}
