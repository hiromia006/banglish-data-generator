package com.banglish.data.generator.model;

import java.util.List;
import java.util.Objects;

/**
 * Represents an administrative district in Bangladesh along with its division,
 * postal codes, and prominent cities or localities.
 */
public class District {

    private final String name;
    private final Division division;
    private final List<String> postalCodes;
    private final List<String> cities;

    /**
     * Constructs a District record.
     *
     * @param name        the district name (e.g., "Dhaka")
     * @param division    the parent administrative division
     * @param postalCodes list of verified postal codes in this district
     * @param cities      list of notable cities or localities in this district
     */
    public District(String name, Division division, List<String> postalCodes, List<String> cities) {
        this.name = Objects.requireNonNull(name, "District name must not be null");
        this.division = Objects.requireNonNull(division, "Division must not be null");
        this.postalCodes = List.copyOf(Objects.requireNonNull(postalCodes, "Postal codes must not be null"));
        this.cities = List.copyOf(Objects.requireNonNull(cities, "Cities must not be null"));
    }

    public String getName() {
        return name;
    }

    public Division getDivision() {
        return division;
    }

    public List<String> getPostalCodes() {
        return postalCodes;
    }

    public List<String> getCities() {
        return cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof District other)) return false;
        return name.equalsIgnoreCase(other.name) && division == other.division;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), division);
    }

    @Override
    public String toString() {
        return "District{" +
                "name='" + name + '\'' +
                ", division=" + division.getDisplayName() +
                ", citiesCount=" + cities.size() +
                '}';
    }
}
