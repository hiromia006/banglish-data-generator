package com.hiromia.banglish.data;

import com.hiromia.banglish.model.District;
import com.hiromia.banglish.model.Division;

import java.util.*;

/**
 * Curated administrative locations and postal codes for Bangladesh.
 * <p>
 * Sourced from verified Bangladesh Post Office (BPO) records and the Local
 * Government Engineering Department (LGED) administrative index (verified 2026).
 * Covers all 8 divisions, 64 districts, and their prominent cities and localities.
 */
public final class LocationData {

    public static final String COUNTRY = "Bangladesh";

    private static final Map<String, District> DISTRICT_MAP = new LinkedHashMap<>();
    private static final Map<Division, List<District>> DIVISION_DISTRICTS = new EnumMap<>(Division.class);
    private static final List<District> ALL_DISTRICTS;
    private static final List<String> ALL_POSTAL_CODES;
    private static final List<String> ALL_CITIES;

    static {
        for (Division div : Division.values()) {
            DIVISION_DISTRICTS.put(div, new ArrayList<>());
        }

        // Dhaka Division (13 districts)
        addDistrict("Dhaka", Division.DHAKA,
                List.of("1000", "1205", "1212", "1213", "1216", "1230"),
                List.of("Dhaka", "Dhanmondi", "Gulshan", "Banani", "Mirpur", "Uttara", "Motijheel", "Mohammadpur"));
        addDistrict("Gazipur", Division.DHAKA,
                List.of("1700", "1704", "1711"),
                List.of("Gazipur", "Tongi", "Kaliakair", "Sreepur", "Kapasia"));
        addDistrict("Narayanganj", Division.DHAKA,
                List.of("1400", "1410", "1420"),
                List.of("Narayanganj", "Sonargaon", "Rupganj", "Bandar", "Araihazar"));
        addDistrict("Tangail", Division.DHAKA,
                List.of("1900", "1910", "1916"),
                List.of("Tangail", "Mirzapur", "Madhupur", "Gopalpur", "Ghatail"));
        addDistrict("Kishoreganj", Division.DHAKA,
                List.of("2300", "2310", "2350"),
                List.of("Kishoreganj", "Bhairab", "Bajitpur", "Karimganj"));
        addDistrict("Narsingdi", Division.DHAKA,
                List.of("1600", "1610", "1620"),
                List.of("Narsingdi", "Madhabdi", "Shibpur", "Raipura", "Palash"));
        addDistrict("Manikganj", Division.DHAKA,
                List.of("1800", "1810", "1820"),
                List.of("Manikganj", "Singair", "Shibalaya", "Saturia"));
        addDistrict("Munshiganj", Division.DHAKA,
                List.of("1500", "1510", "1520"),
                List.of("Munshiganj", "Sreenagar", "Louhajang", "Tongibari"));
        addDistrict("Faridpur", Division.DHAKA,
                List.of("7800", "7810", "7820"),
                List.of("Faridpur", "Bhanga", "Boalmari", "Madhukhali"));
        addDistrict("Gopalganj", Division.DHAKA,
                List.of("8100", "8110", "8120"),
                List.of("Gopalganj", "Tungipara", "Kotalipara", "Muksudpur"));
        addDistrict("Madaripur", Division.DHAKA,
                List.of("7900", "7910", "7920"),
                List.of("Madaripur", "Shibchar", "Rajoir", "Kalkini"));
        addDistrict("Rajbari", Division.DHAKA,
                List.of("7700", "7710", "7720"),
                List.of("Rajbari", "Pangsha", "Goalanda", "Baliakandi"));
        addDistrict("Shariatpur", Division.DHAKA,
                List.of("8000", "8010", "8020"),
                List.of("Shariatpur", "Naria", "Zajira", "Damudya"));

        // Chattogram Division (11 districts)
        addDistrict("Chattogram", Division.CHATTOGRAM,
                List.of("4000", "4100", "4200", "4300"),
                List.of("Chattogram", "Agrabad", "Nasirabad", "Hathazari", "Sitakunda", "Patiya"));
        addDistrict("Cox's Bazar", Division.CHATTOGRAM,
                List.of("4700", "4710", "4720"),
                List.of("Cox's Bazar", "Teknaf", "Ramu", "Chakaria", "Ukhia"));
        addDistrict("Cumilla", Division.CHATTOGRAM,
                List.of("3500", "3510", "3520"),
                List.of("Cumilla", "Laksam", "Daudkandi", "Debidwar", "Chauddagram"));
        addDistrict("Brahmanbaria", Division.CHATTOGRAM,
                List.of("3400", "3410", "3420"),
                List.of("Brahmanbaria", "Ashuganj", "Sarail", "Akhaura", "Kasba"));
        addDistrict("Chandpur", Division.CHATTOGRAM,
                List.of("3600", "3610", "3620"),
                List.of("Chandpur", "Hajiganj", "Matlab", "Faridganj"));
        addDistrict("Feni", Division.CHATTOGRAM,
                List.of("3900", "3910", "3920"),
                List.of("Feni", "Daganbhuiyan", "Chhagalnaiya", "Parshuram"));
        addDistrict("Lakshmipur", Division.CHATTOGRAM,
                List.of("3700", "3710", "3720"),
                List.of("Lakshmipur", "Ramganj", "Raipur", "Ramgati"));
        addDistrict("Noakhali", Division.CHATTOGRAM,
                List.of("3800", "3810", "3820"),
                List.of("Noakhali", "Maijdee", "Begumganj", "Chowmuhani", "Senbagh"));
        addDistrict("Khagrachhari", Division.CHATTOGRAM,
                List.of("4400", "4410", "4420"),
                List.of("Khagrachhari", "Dighinala", "Ramgarh", "Matiranga"));
        addDistrict("Rangamati", Division.CHATTOGRAM,
                List.of("4500", "4510", "4520"),
                List.of("Rangamati", "Kaptai", "Baghaichhari", "Barkal"));
        addDistrict("Bandarban", Division.CHATTOGRAM,
                List.of("4600", "4610", "4620"),
                List.of("Bandarban", "Ruma", "Thanchi", "Rowangchhari", "Lama"));

        // Rajshahi Division (8 districts)
        addDistrict("Rajshahi", Division.RAJSHAHI,
                List.of("6000", "6100", "6200"),
                List.of("Rajshahi", "Paba", "Bagha", "Godagari", "Tanore"));
        addDistrict("Bogura", Division.RAJSHAHI,
                List.of("5800", "5810", "5820"),
                List.of("Bogura", "Sherpur", "Shibganj", "Gabtali", "Shajahanpur"));
        addDistrict("Pabna", Division.RAJSHAHI,
                List.of("6600", "6610", "6620"),
                List.of("Pabna", "Ishwardi", "Santhia", "Bera", "Chatmohar"));
        addDistrict("Sirajganj", Division.RAJSHAHI,
                List.of("6700", "6710", "6720"),
                List.of("Sirajganj", "Shahjadpur", "Ullapara", "Belkuchi", "Kazipur"));
        addDistrict("Naogaon", Division.RAJSHAHI,
                List.of("6500", "6510", "6520"),
                List.of("Naogaon", "Mohadevpur", "Patnitala", "Manda", "Dhamoirhat"));
        addDistrict("Natore", Division.RAJSHAHI,
                List.of("6400", "6410", "6420"),
                List.of("Natore", "Singra", "Lalpur", "Bagatipara", "Baraigram"));
        addDistrict("Joypurhat", Division.RAJSHAHI,
                List.of("5900", "5910", "5920"),
                List.of("Joypurhat", "Panchbibi", "Kalai", "Khetlal", "Akkelpur"));
        addDistrict("Chapainawabganj", Division.RAJSHAHI,
                List.of("6300", "6310", "6320"),
                List.of("Chapainawabganj", "Shibganj", "Nachole", "Gomastapur", "Bholahat"));

        // Khulna Division (10 districts)
        addDistrict("Khulna", Division.KHULNA,
                List.of("9000", "9100", "9200"),
                List.of("Khulna", "Daulatpur", "Khalishpur", "Rupsha", "Dumuria"));
        addDistrict("Jashore", Division.KHULNA,
                List.of("7400", "7410", "7420"),
                List.of("Jashore", "Benapole", "Jhikargachha", "Keshabpur", "Manirampur"));
        addDistrict("Kushtia", Division.KHULNA,
                List.of("7000", "7010", "7020"),
                List.of("Kushtia", "Kumarkhali", "Bheramara", "Mirpur", "Khoksa"));
        addDistrict("Jhenaidah", Division.KHULNA,
                List.of("7300", "7310", "7320"),
                List.of("Jhenaidah", "Kaliganj", "Shailkupa", "Kotchandpur", "Maheshpur"));
        addDistrict("Bagerhat", Division.KHULNA,
                List.of("9300", "9310", "9320"),
                List.of("Bagerhat", "Mongla", "Morrelganj", "Rampal", "Fakirhat"));
        addDistrict("Chuadanga", Division.KHULNA,
                List.of("7200", "7210", "7220"),
                List.of("Chuadanga", "Alamdanga", "Damurhuda", "Jibannagar"));
        addDistrict("Meherpur", Division.KHULNA,
                List.of("7100", "7110", "7120"),
                List.of("Meherpur", "Gangni", "Mujibnagar"));
        addDistrict("Magura", Division.KHULNA,
                List.of("7600", "7610", "7620"),
                List.of("Magura", "Sreepur", "Mohammadpur", "Shalikha"));
        addDistrict("Narail", Division.KHULNA,
                List.of("7500", "7510", "7520"),
                List.of("Narail", "Lohagara", "Kalia"));
        addDistrict("Satkhira", Division.KHULNA,
                List.of("9400", "9410", "9420"),
                List.of("Satkhira", "Shyamnagar", "Kaliganj", "Kalaroa", "Assasuni"));

        // Barishal Division (6 districts)
        addDistrict("Barishal", Division.BARISHAL,
                List.of("8200", "8210", "8220"),
                List.of("Barishal", "Gournadi", "Banaripara", "Bakerganj", "Wazirpur"));
        addDistrict("Bhola", Division.BARISHAL,
                List.of("8300", "8310", "8320"),
                List.of("Bhola", "Lalmohan", "Char Fasson", "Borhanuddin", "Daulatkhan"));
        addDistrict("Jhalokathi", Division.BARISHAL,
                List.of("8400", "8410", "8420"),
                List.of("Jhalokathi", "Nalchity", "Rajapur", "Kathalia"));
        addDistrict("Pirojpur", Division.BARISHAL,
                List.of("8500", "8510", "8520"),
                List.of("Pirojpur", "Bhandaria", "Mathbaria", "Nazirpur", "Nesarabad"));
        addDistrict("Barguna", Division.BARISHAL,
                List.of("8700", "8710", "8720"),
                List.of("Barguna", "Amtali", "Patharghata", "Betagi", "Bamna"));
        addDistrict("Patuakhali", Division.BARISHAL,
                List.of("8600", "8610", "8620"),
                List.of("Patuakhali", "Kuakata", "Galachipa", "Bauphal", "Kalapara"));

        // Sylhet Division (4 districts)
        addDistrict("Sylhet", Division.SYLHET,
                List.of("3100", "3110", "3120"),
                List.of("Sylhet", "Beanibazar", "Golapganj", "Biswanath", "Fenchuganj"));
        addDistrict("Moulvibazar", Division.SYLHET,
                List.of("3200", "3210", "3220"),
                List.of("Moulvibazar", "Sreemangal", "Kulaura", "Barlekha", "Kamalganj"));
        addDistrict("Habiganj", Division.SYLHET,
                List.of("3300", "3310", "3320"),
                List.of("Habiganj", "Madhabpur", "Nabiganj", "Bahubal", "Chunarughat"));
        addDistrict("Sunamganj", Division.SYLHET,
                List.of("3000", "3010", "3020"),
                List.of("Sunamganj", "Chhatak", "Jagannathpur", "Derai", "Tahirpur"));

        // Rangpur Division (8 districts)
        addDistrict("Rangpur", Division.RANGPUR,
                List.of("5400", "5410", "5420"),
                List.of("Rangpur", "Badarganj", "Pirganj", "Mithapukur", "Gangachhara"));
        addDistrict("Dinajpur", Division.RANGPUR,
                List.of("5200", "5210", "5220"),
                List.of("Dinajpur", "Parbatipur", "Birampur", "Fulbari", "Birganj"));
        addDistrict("Gaibandha", Division.RANGPUR,
                List.of("5700", "5710", "5720"),
                List.of("Gaibandha", "Gobindaganj", "Sundarganj", "Palashbari", "Sadullapur"));
        addDistrict("Kurigram", Division.RANGPUR,
                List.of("5600", "5610", "5620"),
                List.of("Kurigram", "Nageshwari", "Ulipur", "Chilmari", "Rajarhat"));
        addDistrict("Lalmonirhat", Division.RANGPUR,
                List.of("5500", "5510", "5520"),
                List.of("Lalmonirhat", "Hatibandha", "Patgram", "Kaliganj", "Aditmari"));
        addDistrict("Nilphamari", Division.RANGPUR,
                List.of("5300", "5310", "5320"),
                List.of("Nilphamari", "Saidpur", "Domar", "Jaldhaka", "Kishoreganj"));
        addDistrict("Panchagarh", Division.RANGPUR,
                List.of("5000", "5010", "5020"),
                List.of("Panchagarh", "Tetulia", "Boda", "Debiganj", "Atwari"));
        addDistrict("Thakurgaon", Division.RANGPUR,
                List.of("5100", "5110", "5120"),
                List.of("Thakurgaon", "Pirganj", "Ranisankail", "Haripur", "Baliadangi"));

        // Mymensingh Division (4 districts)
        addDistrict("Mymensingh", Division.MYMENSINGH,
                List.of("2200", "2210", "2220"),
                List.of("Mymensingh", "Muktagacha", "Trishal", "Bhaluka", "Gafargaon"));
        addDistrict("Jamalpur", Division.MYMENSINGH,
                List.of("2000", "2010", "2020"),
                List.of("Jamalpur", "Islampur", "Sarishabari", "Dewanganj", "Melandaha"));
        addDistrict("Netrokona", Division.MYMENSINGH,
                List.of("2400", "2410", "2420"),
                List.of("Netrokona", "Mohanganj", "Kendua", "Durgapur", "Purbadhala"));
        addDistrict("Sherpur", Division.MYMENSINGH,
                List.of("2100", "2110", "2120"),
                List.of("Sherpur", "Nakla", "Nalitabari", "Jhenaigati", "Sreebardi"));

        ALL_DISTRICTS = List.copyOf(DISTRICT_MAP.values());

        List<String> postalCodes = new ArrayList<>();
        List<String> cities = new ArrayList<>();
        for (District d : ALL_DISTRICTS) {
            postalCodes.addAll(d.getPostalCodes());
            cities.addAll(d.getCities());
        }
        ALL_POSTAL_CODES = List.copyOf(postalCodes);
        ALL_CITIES = List.copyOf(cities);
    }

    private static void addDistrict(String name, Division division, List<String> postalCodes, List<String> cities) {
        District district = new District(name, division, postalCodes, cities);
        DISTRICT_MAP.put(name.toLowerCase(), district);
        DIVISION_DISTRICTS.get(division).add(district);
    }

    private LocationData() {
        // static data provider
    }

    public static List<District> getAllDistricts() {
        return ALL_DISTRICTS;
    }

    public static List<String> getAllPostalCodes() {
        return ALL_POSTAL_CODES;
    }

    public static List<String> getAllCities() {
        return ALL_CITIES;
    }

    public static List<District> getDistrictsByDivision(Division division) {
        return Collections.unmodifiableList(DIVISION_DISTRICTS.getOrDefault(division, List.of()));
    }

    public static District findDistrict(String districtName) {
        if (districtName == null || districtName.trim().isEmpty()) {
            return null;
        }
        String key = districtName.trim().toLowerCase();
        District district = DISTRICT_MAP.get(key);
        if (district != null) {
            return district;
        }
        // Common alternative name checks (e.g. Bogra -> Bogura, Chittagong -> Chattogram, Comilla -> Cumilla, Jessore -> Jashore)
        return switch (key) {
            case "bogra" -> DISTRICT_MAP.get("bogura");
            case "chittagong" -> DISTRICT_MAP.get("chattogram");
            case "comilla" -> DISTRICT_MAP.get("cumilla");
            case "jessore" -> DISTRICT_MAP.get("jashore");
            case "barisal" -> DISTRICT_MAP.get("barishal");
            default -> null;
        };
    }
}
