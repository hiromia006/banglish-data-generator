package com.hiromia.banglish;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BanglishGeneratorTest {

    @Test(description = "getInstance returns non-null default singleton")
    public void testGetInstance() {
        Banglish banglish1 = BanglishGenerator.getInstance();
        Banglish banglish2 = BanglishGenerator.getInstance();
        assertNotNull(banglish1);
        assertSame(banglish1, banglish2, "getInstance() should return the singleton instance");
    }

    @Test(description = "getInstance(seed) provides deterministic output")
    public void testDeterministicSeededInstance() {
        long seed = 123456789L;
        Banglish gen1 = BanglishGenerator.getInstance(seed);
        Banglish gen2 = BanglishGenerator.getInstance(seed);

        assertEquals(gen1.getWord(), gen2.getWord());
        assertEquals(gen1.getWords(5, 5), gen2.getWords(5, 5));
        assertEquals(gen1.getSentence(), gen2.getSentence());
        assertEquals(gen1.getName(), gen2.getName());
        assertEquals(gen1.getMobileNumber(), gen2.getMobileNumber());
        assertEquals(gen1.getZipCode(), gen2.getZipCode());
        assertEquals(gen1.getCity(), gen2.getCity());
        assertEquals(gen1.getDistrict(), gen2.getDistrict());
        assertEquals(gen1.getDivision(), gen2.getDivision());
    }

    @Test(description = "Facade methods produce expected non-null and formatted data")
    public void testFacadeMethods() {
        Banglish banglish = BanglishGenerator.getInstance();

        assertNotNull(banglish.getWord());
        assertNotNull(banglish.getWords(3, 6));
        assertNotNull(banglish.getSentence());
        assertNotNull(banglish.getSentences(2, 4));
        assertNotNull(banglish.getParagraph());
        assertNotNull(banglish.getParagraphs(1, 2));
        assertNotNull(banglish.getTitle(2, 5));

        assertNotNull(banglish.getName());
        assertNotNull(banglish.getFirstName());
        assertNotNull(banglish.getLastName());
        assertNotNull(banglish.getMaleName());
        assertNotNull(banglish.getFemaleName());

        assertNotNull(banglish.getMobileNumber());
        assertEquals(banglish.getMobileNumber().length(), 11);
        assertNotNull(banglish.getPhone());
        assertNotNull(banglish.getPhoneWithCountryCode());
        assertEquals(banglish.getPhoneWithCountryCode().length(), 14);
        assertEquals(banglish.getMobileNumber("017").length(), 11);

        assertNotNull(banglish.getZipCode());
        assertEquals(banglish.getZipCode().length(), 4);
        assertNotNull(banglish.getPostalCode());
        assertEquals(banglish.getZipCode("Dhaka").length(), 4);

        assertNotNull(banglish.getCity());
        assertNotNull(banglish.getDistrict());
        assertNotNull(banglish.getDivision());
        assertEquals(banglish.getCountry(), "Bangladesh");
        assertNotNull(banglish.getDistrict("Dhaka"));
        assertFalse(banglish.getDistrictsByDivision("Dhaka").isEmpty());
        assertNotNull(banglish.getCity("Dhaka"));
        assertFalse(banglish.getCitiesByDistrict("Dhaka").isEmpty());
    }
}
