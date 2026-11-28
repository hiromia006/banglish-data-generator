package com.banglish.data.generator;

import com.banglish.data.generator.data.LocationData;
import com.banglish.data.generator.model.District;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

public class PostalCodeGeneratorTest {

    private PostalCodeGenerator generator;

    @BeforeMethod
    public void setUp() {
        generator = new PostalCodeGenerator(new Random(202));
    }

    @Test(description = "getZipCode returns valid 4-digit code from verified dataset")
    public void testGetZipCode() {
        for (int i = 0; i < 100; i++) {
            String code = generator.getZipCode();
            assertNotNull(code);
            assertEquals(code.length(), 4, "Postal code must be 4 digits: " + code);
            assertTrue(code.matches("^\\d{4}$"), "Postal code must be 4 numeric digits: " + code);
            assertTrue(LocationData.getAllPostalCodes().contains(code),
                    "Postal code must come from verified dataset: " + code);
        }
    }

    @Test(description = "getPostalCode is alias for getZipCode")
    public void testGetPostalCode() {
        String code = generator.getPostalCode();
        assertNotNull(code);
        assertEquals(code.length(), 4);
        assertTrue(code.matches("^\\d{4}$"));
    }

    @Test(description = "getZipCode with valid district returns correct code")
    public void testGetZipCodeForDistrict() {
        for (District district : LocationData.getAllDistricts()) {
            String code = generator.getZipCode(district.getName());
            assertNotNull(code);
            assertEquals(code.length(), 4);
            assertTrue(district.getPostalCodes().contains(code),
                    "Code " + code + " must belong to district " + district.getName());
        }
    }

    @Test(description = "getZipCode handles case-insensitivity and whitespace")
    public void testGetZipCodeCaseInsensitive() {
        String code1 = generator.getZipCode("dhaka");
        String code2 = generator.getZipCode("DHAKA");
        String code3 = generator.getZipCode("  Dhaka  ");
        assertNotNull(code1);
        assertNotNull(code2);
        assertNotNull(code3);
        assertEquals(code1.length(), 4);
    }

    @DataProvider(name = "unknownDistricts")
    public Object[][] unknownDistricts() {
        return new Object[][] {
                {"Atlantis"}, {"London"}, {"Narnia"}, {"Mars"}, {"123"}, {""}, {"   "}
        };
    }

    @Test(dataProvider = "unknownDistricts", description = "getZipCode throws IllegalArgumentException for unknown district")
    public void testGetZipCodeUnknownDistrict(String district) {
        assertThrows(IllegalArgumentException.class, () -> generator.getZipCode(district));
    }

    @Test(description = "getZipCode throws IllegalArgumentException for null district")
    public void testGetZipCodeNullDistrict() {
        assertThrows(IllegalArgumentException.class, () -> generator.getZipCode(null));
    }
}
