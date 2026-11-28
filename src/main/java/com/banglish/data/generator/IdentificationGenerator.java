package com.banglish.data.generator;

import com.banglish.data.generator.data.IdentificationData;
import com.banglish.data.generator.model.CitizenIdentification;

import java.util.Objects;
import java.util.Random;

/**
 * Generator for synthetic Bangladesh citizen identification and official document numbers.
 * <p>
 * Supports generation for:
 * <ul>
 *   <li><b>National ID (NID) Number</b> (standardized: {@code nationalIdNumber}, legacy: {@code nationalVoterNo}) — 17, 13, or 10 digits</li>
 *   <li><b>Birth Registration Number</b> (standardized: {@code birthRegistrationNumber}, legacy: {@code JonmnibondonNo}) — 17 digits</li>
 *   <li><b>Smart NID Card Number</b> (standardized: {@code smartCardNumber}, legacy: {@code smartCardNo}) — 10 digits</li>
 *   <li><b>Bangladesh Passport Number</b> (standardized: {@code passportNumber}, legacy: {@code passordNo}) — 9 characters (1 letter + 8 digits)</li>
 *   <li><b>BRTA Driving Licence Number</b> (standardized: {@code drivingLicenseNumber}, legacy: {@code driving lincenceId}) — 9 characters (2-letter circle code + 7 digits)</li>
 * </ul>
 *
 * <p><strong>Safety Disclaimer:</strong> All generated identification numbers are purely synthetic mock data
 * intended for quality assurance, test automation, and database seeding. They must never be used to impersonate
 * or represent real individuals or authentic government records.</p>
 */
public class IdentificationGenerator {

    private final Random random;

    /**
     * Constructs an IdentificationGenerator with the specified random number generator.
     *
     * @param random random source
     */
    public IdentificationGenerator(Random random) {
        this.random = Objects.requireNonNull(random, "Random generator must not be null");
    }

    // =========================================================================
    // 1. National ID (NID) Number / nationalVoterNo
    // =========================================================================

    /**
     * Generates a standard 17-digit Bangladesh National ID (NID) number.
     * <p>
     * Formatted with a 4-digit birth year (1950–2006 for adult voter age 18+)
     * followed by a 13-digit sequential/area code.
     *
     * @return 17-digit National ID number string
     */
    public String getNationalIdNumber() {
        return getNationalIdNumber(17);
    }

    /**
     * Generates a Bangladesh National ID number with the specified digit length.
     * Supported lengths are 10 (smart format), 13 (legacy pre-computerized), or 17 (standard full computerized).
     *
     * @param length desired digit count (10, 13, or 17)
     * @return National ID number string of the specified length
     * @throws IllegalArgumentException if length is not 10, 13, or 17
     */
    public String getNationalIdNumber(int length) {
        if (length == 17) {
            int birthYear;
            synchronized (random) {
                birthYear = 1950 + random.nextInt(57); // 1950 to 2006 (inclusive)
            }
            return birthYear + generateDigits(13);
        } else if (length == 10) {
            int firstDigit;
            synchronized (random) {
                firstDigit = 1 + random.nextInt(9);
            }
            return firstDigit + generateDigits(9);
        } else if (length == 13) {
            int firstDigit;
            synchronized (random) {
                firstDigit = 1 + random.nextInt(9);
            }
            return firstDigit + generateDigits(12);
        } else {
            throw new IllegalArgumentException("Unsupported National ID number length: " + length +
                    ". Supported lengths are 10, 13, or 17 digits.");
        }
    }

    /**
     * Legacy alias for {@link #getNationalIdNumber()} (field name: {@code nationalVoterNo}).
     *
     * @return 17-digit National ID number
     */
    public String getNationalVoterNo() {
        return getNationalIdNumber();
    }

    /**
     * Legacy alias for {@link #getNationalIdNumber(int)} (field name: {@code nationalVoterNo}).
     *
     * @param length digit length (10, 13, or 17)
     * @return National ID number string
     */
    public String getNationalVoterNo(int length) {
        return getNationalIdNumber(length);
    }

    // =========================================================================
    // 2. Birth Registration Number / JonmnibondonNo
    // =========================================================================

    /**
     * Generates a standard 17-digit Bangladesh Birth Registration Number (BRN / Jonmo Nibondhon No).
     * <p>
     * Conforms to the 17-digit BDRIS structure:
     * <ul>
     *   <li>Digits 1–4: Year of birth (1950–2025)</li>
     *   <li>Digits 5–6: District geo code (01–64)</li>
     *   <li>Digits 7–8: Upazila / Thana code (01–99)</li>
     *   <li>Digits 9–10: Union Parishad / Ward code (01–99)</li>
     *   <li>Digits 11–17: 7-digit unique sequence number</li>
     * </ul>
     *
     * @return 17-digit birth registration number string
     */
    public String getBirthRegistrationNumber() {
        int birthYear, districtCode, upazilaCode, unionCode;
        synchronized (random) {
            birthYear = 1950 + random.nextInt(76); // 1950 to 2025
            districtCode = 1 + random.nextInt(64); // 01 to 64
            upazilaCode = 1 + random.nextInt(99);  // 01 to 99
            unionCode = 1 + random.nextInt(99);    // 01 to 99
        }
        return String.format("%04d%02d%02d%02d%s", birthYear, districtCode, upazilaCode, unionCode, generateDigits(7));
    }

    /**
     * Legacy alias for {@link #getBirthRegistrationNumber()} (field name: {@code JonmnibondonNo}).
     *
     * @return 17-digit birth registration number
     */
    public String getJonmnibondonNo() {
        return getBirthRegistrationNumber();
    }

    // =========================================================================
    // 3. Smart NID Card Number / smartCardNo
    // =========================================================================

    /**
     * Generates a standard 10-digit Bangladesh Smart NID Card Number.
     * <p>
     * Issued for polymer Smart NID cards by the Bangladesh Election Commission.
     * Always exactly 10 digits starting with a non-zero digit (1–9).
     *
     * @return 10-digit Smart NID card number string
     */
    public String getSmartCardNumber() {
        int firstDigit;
        synchronized (random) {
            firstDigit = 1 + random.nextInt(9);
        }
        return firstDigit + generateDigits(9);
    }

    /**
     * Legacy alias for {@link #getSmartCardNumber()} (field name: {@code smartCardNo}).
     *
     * @return 10-digit Smart NID card number
     */
    public String getSmartCardNo() {
        return getSmartCardNumber();
    }

    // =========================================================================
    // 4. Bangladesh Passport Number / passordNo
    // =========================================================================

    /**
     * Generates a 9-character Bangladesh Passport Number (Machine Readable Passport or e-Passport).
     * <p>
     * Format: 1 uppercase letter series followed by 8 numeric digits (e.g., {@code A01234567}).
     *
     * @return 9-character passport number string
     */
    public String getPassportNumber() {
        char series;
        synchronized (random) {
            int idx = random.nextInt(IdentificationData.PASSPORT_SERIES.size());
            series = IdentificationData.PASSPORT_SERIES.get(idx);
        }
        return series + generateDigits(8);
    }

    /**
     * Generates a 9-character Bangladesh Passport Number with the specified series character.
     *
     * @param series series letter (e.g. 'A', 'B', 'E', 'P', 'D')
     * @return 9-character passport number string starting with the specified series letter
     * @throws IllegalArgumentException if series is not an alphabetic character
     */
    public String getPassportNumber(char series) {
        if (!Character.isLetter(series)) {
            throw new IllegalArgumentException("Passport series must be an alphabetic letter: '" + series + "'");
        }
        return Character.toUpperCase(series) + generateDigits(8);
    }

    /**
     * Generates a 9-character Bangladesh Passport Number with the specified series prefix.
     *
     * @param series single-letter series string (e.g. "A", "B", "E")
     * @return 9-character passport number string
     * @throws IllegalArgumentException if series is null, blank, or not a single alphabetic letter
     */
    public String getPassportNumber(String series) {
        if (series == null || series.trim().length() != 1 || !Character.isLetter(series.trim().charAt(0))) {
            throw new IllegalArgumentException("Passport series must be a single alphabetic character: '" + series + "'");
        }
        return getPassportNumber(series.trim().charAt(0));
    }

    /**
     * Legacy alias for {@link #getPassportNumber()} (field name: {@code passordNo}).
     *
     * @return 9-character passport number
     */
    public String getPassordNo() {
        return getPassportNumber();
    }

    // =========================================================================
    // 5. BRTA Driving Licence Number / driving lincenceId
    // =========================================================================

    /**
     * Generates a standard BRTA Driving Licence Number.
     * <p>
     * Format: 2-letter BRTA administrative circle code (e.g. {@code DK}, {@code CT}, {@code SY})
     * followed by 7 numeric digits (total 9 characters, e.g. {@code DK1234567}).
     *
     * @return 9-character BRTA driving licence number string
     */
    public String getDrivingLicenseNumber() {
        String circleCode;
        synchronized (random) {
            int idx = random.nextInt(IdentificationData.BRTA_CIRCLE_CODES.size());
            circleCode = IdentificationData.BRTA_CIRCLE_CODES.get(idx);
        }
        return getDrivingLicenseNumber(circleCode);
    }

    /**
     * Generates a standard BRTA Driving Licence Number for the specified 2-letter circle code.
     *
     * @param circleCode 2-letter circle code (e.g., "DK", "CT", "SY", "KH", "RJ")
     * @return 9-character BRTA driving licence number
     * @throws IllegalArgumentException if circleCode is null, blank, or not a 2-letter code
     */
    public String getDrivingLicenseNumber(String circleCode) {
        if (circleCode == null || circleCode.trim().length() != 2 || !circleCode.trim().matches("^[a-zA-Z]{2}$")) {
            throw new IllegalArgumentException("Invalid BRTA circle code: '" + circleCode +
                    "'. Must be a 2-letter alphabetic code (e.g., 'DK', 'CT', 'SY').");
        }
        return circleCode.trim().toUpperCase() + generateDigits(7);
    }

    /**
     * Legacy alias for {@link #getDrivingLicenseNumber()} (field name: {@code driving lincenceId}).
     *
     * @return 9-character BRTA driving licence number
     */
    public String getDrivingLincenceId() {
        return getDrivingLicenseNumber();
    }

    /**
     * Alias for {@link #getDrivingLicenseNumber()}.
     *
     * @return 9-character BRTA driving licence number
     */
    public String getDrivingLicenseId() {
        return getDrivingLicenseNumber();
    }

    // =========================================================================
    // 6. Citizen Identification Record
    // =========================================================================

    /**
     * Generates a complete {@link CitizenIdentification} record containing all standardized
     * identification and document numbers.
     *
     * @return a new CitizenIdentification record with standardized field values
     */
    public CitizenIdentification getCitizenIdentification() {
        return new CitizenIdentification(
                getNationalIdNumber(),
                getBirthRegistrationNumber(),
                getSmartCardNumber(),
                getPassportNumber(),
                getDrivingLicenseNumber()
        );
    }

    // =========================================================================
    // Internal Helper
    // =========================================================================

    private String generateDigits(int count) {
        StringBuilder sb = new StringBuilder(count);
        synchronized (random) {
            for (int i = 0; i < count; i++) {
                sb.append(random.nextInt(10));
            }
        }
        return sb.toString();
    }
}
