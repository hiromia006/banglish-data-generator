package com.banglish.data.generator;

import com.banglish.data.generator.data.PhonePrefixes;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

public class PhoneGeneratorTest {

    private PhoneGenerator generator;

    @BeforeMethod
    public void setUp() {
        generator = new PhoneGenerator(new Random(101));
    }

    @Test(description = "getMobileNumber generates 11-digit number with valid operator prefix")
    public void testGetMobileNumber() {
        for (int i = 0; i < 100; i++) {
            String mobile = generator.getMobileNumber();
            assertNotNull(mobile);
            assertEquals(mobile.length(), 11, "Mobile number must be exactly 11 digits: " + mobile);
            assertTrue(mobile.matches("^01[3-9]\\d{8}$"),
                    "Number must match pattern 01[3-9]XXXXXXXX: " + mobile);

            String prefix = mobile.substring(0, 3);
            assertTrue(PhonePrefixes.isValidPrefix(prefix), "Prefix must be valid operator: " + prefix);
        }
    }

    @Test(description = "getPhone is alias of getMobileNumber")
    public void testGetPhone() {
        String phone = generator.getPhone();
        assertNotNull(phone);
        assertEquals(phone.length(), 11);
        assertTrue(phone.matches("^01[3-9]\\d{8}$"));
    }

    @Test(description = "getPhoneWithCountryCode formats as +8801XXXXXXXXX (14 characters)")
    public void testGetPhoneWithCountryCode() {
        for (int i = 0; i < 50; i++) {
            String phone = generator.getPhoneWithCountryCode();
            assertNotNull(phone);
            assertEquals(phone.length(), 14, "International format must be 14 chars: " + phone);
            assertTrue(phone.startsWith("+8801"), "Must start with +8801: " + phone);
            assertTrue(phone.matches("^\\+8801[3-9]\\d{8}$"),
                    "Must match international regex: " + phone);
        }
    }

    @DataProvider(name = "validPrefixes")
    public Object[][] validPrefixes() {
        return new Object[][] {
                {"013"}, {"014"}, {"015"}, {"016"}, {"017"}, {"018"}, {"019"}
        };
    }

    @Test(dataProvider = "validPrefixes", description = "getMobileNumber with valid operator prefix produces 11-digit number")
    public void testGetMobileNumberWithValidPrefix(String prefix) {
        for (int i = 0; i < 10; i++) {
            String mobile = generator.getMobileNumber(prefix);
            assertEquals(mobile.length(), 11);
            assertTrue(mobile.startsWith(prefix));
            assertTrue(mobile.matches("^" + prefix + "\\d{8}$"));
        }
    }

    @DataProvider(name = "invalidPrefixes")
    public Object[][] invalidPrefixes() {
        return new Object[][] {
                {"010"}, {"011"}, {"012"}, {"020"}, {"123"}, {""}, {"   "}, {"0177"}, {"01"}
        };
    }

    @Test(dataProvider = "invalidPrefixes", description = "getMobileNumber with invalid prefix throws IllegalArgumentException")
    public void testGetMobileNumberInvalidPrefix(String invalidPrefix) {
        assertThrows(IllegalArgumentException.class, () -> generator.getMobileNumber(invalidPrefix));
    }

    @Test(description = "getMobileNumber with null prefix throws IllegalArgumentException")
    public void testGetMobileNumberNullPrefix() {
        assertThrows(IllegalArgumentException.class, () -> generator.getMobileNumber(null));
    }
}
