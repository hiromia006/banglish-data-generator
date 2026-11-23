package com.hiromia.banglish;

import java.util.List;

/**
 * Public contract for the Banglish Data Generator library.
 * <p>
 * Provides synthetic Banglish text (Roman characters phonetically representing spoken Bengali)
 * and verified Bangladesh-specific test data (names, mobile phone numbers, postal codes, and locations)
 * for software quality assurance, database seeding, automated testing, and development.
 *
 * <p><strong>Disclaimer:</strong> All generated text, names, and contact details are purely synthetic
 * mock data intended exclusively for testing and simulation. Generated phone numbers may coincide with
 * actual subscriber numbers in real life; never use generated numbers to call or send messages to real persons.</p>
 */
public interface Banglish {

    // =========================================================================
    // 1. Banglish Text Generation
    // =========================================================================

    /**
     * Generates a single random Banglish word.
     *
     * @return a Banglish word in lower case
     */
    String getWord();

    /**
     * Generates a string containing between {@code min} and {@code max} Banglish words,
     * separated by a single space.
     *
     * @param min minimum number of words (must be &ge; 0)
     * @param max maximum number of words (must be &ge; min)
     * @return a space-separated string of Banglish words
     * @throws IllegalArgumentException if min &lt; 0 or max &lt; min
     */
    String getWords(int min, int max);

    /**
     * Generates a single readable Banglish sentence starting with a capital letter
     * and ending with proper punctuation (period or question mark).
     *
     * @return a single Banglish sentence
     */
    String getSentence();

    /**
     * Generates between {@code min} and {@code max} Banglish sentences, separated by a single space.
     *
     * @param min minimum number of sentences (must be &ge; 0)
     * @param max maximum number of sentences (must be &ge; min)
     * @return a space-separated string of Banglish sentences
     * @throws IllegalArgumentException if min &lt; 0 or max &lt; min
     */
    String getSentences(int min, int max);

    /**
     * Generates a single paragraph consisting of multiple Banglish sentences.
     *
     * @return a Banglish paragraph
     */
    String getParagraph();

    /**
     * Generates between {@code min} and {@code max} Banglish paragraphs, separated by double newlines.
     *
     * @param min minimum number of paragraphs (must be &ge; 0)
     * @param max maximum number of paragraphs (must be &ge; min)
     * @return paragraphs separated by double newlines
     * @throws IllegalArgumentException if min &lt; 0 or max &lt; min
     */
    String getParagraphs(int min, int max);

    /**
     * Generates a capitalized title consisting of between {@code min} and {@code max} words.
     *
     * @param min minimum number of title words (must be &ge; 0)
     * @param max maximum number of title words (must be &ge; min)
     * @return title-cased string of Banglish words
     * @throws IllegalArgumentException if min &lt; 0 or max &lt; min
     */
    String getTitle(int min, int max);

    // =========================================================================
    // 2. Bangladeshi Name Generation
    // =========================================================================

    /**
     * Generates a full Bangladeshi name (first name and last name, either male or female).
     *
     * @return a full Bangladeshi name
     */
    String getName();

    /**
     * Generates a random Bangladeshi first name (either male or female).
     *
     * @return a first name
     */
    String getFirstName();

    /**
     * Generates a random common Bangladeshi family surname / last name.
     *
     * @return a last name
     */
    String getLastName();

    /**
     * Generates a full male Bangladeshi name (first name and last name).
     *
     * @return a male full name
     */
    String getMaleName();

    /**
     * Generates a full female Bangladeshi name (first name and last name).
     *
     * @return a female full name
     */
    String getFemaleName();

    // =========================================================================
    // 3. Bangladesh Mobile and Phone Generation
    // =========================================================================

    /**
     * Generates an 11-digit Bangladeshi mobile number in standard local format (e.g. {@code 01712345678}).
     * Uses one of the valid operator prefixes (013, 014, 015, 016, 017, 018, 019).
     *
     * @return 11-digit mobile number string
     */
    String getMobileNumber();

    /**
     * Alias for {@link #getMobileNumber()}.
     *
     * @return 11-digit mobile number string
     */
    String getPhone();

    /**
     * Generates an international formatted Bangladeshi mobile number (e.g. {@code +8801712345678}).
     * Format: country code {@code +880} followed by 10 digits starting with {@code 1}. Total 14 characters.
     *
     * @return international formatted phone number
     */
    String getPhoneWithCountryCode();

    /**
     * Generates an 11-digit Bangladeshi mobile number using the specified 3-digit operator prefix.
     *
     * @param operatorPrefix 3-digit prefix such as "017", "018", "019", "013", "014", "015", "016"
     * @return 11-digit mobile number starting with the specified prefix
     * @throws IllegalArgumentException if the prefix is null or not a recognized operator prefix
     */
    String getMobileNumber(String operatorPrefix);

    // =========================================================================
    // 4. Bangladesh Postal-Code Generation
    // =========================================================================

    /**
     * Generates a valid 4-digit postal code randomly selected from verified Bangladesh Post Office data.
     *
     * @return a 4-digit postal code string
     */
    String getZipCode();

    /**
     * Alias for {@link #getZipCode()}.
     *
     * @return a 4-digit postal code string
     */
    String getPostalCode();

    /**
     * Generates a valid 4-digit postal code for the specified Bangladesh district.
     *
     * @param district the name of the district (case-insensitive, e.g., "Dhaka", "Chattogram", "Sylhet")
     * @return a 4-digit postal code associated with the specified district
     * @throws IllegalArgumentException if the district is null, blank, or unrecognized
     */
    String getZipCode(String district);

    // =========================================================================
    // 5. Bangladesh Location Generation
    // =========================================================================

    /**
     * Generates a random prominent city, thana, or locality name in Bangladesh.
     *
     * @return a city or locality name
     */
    String getCity();

    /**
     * Generates a random administrative district name among all 64 districts in Bangladesh.
     *
     * @return a district name (e.g., "Dhaka", "Bogura", "Khulna")
     */
    String getDistrict();

    /**
     * Generates a random administrative division name among all 8 divisions in Bangladesh.
     *
     * @return a division name (e.g., "Dhaka", "Chattogram", "Rajshahi", "Khulna", "Barishal", "Sylhet", "Rangpur", "Mymensingh")
     */
    String getDivision();

    /**
     * Returns the country name: "Bangladesh".
     *
     * @return "Bangladesh"
     */
    String getCountry();

    /**
     * Generates a random district belonging to the specified division.
     *
     * @param division the name of the administrative division
     * @return a district in the specified division
     * @throws IllegalArgumentException if the division is unrecognized
     */
    String getDistrict(String division);

    /**
     * Returns an unmodifiable list of all districts belonging to the specified division.
     *
     * @param division the division name (e.g., "Dhaka", "Sylhet")
     * @return list of district names
     * @throws IllegalArgumentException if the division is unrecognized
     */
    List<String> getDistrictsByDivision(String division);

    /**
     * Generates a random prominent city or locality belonging to the specified district.
     *
     * @param district the district name
     * @return a city or locality in that district
     * @throws IllegalArgumentException if the district is unrecognized
     */
    String getCity(String district);

    /**
     * Returns an unmodifiable list of prominent cities or localities belonging to the specified district.
     *
     * @param district the district name
     * @return list of city names
     * @throws IllegalArgumentException if the district is unrecognized
     */
    List<String> getCitiesByDistrict(String district);
}
