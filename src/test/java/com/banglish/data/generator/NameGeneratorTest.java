package com.banglish.data.generator;

import com.banglish.data.generator.data.BangladeshiNames;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

public class NameGeneratorTest {

    private NameGenerator generator;

    @BeforeMethod
    public void setUp() {
        generator = new NameGenerator(new Random(99));
    }

    @Test(description = "getFirstName returns known first name")
    public void testGetFirstName() {
        for (int i = 0; i < 50; i++) {
            String firstName = generator.getFirstName();
            assertNotNull(firstName);
            assertFalse(firstName.isBlank());
            assertTrue(BangladeshiNames.MALE_FIRST_NAMES.contains(firstName)
                    || BangladeshiNames.FEMALE_FIRST_NAMES.contains(firstName));
        }
    }

    @Test(description = "getLastName returns known family name")
    public void testGetLastName() {
        for (int i = 0; i < 50; i++) {
            String lastName = generator.getLastName();
            assertNotNull(lastName);
            assertTrue(BangladeshiNames.LAST_NAMES.contains(lastName));
        }
    }

    @Test(description = "getMaleName produces valid full male name")
    public void testGetMaleName() {
        for (int i = 0; i < 50; i++) {
            String maleName = generator.getMaleName();
            assertNotNull(maleName);
            String[] parts = maleName.split(" ");
            assertEquals(parts.length, 2);
            assertTrue(BangladeshiNames.MALE_FIRST_NAMES.contains(parts[0]));
            assertTrue(BangladeshiNames.LAST_NAMES.contains(parts[1]));
        }
    }

    @Test(description = "getFemaleName produces valid full female name")
    public void testGetFemaleName() {
        for (int i = 0; i < 50; i++) {
            String femaleName = generator.getFemaleName();
            assertNotNull(femaleName);
            String[] parts = femaleName.split(" ");
            assertEquals(parts.length, 2);
            assertTrue(BangladeshiNames.FEMALE_FIRST_NAMES.contains(parts[0]));
            assertTrue(BangladeshiNames.LAST_NAMES.contains(parts[1]));
        }
    }

    @Test(description = "getName generates valid full name")
    public void testGetName() {
        for (int i = 0; i < 50; i++) {
            String name = generator.getName();
            assertNotNull(name);
            String[] parts = name.split(" ");
            assertEquals(parts.length, 2);
            assertTrue(BangladeshiNames.LAST_NAMES.contains(parts[1]));
        }
    }

    @Test(description = "Verify dataset sizes, uniqueness, and format")
    public void testDatasetSizesAndUniqueness() {
        assertEquals(BangladeshiNames.MALE_FIRST_NAMES.size(), 500);
        assertEquals(BangladeshiNames.FEMALE_FIRST_NAMES.size(), 500);
        assertEquals(BangladeshiNames.LAST_NAMES.size(), 500);

        assertEquals(new java.util.HashSet<>(BangladeshiNames.MALE_FIRST_NAMES).size(), 500);
        assertEquals(new java.util.HashSet<>(BangladeshiNames.FEMALE_FIRST_NAMES).size(), 500);
        assertEquals(new java.util.HashSet<>(BangladeshiNames.LAST_NAMES).size(), 500);

        for (String name : BangladeshiNames.MALE_FIRST_NAMES) {
            assertNotNull(name);
            assertFalse(name.isBlank());
            assertTrue(Character.isUpperCase(name.charAt(0)));
        }
        for (String name : BangladeshiNames.FEMALE_FIRST_NAMES) {
            assertNotNull(name);
            assertFalse(name.isBlank());
            assertTrue(Character.isUpperCase(name.charAt(0)));
        }
        for (String name : BangladeshiNames.LAST_NAMES) {
            assertNotNull(name);
            assertFalse(name.isBlank());
            assertTrue(Character.isUpperCase(name.charAt(0)));
        }
    }
}
