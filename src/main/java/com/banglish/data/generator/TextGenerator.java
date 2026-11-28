package com.banglish.data.generator;

import com.banglish.data.generator.data.BanglishWords;

import java.util.Objects;
import java.util.Random;

/**
 * Generator for synthetic Banglish words, sentences, titles, and paragraphs.
 * <p>
 * Banglish uses Roman letters to phonetically represent spoken Bengali.
 * Note: Generated text is synthetic test data designed for QA testing, mock
 * generation, and database seeding, and may not always reflect formal grammar.
 */
public class TextGenerator {

    private final Random random;

    /**
     * Constructs a TextGenerator with a specific random number generator.
     *
     * @param random random source
     */
    public TextGenerator(Random random) {
        this.random = Objects.requireNonNull(random, "Random generator must not be null");
    }

    /**
     * Generates a single random Banglish word.
     *
     * @return a Banglish word in lower case
     */
    public String getWord() {
        int index;
        synchronized (random) {
            index = random.nextInt(BanglishWords.VOCABULARY.size());
        }
        return BanglishWords.VOCABULARY.get(index);
    }

    /**
     * Generates a space-separated string of between {@code min} and {@code max} Banglish words.
     * Avoids immediate consecutive word repetition.
     *
     * @param min minimum number of words (must be &ge; 0)
     * @param max maximum number of words (must be &ge; min)
     * @return space-separated Banglish words
     * @throws IllegalArgumentException if min &lt; 0 or max &lt; min
     */
    public String getWords(int min, int max) {
        validateRange(min, max, "words");
        if (max == 0) {
            return "";
        }
        int count;
        synchronized (random) {
            count = min == max ? min : min + random.nextInt(max - min + 1);
        }
        if (count == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String previousWord = null;
        for (int i = 0; i < count; i++) {
            String word = getWordWithoutImmediateRepeat(previousWord);
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(word);
            previousWord = word;
        }
        return sb.toString();
    }

    /**
     * Generates a single readable Banglish sentence starting with a capital letter
     * and ending with proper punctuation.
     *
     * @return a single Banglish sentence
     */
    public String getSentence() {
        boolean useTemplate;
        int templateIdx;
        synchronized (random) {
            useTemplate = random.nextBoolean();
            templateIdx = random.nextInt(BanglishWords.SENTENCE_TEMPLATES.size());
        }

        if (useTemplate) {
            return BanglishWords.SENTENCE_TEMPLATES.get(templateIdx);
        }

        // Construct synthetic sentence (5 to 10 words)
        int wordCount;
        boolean isQuestion;
        synchronized (random) {
            wordCount = 5 + random.nextInt(6);
            isQuestion = random.nextInt(4) == 0;
        }

        String rawWords = getWords(wordCount, wordCount);
        if (rawWords.isEmpty()) {
            return "";
        }
        String capitalized = Character.toUpperCase(rawWords.charAt(0)) + rawWords.substring(1);
        return capitalized + (isQuestion ? "?" : ".");
    }

    /**
     * Generates between {@code min} and {@code max} Banglish sentences, separated by a single space.
     *
     * @param min minimum number of sentences (must be &ge; 0)
     * @param max maximum number of sentences (must be &ge; min)
     * @return space-separated sentences
     * @throws IllegalArgumentException if min &lt; 0 or max &lt; min
     */
    public String getSentences(int min, int max) {
        validateRange(min, max, "sentences");
        if (max == 0) {
            return "";
        }
        int count;
        synchronized (random) {
            count = min == max ? min : min + random.nextInt(max - min + 1);
        }
        if (count == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String prevSentence = null;
        for (int i = 0; i < count; i++) {
            String sentence;
            int attempts = 0;
            do {
                sentence = getSentence();
                attempts++;
            } while (sentence.equals(prevSentence) && attempts < 5);

            if (i > 0) {
                sb.append(' ');
            }
            sb.append(sentence);
            prevSentence = sentence;
        }
        return sb.toString();
    }

    /**
     * Generates a single paragraph consisting of 3 to 6 Banglish sentences.
     *
     * @return a Banglish paragraph
     */
    public String getParagraph() {
        return getSentences(3, 6);
    }

    /**
     * Generates between {@code min} and {@code max} Banglish paragraphs separated by double newlines.
     *
     * @param min minimum number of paragraphs (must be &ge; 0)
     * @param max maximum number of paragraphs (must be &ge; min)
     * @return paragraphs separated by {@code \n\n}
     * @throws IllegalArgumentException if min &lt; 0 or max &lt; min
     */
    public String getParagraphs(int min, int max) {
        validateRange(min, max, "paragraphs");
        if (max == 0) {
            return "";
        }
        int count;
        synchronized (random) {
            count = min == max ? min : min + random.nextInt(max - min + 1);
        }
        if (count == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                sb.append("\n\n");
            }
            sb.append(getParagraph());
        }
        return sb.toString();
    }

    /**
     * Generates a capitalized title consisting of between {@code min} and {@code max} words.
     *
     * @param min minimum number of words (must be &ge; 0)
     * @param max maximum number of words (must be &ge; min)
     * @return title-cased string of words
     * @throws IllegalArgumentException if min &lt; 0 or max &lt; min
     */
    public String getTitle(int min, int max) {
        validateRange(min, max, "title words");
        if (max == 0) {
            return "";
        }
        int count;
        synchronized (random) {
            count = min == max ? min : min + random.nextInt(max - min + 1);
        }
        if (count == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String prevWord = null;
        for (int i = 0; i < count; i++) {
            String word = getWordWithoutImmediateRepeat(prevWord);
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
            prevWord = word;
        }
        return sb.toString();
    }

    private String getWordWithoutImmediateRepeat(String previous) {
        String word;
        int attempts = 0;
        do {
            word = getWord();
            attempts++;
        } while (word.equalsIgnoreCase(previous) && attempts < 10);
        return word;
    }

    private void validateRange(int min, int max, String label) {
        if (min < 0) {
            throw new IllegalArgumentException("Minimum " + label + " count must be non-negative: " + min);
        }
        if (max < min) {
            throw new IllegalArgumentException("Maximum " + label + " count (" + max + ") cannot be less than minimum (" + min + ")");
        }
    }
}
