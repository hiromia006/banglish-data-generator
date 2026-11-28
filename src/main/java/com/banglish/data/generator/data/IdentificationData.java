package com.banglish.data.generator.data;

import java.util.List;

/**
 * Curated reference constants for Bangladesh citizen identification and official documents.
 */
public final class IdentificationData {

    private IdentificationData() {
        // Utility constant class
    }

    /**
     * Prominent Bangladesh Road Transport Authority (BRTA) circle / regional office codes.
     * <p>
     * Codes correspond to major administrative zones:
     * <ul>
     *   <li>DK — Dhaka Metro / Dhaka District</li>
     *   <li>CT — Chattogram Metro / Chattogram District</li>
     *   <li>SY — Sylhet Metro / Sylhet District</li>
     *   <li>KH — Khulna Metro / Khulna District</li>
     *   <li>RJ — Rajshahi Metro / Rajshahi District</li>
     *   <li>BR — Barishal District</li>
     *   <li>RP — Rangpur District</li>
     *   <li>MY — Mymensingh District</li>
     *   <li>JS — Jashore District</li>
     *   <li>CM — Cumilla District</li>
     * </ul>
     */
    public static final List<String> BRTA_CIRCLE_CODES = List.of(
            "DK", "CT", "SY", "KH", "RJ", "BR", "RP", "MY", "JS", "CM"
    );

    /**
     * Common passport series prefixes in Bangladesh (Machine Readable Passport and e-Passport).
     * <ul>
     *   <li>A — Standard ordinary e-Passport / MRP</li>
     *   <li>B — Ordinary MRP</li>
     *   <li>E — Official government passport</li>
     *   <li>P — Ordinary passport series</li>
     *   <li>D — Diplomatic passport</li>
     * </ul>
     */
    public static final List<Character> PASSPORT_SERIES = List.of(
            'A', 'B', 'E', 'P', 'D'
    );
}
