package com.hiromia.banglish;

/**
 * Interactive demonstration entry point for the Banglish Data Generator library.
 * <p>
 * Displays sample generated data across all supported capabilities:
 * text, names, mobile phones, postal codes, and locations.
 */
public class Main {

    public static void main(String[] args) {
        Banglish banglish = BanglishGenerator.getInstance();

        System.out.println("================================================================================");
        System.out.println("            BANGLISH DATA GENERATOR — DEMONSTRATION (v1.0.0)                    ");
        System.out.println("================================================================================\n");

        // 1. Banglish Text Generation
        System.out.println("--- [1. Banglish Text Generation] ---");
        System.out.println("Random Word        : " + banglish.getWord());
        System.out.println("Words (5 to 8)     : " + banglish.getWords(5, 8));
        System.out.println("Sentence           : " + banglish.getSentence());
        System.out.println("Title              : " + banglish.getTitle(3, 5));
        System.out.println("Paragraph          :\n" + banglish.getParagraph());
        System.out.println();

        // 2. Bangladeshi Names
        System.out.println("--- [2. Bangladeshi Names] ---");
        System.out.println("Full Name          : " + banglish.getName());
        System.out.println("First Name         : " + banglish.getFirstName());
        System.out.println("Last Name          : " + banglish.getLastName());
        System.out.println("Male Full Name     : " + banglish.getMaleName());
        System.out.println("Female Full Name   : " + banglish.getFemaleName());
        System.out.println();

        // 3. Bangladesh Mobile and Phone Numbers
        System.out.println("--- [3. Bangladesh Mobile and Phone Numbers] ---");
        System.out.println("Local Mobile (11d) : " + banglish.getMobileNumber());
        System.out.println("International Phone: " + banglish.getPhoneWithCountryCode());
        System.out.println("Grameenphone (017) : " + banglish.getMobileNumber("017"));
        System.out.println("Banglalink   (019) : " + banglish.getMobileNumber("019"));
        System.out.println("Robi         (018) : " + banglish.getMobileNumber("018"));
        System.out.println("Teletalk     (015) : " + banglish.getMobileNumber("015"));
        System.out.println();

        // 4. Bangladesh Postal Codes (Verified BPO Dataset)
        System.out.println("--- [4. Verified Bangladesh Postal Codes (BPO)] ---");
        System.out.println("Random Postal Code : " + banglish.getZipCode());
        System.out.println("Dhaka District     : " + banglish.getZipCode("Dhaka"));
        System.out.println("Chattogram District: " + banglish.getZipCode("Chattogram"));
        System.out.println("Sylhet District    : " + banglish.getZipCode("Sylhet"));
        System.out.println("Rajshahi District  : " + banglish.getZipCode("Rajshahi"));
        System.out.println("Khulna District    : " + banglish.getZipCode("Khulna"));
        System.out.println();

        // 5. Bangladesh Administrative Locations
        System.out.println("--- [5. Bangladesh Administrative Locations] ---");
        System.out.println("Country            : " + banglish.getCountry());
        System.out.println("Random Division    : " + banglish.getDivision());
        System.out.println("Random District    : " + banglish.getDistrict());
        System.out.println("Random City        : " + banglish.getCity());
        System.out.println("District in Sylhet : " + banglish.getDistrict("Sylhet"));
        System.out.println("City in Bogura     : " + banglish.getCity("Bogura"));
        System.out.println("Districts in Dhaka : " + banglish.getDistrictsByDivision("Dhaka"));
        System.out.println("Cities in Dhaka    : " + banglish.getCitiesByDistrict("Dhaka"));
        System.out.println();

        // 6. Bangladesh Citizen Identification & Document Numbers
        System.out.println("--- [6. Bangladesh Citizen Identification & Official Documents] ---");
        System.out.println("National ID (17d)  : " + banglish.getNationalIdNumber() + " (field: nationalIdNumber / nationalVoterNo)");
        System.out.println("National ID (10d)  : " + banglish.getNationalIdNumber(10));
        System.out.println("National ID (13d)  : " + banglish.getNationalIdNumber(13));
        System.out.println("Birth Reg No (17d) : " + banglish.getBirthRegistrationNumber() + " (field: birthRegistrationNumber / JonmnibondonNo)");
        System.out.println("Smart Card No (10d): " + banglish.getSmartCardNumber() + " (field: smartCardNumber / smartCardNo)");
        System.out.println("Passport No (9ch)  : " + banglish.getPassportNumber() + " (field: passportNumber / passordNo)");
        System.out.println("Driving Licence    : " + banglish.getDrivingLicenseNumber() + " (field: drivingLicenseNumber / driving lincenceId)");
        System.out.println("Dhaka Licence (DK) : " + banglish.getDrivingLicenseNumber("DK"));
        System.out.println("Citizen POJO       : " + banglish.getCitizenIdentification());
        System.out.println();

        // 7. Deterministic Seeding Demo
        System.out.println("--- [7. Deterministic Seed Demo (Seed = 2026L)] ---");
        Banglish seedA = BanglishGenerator.getInstance(2026L);
        Banglish seedB = BanglishGenerator.getInstance(2026L);
        System.out.println("Run A Sentence     : " + seedA.getSentence());
        System.out.println("Run B Sentence     : " + seedB.getSentence());
        System.out.println("Run A Mobile       : " + seedA.getMobileNumber());
        System.out.println("Run B Mobile       : " + seedB.getMobileNumber());
        System.out.println("Run A NID (17d)    : " + seedA.getNationalIdNumber());
        System.out.println("Run B NID (17d)    : " + seedB.getNationalIdNumber());
        System.out.println("Identical Outputs  : " + (seedA.getSentence().equals(seedB.getSentence())
                && seedA.getMobileNumber().equals(seedB.getMobileNumber())
                && seedA.getNationalIdNumber().equals(seedB.getNationalIdNumber()) ? "YES (Verified)" : "NO"));

        System.out.println("\n================================================================================");
        System.out.println("            ALL BANGLISH GENERATORS EXECUTED SUCCESSFULLY!                      ");
        System.out.println("================================================================================");
    }
}
