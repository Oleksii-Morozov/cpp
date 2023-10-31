import dev.TextProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SentenceProcessorTest {

    private TextProcessor textProcessor;

    @BeforeEach
    public void setup() {
        textProcessor = new TextProcessor();
    }

    @Test
    public void testExtractUniqueWordsOfGivenLength_ValidInput() {
        String text = "What is this? Is that a banana?";
        String result = textProcessor.extractUniqueWordsOfGivenLength(text, 4);
        String expected = "that\nthis\nWhat\n";
        assertEquals(expected, result);
    }

    @Test
    public void testExtractUniqueWordsOfGivenLength_EmptyText() {
        assertThrows(IllegalArgumentException.class, () -> textProcessor.extractUniqueWordsOfGivenLength("", 4));
    }

    @Test
    public void testExtractUniqueWordsOfGivenLength_NullText() {
        assertThrows(IllegalArgumentException.class, () -> textProcessor.extractUniqueWordsOfGivenLength(null, 4));
    }

    @Test
    public void testExtractUniqueWordsOfGivenLength_InvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> textProcessor.extractUniqueWordsOfGivenLength("What is this?", 0));
    }

    @Test
    public void testExtractUniqueWordsOfGivenLength_InvalidLength2() {
        assertThrows(IllegalArgumentException.class, () -> textProcessor.extractUniqueWordsOfGivenLength("What is this?", -1));
    }

    @Test
    public void testExtractUniqueWordsOfGivenLength_NoInterrogativeSentence() {
        String text = "In a hole in the ground there lived a hobbit.";
        String result = textProcessor.extractUniqueWordsOfGivenLength(text, 4);
        assertEquals("", result);
    }

    @Test
    public void testExtractUniqueWordsOfGivenLength_NoMatchingWords() {
        String text = "Is it a cat?";
        String result = textProcessor.extractUniqueWordsOfGivenLength(text, 5);
        assertEquals("", result);
    }

    @Test
    public void testExtractUniqueWordsOfGivenLength_OnlyOneMatchingWord() {
        String text = "Is it a cat?";
        String result = textProcessor.extractUniqueWordsOfGivenLength(text, 2);
        assertEquals("Is\nit\n", result);
    }

    @Test
    public void testExtractUniqueWordsOfGivenLength_MultipleMatchingWords() {
        String text = "Is it a cat? Is it a fox?";
        String result = textProcessor.extractUniqueWordsOfGivenLength(text, 2);
        assertEquals("Is\nit\n", result);
    }
}
