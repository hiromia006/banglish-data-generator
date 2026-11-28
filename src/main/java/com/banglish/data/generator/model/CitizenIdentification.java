package com.banglish.data.generator.model;

import java.util.Objects;

/**
 * Model representing standardized Bangladesh citizen identification and document numbers.
 * <p>
 * Standardized field names:
 * <ul>
 *   <li>{@code nationalIdNumber} — National ID (NID) Number (legacy alias: {@code nationalVoterNo})</li>
 *   <li>{@code birthRegistrationNumber} — Birth Registration Number (legacy alias: {@code JonmnibondonNo})</li>
 *   <li>{@code smartCardNumber} — Smart NID Card Number (legacy alias: {@code smartCardNo})</li>
 *   <li>{@code passportNumber} — Bangladesh Passport Number (legacy alias: {@code passordNo})</li>
 *   <li>{@code drivingLicenseNumber} — BRTA Driving Licence Number (legacy alias: {@code driving lincenceId})</li>
 * </ul>
 */
public class CitizenIdentification {

    private final String nationalIdNumber;
    private final String birthRegistrationNumber;
    private final String smartCardNumber;
    private final String passportNumber;
    private final String drivingLicenseNumber;

    /**
     * Constructs a new CitizenIdentification record with standardized field values.
     *
     * @param nationalIdNumber        standardized National ID (NID) number (10, 13, or 17 digits)
     * @param birthRegistrationNumber standardized 17-digit Birth Registration Number
     * @param smartCardNumber         standardized 10-digit Smart NID Card Number
     * @param passportNumber          standardized 9-character Bangladesh Passport Number
     * @param drivingLicenseNumber    standardized BRTA Driving Licence Number
     */
    public CitizenIdentification(String nationalIdNumber,
                                 String birthRegistrationNumber,
                                 String smartCardNumber,
                                 String passportNumber,
                                 String drivingLicenseNumber) {
        this.nationalIdNumber = Objects.requireNonNull(nationalIdNumber, "nationalIdNumber must not be null");
        this.birthRegistrationNumber = Objects.requireNonNull(birthRegistrationNumber, "birthRegistrationNumber must not be null");
        this.smartCardNumber = Objects.requireNonNull(smartCardNumber, "smartCardNumber must not be null");
        this.passportNumber = Objects.requireNonNull(passportNumber, "passportNumber must not be null");
        this.drivingLicenseNumber = Objects.requireNonNull(drivingLicenseNumber, "drivingLicenseNumber must not be null");
    }

    // =========================================================================
    // Standardized Getters
    // =========================================================================

    /**
     * Returns the standardized National ID (NID) Number.
     *
     * @return National ID number
     */
    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    /**
     * Returns the standardized Birth Registration Number.
     *
     * @return Birth Registration number
     */
    public String getBirthRegistrationNumber() {
        return birthRegistrationNumber;
    }

    /**
     * Returns the standardized Smart NID Card Number.
     *
     * @return Smart NID card number
     */
    public String getSmartCardNumber() {
        return smartCardNumber;
    }

    /**
     * Returns the standardized Bangladesh Passport Number.
     *
     * @return Bangladesh Passport number
     */
    public String getPassportNumber() {
        return passportNumber;
    }

    /**
     * Returns the standardized BRTA Driving Licence Number.
     *
     * @return BRTA Driving Licence number
     */
    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    // =========================================================================
    // Backward Compatibility / Legacy Aliases
    // =========================================================================

    /**
     * Alias for {@link #getNationalIdNumber()} (legacy name: nationalVoterNo).
     *
     * @return National ID number
     */
    public String getNationalVoterNo() {
        return nationalIdNumber;
    }

    /**
     * Alias for {@link #getBirthRegistrationNumber()} (legacy name: JonmnibondonNo).
     *
     * @return Birth Registration number
     */
    public String getJonmnibondonNo() {
        return birthRegistrationNumber;
    }

    /**
     * Alias for {@link #getSmartCardNumber()} (legacy name: smartCardNo).
     *
     * @return Smart NID card number
     */
    public String getSmartCardNo() {
        return smartCardNumber;
    }

    /**
     * Alias for {@link #getPassportNumber()} (legacy name: passordNo).
     *
     * @return Bangladesh Passport number
     */
    public String getPassordNo() {
        return passportNumber;
    }

    /**
     * Alias for {@link #getDrivingLicenseNumber()} (legacy name: driving lincenceId).
     *
     * @return BRTA Driving Licence number
     */
    public String getDrivingLincenceId() {
        return drivingLicenseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CitizenIdentification that)) return false;
        return Objects.equals(nationalIdNumber, that.nationalIdNumber) &&
                Objects.equals(birthRegistrationNumber, that.birthRegistrationNumber) &&
                Objects.equals(smartCardNumber, that.smartCardNumber) &&
                Objects.equals(passportNumber, that.passportNumber) &&
                Objects.equals(drivingLicenseNumber, that.drivingLicenseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalIdNumber, birthRegistrationNumber, smartCardNumber, passportNumber, drivingLicenseNumber);
    }

    @Override
    public String toString() {
        return "CitizenIdentification{" +
                "nationalIdNumber='" + nationalIdNumber + '\'' +
                ", birthRegistrationNumber='" + birthRegistrationNumber + '\'' +
                ", smartCardNumber='" + smartCardNumber + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                '}';
    }
}
