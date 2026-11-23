package com.hiromia.banglish.data;

import java.util.List;
import java.util.Set;

/**
 * Curated mobile operator prefixes for Bangladesh.
 * <p>
 * Supported operators:
 * <ul>
 *   <li>013 - Grameenphone</li>
 *   <li>014 - Banglalink</li>
 *   <li>015 - Teletalk</li>
 *   <li>016 - Airtel</li>
 *   <li>017 - Grameenphone</li>
 *   <li>018 - Robi</li>
 *   <li>019 - Banglalink</li>
 * </ul>
 */
public final class PhonePrefixes {

    private PhonePrefixes() {
        // static constants only
    }

    public static final String COUNTRY_CODE = "+880";

    public static final List<String> OPERATOR_PREFIXES = List.of(
            "013",
            "014",
            "015",
            "016",
            "017",
            "018",
            "019"
    );

    private static final Set<String> VALID_PREFIX_SET = Set.copyOf(OPERATOR_PREFIXES);

    /**
     * Checks if the given prefix is a valid Bangladeshi mobile operator prefix.
     *
     * @param prefix 3-digit prefix such as "017"
     * @return true if valid, false otherwise
     */
    public static boolean isValidPrefix(String prefix) {
        return prefix != null && VALID_PREFIX_SET.contains(prefix.trim());
    }
}
