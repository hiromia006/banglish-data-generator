package com.hiromia.banglish;

import com.hiromia.banglish.data.LocationData;
import com.hiromia.banglish.model.District;
import com.hiromia.banglish.model.Division;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Generator for Bangladesh administrative divisions, districts, and prominent cities.
 * <p>
 * Accurate to the 8 administrative divisions and 64 districts of Bangladesh.
 * Sourced from LGED and Bangladesh administrative survey data (verified 2026).
 */
public class LocationGenerator {

    private final Random random;

    /**
     * Constructs a LocationGenerator with a specific random number generator.
     *
     * @param random random source
     */
    public LocationGenerator(Random random) {
        this.random = Objects.requireNonNull(random, "Random generator must not be null");
    }

    /**
     * Generates a random prominent city or locality name in Bangladesh.
     *
     * @return a city or locality name
     */
    public String getCity() {
        List<String> cities = LocationData.getAllCities();
        int index;
        synchronized (random) {
            index = random.nextInt(cities.size());
        }
        return cities.get(index);
    }

    /**
     * Generates a random administrative district name among all 64 districts in Bangladesh.
     *
     * @return a district name
     */
    public String getDistrict() {
        List<District> districts = LocationData.getAllDistricts();
        int index;
        synchronized (random) {
            index = random.nextInt(districts.size());
        }
        return districts.get(index).getName();
    }

    /**
     * Generates a random administrative division name among all 8 divisions in Bangladesh.
     *
     * @return a division name
     */
    public String getDivision() {
        Division[] divisions = Division.values();
        int index;
        synchronized (random) {
            index = random.nextInt(divisions.length);
        }
        return divisions[index].getDisplayName();
    }

    /**
     * Returns the country name: "Bangladesh".
     *
     * @return "Bangladesh"
     */
    public String getCountry() {
        return LocationData.COUNTRY;
    }

    /**
     * Generates a random district belonging to the specified division.
     *
     * @param division the division name (e.g. "Dhaka", "Chattogram")
     * @return a district name in the division
     * @throws IllegalArgumentException if the division is unrecognized
     */
    public String getDistrict(String division) {
        List<String> districts = getDistrictsByDivision(division);
        int index;
        synchronized (random) {
            index = random.nextInt(districts.size());
        }
        return districts.get(index);
    }

    /**
     * Returns an unmodifiable list of all districts belonging to the specified division.
     *
     * @param division the division name (e.g. "Dhaka", "Sylhet")
     * @return list of district names
     * @throws IllegalArgumentException if the division is unrecognized
     */
    public List<String> getDistrictsByDivision(String division) {
        Division div = Division.fromString(division);
        List<District> list = LocationData.getDistrictsByDivision(div);
        return list.stream().map(District::getName).toList();
    }

    /**
     * Generates a random prominent city or locality in the specified district.
     *
     * @param district the district name
     * @return a city or locality name
     * @throws IllegalArgumentException if the district is unrecognized
     */
    public String getCity(String district) {
        List<String> cities = getCitiesByDistrict(district);
        int index;
        synchronized (random) {
            index = random.nextInt(cities.size());
        }
        return cities.get(index);
    }

    /**
     * Returns an unmodifiable list of prominent cities or localities in the specified district.
     *
     * @param district the district name
     * @return list of city names
     * @throws IllegalArgumentException if the district is unrecognized
     */
    public List<String> getCitiesByDistrict(String district) {
        District found = LocationData.findDistrict(district);
        if (found == null) {
            throw new IllegalArgumentException("Unknown or unsupported Bangladesh district: '" + district + "'");
        }
        return found.getCities();
    }
}
