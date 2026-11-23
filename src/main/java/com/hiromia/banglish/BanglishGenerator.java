package com.hiromia.banglish;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Main facade implementation of the {@link Banglish} generator.
 * <p>
 * Provides convenient singleton access, seed-based deterministic generation,
 * and thread-safe operations.
 *
 * <pre>{@code
 * Banglish banglish = BanglishGenerator.getInstance();
 * String sentence = banglish.getSentence();
 * String name = banglish.getName();
 * String mobile = banglish.getMobileNumber();
 * String zip = banglish.getZipCode();
 * String division = banglish.getDivision();
 * }</pre>
 */
public class BanglishGenerator implements Banglish {

    private static final Banglish DEFAULT_INSTANCE = new BanglishGenerator();

    private final TextGenerator textGenerator;
    private final NameGenerator nameGenerator;
    private final PhoneGenerator phoneGenerator;
    private final PostalCodeGenerator postalCodeGenerator;
    private final LocationGenerator locationGenerator;

    /**
     * Returns the shared, thread-safe default instance of {@link Banglish}.
     *
     * @return the default Banglish instance
     */
    public static Banglish getInstance() {
        return DEFAULT_INSTANCE;
    }

    /**
     * Creates a new deterministic {@link Banglish} instance seeded with the given value.
     *
     * @param seed initial seed for reproducibility
     * @return a new deterministic Banglish instance
     */
    public static Banglish getInstance(long seed) {
        return new BanglishGenerator(seed);
    }

    /**
     * Constructs a new BanglishGenerator using a default {@link Random}.
     */
    public BanglishGenerator() {
        this(new Random());
    }

    /**
     * Constructs a new BanglishGenerator seeded with the specified value.
     *
     * @param seed random seed
     */
    public BanglishGenerator(long seed) {
        this(new Random(seed));
    }

    /**
     * Constructs a new BanglishGenerator using the provided {@link Random} instance.
     *
     * @param random random source
     */
    public BanglishGenerator(Random random) {
        Objects.requireNonNull(random, "Random instance must not be null");
        this.textGenerator = new TextGenerator(random);
        this.nameGenerator = new NameGenerator(random);
        this.phoneGenerator = new PhoneGenerator(random);
        this.postalCodeGenerator = new PostalCodeGenerator(random);
        this.locationGenerator = new LocationGenerator(random);
    }

    // =========================================================================
    // Text Generation
    // =========================================================================

    @Override
    public String getWord() {
        return textGenerator.getWord();
    }

    @Override
    public String getWords(int min, int max) {
        return textGenerator.getWords(min, max);
    }

    @Override
    public String getSentence() {
        return textGenerator.getSentence();
    }

    @Override
    public String getSentences(int min, int max) {
        return textGenerator.getSentences(min, max);
    }

    @Override
    public String getParagraph() {
        return textGenerator.getParagraph();
    }

    @Override
    public String getParagraphs(int min, int max) {
        return textGenerator.getParagraphs(min, max);
    }

    @Override
    public String getTitle(int min, int max) {
        return textGenerator.getTitle(min, max);
    }

    // =========================================================================
    // Name Generation
    // =========================================================================

    @Override
    public String getName() {
        return nameGenerator.getName();
    }

    @Override
    public String getFirstName() {
        return nameGenerator.getFirstName();
    }

    @Override
    public String getLastName() {
        return nameGenerator.getLastName();
    }

    @Override
    public String getMaleName() {
        return nameGenerator.getMaleName();
    }

    @Override
    public String getFemaleName() {
        return nameGenerator.getFemaleName();
    }

    // =========================================================================
    // Mobile / Phone Generation
    // =========================================================================

    @Override
    public String getMobileNumber() {
        return phoneGenerator.getMobileNumber();
    }

    @Override
    public String getPhone() {
        return phoneGenerator.getPhone();
    }

    @Override
    public String getPhoneWithCountryCode() {
        return phoneGenerator.getPhoneWithCountryCode();
    }

    @Override
    public String getMobileNumber(String operatorPrefix) {
        return phoneGenerator.getMobileNumber(operatorPrefix);
    }

    // =========================================================================
    // Postal Code Generation
    // =========================================================================

    @Override
    public String getZipCode() {
        return postalCodeGenerator.getZipCode();
    }

    @Override
    public String getPostalCode() {
        return postalCodeGenerator.getPostalCode();
    }

    @Override
    public String getZipCode(String district) {
        return postalCodeGenerator.getZipCode(district);
    }

    // =========================================================================
    // Location Generation
    // =========================================================================

    @Override
    public String getCity() {
        return locationGenerator.getCity();
    }

    @Override
    public String getDistrict() {
        return locationGenerator.getDistrict();
    }

    @Override
    public String getDivision() {
        return locationGenerator.getDivision();
    }

    @Override
    public String getCountry() {
        return locationGenerator.getCountry();
    }

    @Override
    public String getDistrict(String division) {
        return locationGenerator.getDistrict(division);
    }

    @Override
    public List<String> getDistrictsByDivision(String division) {
        return locationGenerator.getDistrictsByDivision(division);
    }

    @Override
    public String getCity(String district) {
        return locationGenerator.getCity(district);
    }

    @Override
    public List<String> getCitiesByDistrict(String district) {
        return locationGenerator.getCitiesByDistrict(district);
    }
}
