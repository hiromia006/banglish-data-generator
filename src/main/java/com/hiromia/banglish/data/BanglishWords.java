package com.hiromia.banglish.data;

import java.util.List;

/**
 * Curated vocabulary and sentence templates for natural Banglish text generation.
 * <p>
 * Banglish uses Roman letters to phonetically represent spoken Bengali.
 * Note: Generated text is synthetic test data designed for QA testing, mock
 * generation, and database seeding, and may not always reflect formal grammar.
 */
public final class BanglishWords {

    private BanglishWords() {
        // static constants only
    }

    public static final List<String> VOCABULARY = List.of(
            // Pronouns & Subjects
            "ami", "apni", "tumi", "amra", "tomra", "apnara", "shey", "tara",
            "amader", "tomader", "apnader", "tar", "tader", "amar", "tomar", "apnar",
            // Temporal / Time
            "ajke", "kalke", "porshu", "ekhon", "shokal", "dupur", "bikal", "sondha",
            "raat", "shobshomoy", "majhemajhe", "protidin", "shonibar", "robibar",
            "shombar", "mongolbar", "budhbar", "brihoshpotibar", "shukrobar",
            // Common Places & Concepts
            "office", "bashay", "bari", "bazar", "university", "college", "school",
            "station", "dukan", "rasta", "shohor", "gram", "hospital", "bank",
            "desh", "room", "floor",
            // Nouns & Entities
            "kaj", "meeting", "shomoy", "kotha", "khabar", "bhat", "cha", "coffee",
            "phone", "message", "email", "taka", "project", "code", "testing",
            "computer", "laptop", "report", "gari", "ticket", "shubidha", "somossya",
            "porikkha", "chuti", "plan", "update", "document", "file", "system",
            // Verbs
            "jabo", "jacchi", "gechi", "ashi", "ashbo", "asche", "korchi", "korbo",
            "koro", "korun", "korechi", "khabo", "kheyechi", "dekhi", "dekhbo",
            "shunchi", "shunbo", "bolchi", "bolbo", "bolun", "chai", "pari",
            "parbo", "thaki", "thakbo", "hochhe", "hobe", "hoyeche", "ache", "chilo",
            "janan", "pathan", "bolte", "dekhte", "shunte", "bujhte",
            // Adjectives & Adverbs
            "bhalo", "khub", "onnek", "sundor", "shohoj", "kothin", "druto", "aste",
            "shob", "kichu", "notun", "purano", "thik", "bhul", "boro", "choto",
            "beshi", "kom", "shundor", "shantopurno", "joruri", "shorol", "darun",
            // Connectives, Questions & Particles
            "ebong", "kintu", "ar", "othoba", "karon", "tai", "ki", "keno", "kothay",
            "kokhon", "kivabe", "kemon", "kar", "naki", "to", "na", "ha", "shobai",
            "dhonnobad", "inshallah", "bhai", "apa", "madam", "sir"
    );

    /**
     * Common conversational templates to produce natural-sounding Banglish sentences.
     * Tokens:
     * {SUBJECT}, {TIME}, {PLACE}, {OBJECT}, {VERB}, {ADJECTIVE}, {GREETING}
     */
    public static final List<String> SENTENCE_TEMPLATES = List.of(
            "Ami ajke office e jabo.",
            "Apni kemon achen?",
            "Kalke amader meeting ache.",
            "Ami software testing shikhte chai.",
            "Kajta shonibar er moddhe shesh korte hobe.",
            "Tumi ki ekhon free acho?",
            "Amra shobai eksathe dupure lunch korbo.",
            "Apnar notun project er report ready hoyeche ki?",
            "Eita khub sundor ebong shohoj ekta system.",
            "Ajke bikal bela ektu cha khawa jak.",
            "Amader team er performance khub bhalo chilo.",
            "Dhonnobad, shob information thik ache.",
            "Kalke sokale amake ekta reminder diyen.",
            "System er update ta successfully complete hoyeche.",
            "Apni kobe Dhaka ashben?",
            "Ei issue ta niye amra meeting e kotha bolbo.",
            "Office er kaj shesh kore bashay jabo.",
            "Report ta check kore apnake janachhi.",
            "Ei bishoye apnar motamot ki?",
            "Shob documents email e pathiye diyechi."
    );
}
