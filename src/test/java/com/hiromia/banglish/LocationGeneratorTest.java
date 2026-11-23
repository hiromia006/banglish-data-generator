package com.hiromia.banglish;

import com.hiromia.banglish.data.LocationData;
import com.hiromia.banglish.model.District;
import com.hiromia.banglish.model.Division;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

public class LocationGeneratorTest {

    private LocationGenerator generator;

    @BeforeMethod
    public void setUp() {
        generator = new LocationGenerator(new Random(303));
    }

    @Test(description = "getCountry returns Bangladesh")
    public void testGetCountry() {
        assertEquals(generator.getCountry(), "Bangladesh");
    }

    @Test(description = "All 8 divisions are present in Division enum")
    public void testAllEightDivisionsPresent() {
        assertEquals(Division.values().length, 8);
        List<String> names = List.of("Dhaka", "Chattogram", "Rajshahi", "Khulna",
                "Barishal", "Sylhet", "Rangpur", "Mymensingh");
        for (String expected : names) {
            Division div = Division.fromString(expected);
            assertNotNull(div);
        }
    }

    @Test(description = "All 64 districts are present in dataset")
    public void testAll64DistrictsPresent() {
        assertEquals(LocationData.getAllDistricts().size(), 64,
                "Must contain exactly 64 Bangladesh districts");
    }

    @Test(description = "getDivision returns valid division name")
    public void testGetDivision() {
        for (int i = 0; i < 30; i++) {
            String div = generator.getDivision();
            assertNotNull(div);
            assertNotNull(Division.fromString(div));
        }
    }

    @Test(description = "getDistrict returns valid district name")
    public void testGetDistrict() {
        for (int i = 0; i < 50; i++) {
            String dist = generator.getDistrict();
            assertNotNull(dist);
            assertNotNull(LocationData.findDistrict(dist));
        }
    }

    @Test(description = "getCity returns valid city name")
    public void testGetCity() {
        for (int i = 0; i < 50; i++) {
            String city = generator.getCity();
            assertNotNull(city);
            assertTrue(LocationData.getAllCities().contains(city));
        }
    }

    @Test(description = "getDistrict(division) returns district belonging to that division")
    public void testGetDistrictByDivision() {
        for (Division div : Division.values()) {
            for (int i = 0; i < 10; i++) {
                String distName = generator.getDistrict(div.getDisplayName());
                District district = LocationData.findDistrict(distName);
                assertNotNull(district);
                assertEquals(district.getDivision(), div);
            }
        }
    }

    @Test(description = "getDistrictsByDivision returns correct list")
    public void testGetDistrictsByDivisionList() {
        List<String> dhakaDistricts = generator.getDistrictsByDivision("Dhaka");
        assertEquals(dhakaDistricts.size(), 13);
        assertTrue(dhakaDistricts.contains("Dhaka"));
        assertTrue(dhakaDistricts.contains("Gazipur"));
        assertTrue(dhakaDistricts.contains("Narayanganj"));
    }

    @Test(description = "getDistrictsByDivision throws on invalid division")
    public void testGetDistrictsByDivisionInvalid() {
        assertThrows(IllegalArgumentException.class, () -> generator.getDistrictsByDivision("InvalidDiv"));
        assertThrows(IllegalArgumentException.class, () -> generator.getDistrictsByDivision(null));
    }

    @Test(description = "getCity(district) returns valid city belonging to that district")
    public void testGetCityByDistrict() {
        for (District district : LocationData.getAllDistricts()) {
            String city = generator.getCity(district.getName());
            assertNotNull(city);
            assertTrue(district.getCities().contains(city));
        }
    }

    @Test(description = "getCitiesByDistrict returns all cities for district")
    public void testGetCitiesByDistrict() {
        List<String> dhakaCities = generator.getCitiesByDistrict("Dhaka");
        assertNotNull(dhakaCities);
        assertFalse(dhakaCities.isEmpty());
        assertTrue(dhakaCities.contains("Dhanmondi"));
        assertTrue(dhakaCities.contains("Gulshan"));
    }

    @DataProvider(name = "invalidDistricts")
    public Object[][] invalidDistricts() {
        return new Object[][] {
                {"InvalidDistrict"}, {"NewYork"}, {"123"}, {""}, {"   "}
        };
    }

    @Test(dataProvider = "invalidDistricts", description = "getCity(district) throws on invalid district")
    public void testGetCityByDistrictInvalid(String district) {
        assertThrows(IllegalArgumentException.class, () -> generator.getCity(district));
        assertThrows(IllegalArgumentException.class, () -> generator.getCitiesByDistrict(district));
    }
}
