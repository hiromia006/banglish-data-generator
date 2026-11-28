package com.banglish.data.generator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.*;

public class TextGeneratorTest {

    private TextGenerator generator;

    @BeforeMethod
    public void setUp() {
        generator = new TextGenerator(new Random(42));
    }

    @Test(description = "getWord returns non-empty lower-case word")
    public void testGetWord() {
        for (int i = 0; i < 50; i++) {
            String word = generator.getWord();
            assertNotNull(word);
            assertFalse(word.isBlank());
            assertEquals(word, word.toLowerCase());
        }
    }

    @Test(description = "getWords returns correct word count range")
    public void testGetWords() {
        for (int i = 0; i < 30; i++) {
            String words = generator.getWords(5, 10);
            assertNotNull(words);
            String[] split = words.split(" ");
            assertTrue(split.length >= 5 && split.length <= 10,
                    "Expected 5-10 words, got: " + split.length);
        }
    }

    @Test(description = "getWords with equal min and max")
    public void testGetWordsExactCount() {
        String words = generator.getWords(7, 7);
        assertEquals(words.split(" ").length, 7);
    }

    @Test(description = "getWords with zero returns empty string")
    public void testGetWordsZero() {
        assertEquals(generator.getWords(0, 0), "");
    }

    @Test(description = "getWords avoids immediate word repetition")
    public void testGetWordsNoImmediateRepeat() {
        for (int i = 0; i < 20; i++) {
            String words = generator.getWords(15, 20);
            String[] split = words.split(" ");
            for (int j = 1; j < split.length; j++) {
                assertNotEquals(split[j].toLowerCase(), split[j - 1].toLowerCase(),
                        "Found immediate repeat: " + split[j]);
            }
        }
    }

    @DataProvider(name = "invalidRanges")
    public Object[][] invalidRanges() {
        return new Object[][] {
                {-1, 5},
                {5, 4},
                {-5, -2}
        };
    }

    @Test(dataProvider = "invalidRanges", description = "getWords throws IllegalArgumentException for invalid ranges")
    public void testGetWordsInvalidRange(int min, int max) {
        assertThrows(IllegalArgumentException.class, () -> generator.getWords(min, max));
    }

    @Test(description = "getSentence returns capitalized string ending with punctuation")
    public void testGetSentence() {
        for (int i = 0; i < 50; i++) {
            String sentence = generator.getSentence();
            assertNotNull(sentence);
            assertFalse(sentence.isBlank());
            assertTrue(Character.isUpperCase(sentence.charAt(0)),
                    "Sentence must start with capital letter: " + sentence);
            char last = sentence.charAt(sentence.length() - 1);
            assertTrue(last == '.' || last == '?',
                    "Sentence must end with . or ?, but ended with: " + last);
        }
    }

    @Test(description = "getSentences returns requested sentence count")
    public void testGetSentences() {
        String sentences = generator.getSentences(3, 3);
        assertNotNull(sentences);
        assertFalse(sentences.isBlank());
    }

    @Test(description = "getSentences throws for invalid ranges")
    public void testGetSentencesInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> generator.getSentences(-1, 3));
        assertThrows(IllegalArgumentException.class, () -> generator.getSentences(5, 2));
    }

    @Test(description = "getParagraph returns non-empty paragraph")
    public void testGetParagraph() {
        String paragraph = generator.getParagraph();
        assertNotNull(paragraph);
        assertFalse(paragraph.isBlank());
        assertTrue(paragraph.length() > 20);
    }

    @Test(description = "getParagraphs returns multiple paragraphs separated by double newlines")
    public void testGetParagraphs() {
        String paragraphs = generator.getParagraphs(2, 2);
        assertNotNull(paragraphs);
        String[] split = paragraphs.split("\n\n");
        assertEquals(split.length, 2);
    }

    @Test(description = "getParagraphs throws on invalid range")
    public void testGetParagraphsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> generator.getParagraphs(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> generator.getParagraphs(3, 1));
    }

    @Test(description = "getTitle returns title-cased words")
    public void testGetTitle() {
        for (int i = 0; i < 20; i++) {
            String title = generator.getTitle(3, 6);
            assertNotNull(title);
            String[] split = title.split(" ");
            assertTrue(split.length >= 3 && split.length <= 6);
            for (String word : split) {
                assertTrue(Character.isUpperCase(word.charAt(0)),
                        "Title word must start with uppercase: " + word);
            }
        }
    }

    @Test(description = "getTitle throws on invalid range")
    public void testGetTitleInvalid() {
        assertThrows(IllegalArgumentException.class, () -> generator.getTitle(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> generator.getTitle(4, 2));
    }
}
