package com.banglish.data.generator;

import com.banglish.data.generator.data.PhonePrefixes;

import java.util.Objects;
import java.util.Random;

/**
 * Generator for synthetic Bangladesh mobile phone numbers.
 * <p>
 * Supported formats:
 * <ul>
 *   <li>Local 11-digit format: {@code 01XXXXXXXXX}</li>
 *   <li>International format: {@code +8801XXXXXXXXX} (14 characters total)</li>
 * </ul>
 * <p>
 * Supported operator prefixes: 013, 014, 015, 016, 017, 018, 019.
 *
 * <p><strong>Safety Warning:</strong> Generated mobile numbers are strictly synthetic test data.
 * Due to the nature of number assignments, generated numbers may correspond to active lines
 * of real subscribers. Under no circumstances should generated numbers be used to place calls
 * or transmit messages to real persons.</p>
 */
public class PhoneGenerator {

    private final Random random;

    /**
     * Constructs a PhoneGenerator with a specific random number generator.
     *
     * @param random random source
     */
    public PhoneGenerator(Random random) {
        this.random = Objects.requireNonNull(random, "Random generator must not be null");
    }

    /**
     * Generates an 11-digit Bangladeshi mobile number in standard local format (e.g., {@code 01712345678}).
     *
     * @return an 11-digit local phone number string
     */
    public String getMobileNumber() {
        int prefixIdx;
        synchronized (random) {
            prefixIdx = random.nextInt(PhonePrefixes.OPERATOR_PREFIXES.size());
        }
        String prefix = PhonePrefixes.OPERATOR_PREFIXES.get(prefixIdx);
        return getMobileNumber(prefix);
    }

    /**
     * Alias for {@link #getMobileNumber()}.
     *
     * @return an 11-digit local phone number string
     */
    public String getPhone() {
        return getMobileNumber();
    }

    /**
     * Generates an international formatted Bangladeshi mobile number (e.g. {@code +8801712345678}).
     * Starts with {@code +880} followed by the 10 digits starting with {@code 1}. Total 14 characters.
     *
     * @return an international phone number string
     */
    public String getPhoneWithCountryCode() {
        String localNumber = getMobileNumber();
        // Drop the leading '0' of 01XXXXXXXXX and prepend +880
        return PhonePrefixes.COUNTRY_CODE + localNumber.substring(1);
    }

    /**
     * Generates an 11-digit Bangladeshi mobile number starting with the specified 3-digit operator prefix.
     *
     * @param operatorPrefix a recognized 3-digit operator prefix (e.g., "017", "018", "019", "013", "014", "015", "016")
     * @return an 11-digit mobile number
     * @throws IllegalArgumentException if the prefix is null, blank, or not in the supported operator list
     */
    public String getMobileNumber(String operatorPrefix) {
        if (!PhonePrefixes.isValidPrefix(operatorPrefix)) {
            throw new IllegalArgumentException("Invalid operator prefix: '" + operatorPrefix +
                    "'. Must be one of: " + PhonePrefixes.OPERATOR_PREFIXES);
        }
        int remainingDigits;
        synchronized (random) {
            remainingDigits = random.nextInt(100_000_000);
        }
        return String.format("%s%08d", operatorPrefix.trim(), remainingDigits);
    }
}
