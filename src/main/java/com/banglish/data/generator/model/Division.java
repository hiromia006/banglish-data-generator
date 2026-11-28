package com.banglish.data.generator.model;

/**
 * Represents the eight administrative divisions of Bangladesh.
 */
public enum Division {
    DHAKA("Dhaka"),
    CHATTOGRAM("Chattogram"),
    RAJSHAHI("Rajshahi"),
    KHULNA("Khulna"),
    BARISHAL("Barishal"),
    SYLHET("Sylhet"),
    RANGPUR("Rangpur"),
    MYMENSINGH("Mymensingh");

    private final String displayName;

    Division(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the official English display name of the division.
     *
     * @return the division name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Finds a Division enum from a string name (case-insensitive).
     *
     * @param name the division name to find
     * @return the matching Division
     * @throws IllegalArgumentException if no division matches the input
     */
    public static Division fromString(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Division name must not be null or blank");
        }
        String cleaned = name.trim();
        for (Division div : values()) {
            if (div.displayName.equalsIgnoreCase(cleaned) || div.name().equalsIgnoreCase(cleaned)) {
                return div;
            }
        }
        // Also handle legacy/alternative spellings (e.g. Chittagong for Chattogram)
        if (cleaned.equalsIgnoreCase("Chittagong")) {
            return CHATTOGRAM;
        }
        throw new IllegalArgumentException("Unknown Bangladesh division: " + name);
    }
}
