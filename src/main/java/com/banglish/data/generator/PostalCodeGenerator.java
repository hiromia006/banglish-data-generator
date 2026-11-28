package com.banglish.data.generator;

import com.banglish.data.generator.data.LocationData;
import com.banglish.data.generator.model.District;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Generator for authentic 4-digit Bangladesh postal codes.
 * <p>
 * Postal codes are strictly sourced from verified Bangladesh Post Office (BPO)
 * records covering all 64 districts (verified 2026).
 * This generator never fabricates arbitrary postal codes.
 */
public class PostalCodeGenerator {

    private final Random random;

    /**
     * Constructs a PostalCodeGenerator with a specific random number generator.
     *
     * @param random random source
     */
    public PostalCodeGenerator(Random random) {
        this.random = Objects.requireNonNull(random, "Random generator must not be null");
    }

    /**
     * Generates a 4-digit postal code randomly selected from verified Bangladesh Post Office data.
     *
     * @return a verified 4-digit postal code string
     */
    public String getZipCode() {
        List<String> codes = LocationData.getAllPostalCodes();
        int index;
        synchronized (random) {
            index = random.nextInt(codes.size());
        }
        return codes.get(index);
    }

    /**
     * Alias for {@link #getZipCode()}.
     *
     * @return a verified 4-digit postal code string
     */
    public String getPostalCode() {
        return getZipCode();
    }

    /**
     * Generates a verified 4-digit postal code for the specified Bangladesh district.
     *
     * @param district the name of the district (case-insensitive, e.g. "Dhaka", "Sylhet")
     * @return a 4-digit postal code for that district
     * @throws IllegalArgumentException if the district is null, blank, or not recognized
     */
    public String getZipCode(String district) {
        District found = LocationData.findDistrict(district);
        if (found == null) {
            throw new IllegalArgumentException("Unknown or unsupported Bangladesh district: '" + district + "'");
        }
        List<String> districtCodes = found.getPostalCodes();
        int index;
        synchronized (random) {
            index = random.nextInt(districtCodes.size());
        }
        return districtCodes.get(index);
    }
}
