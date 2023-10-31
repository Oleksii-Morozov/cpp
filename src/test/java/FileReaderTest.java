import org.junit.jupiter.api.Test;
import util.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileReaderTest {
    @Test
    public void testRead_ValidInput() {
        String result = FileReader.read("./src/main/resources/Hobbit.txt", 3);
        assertEquals("J.R.R. TolkienThe HOBBITChapter I", result);
    }

    @Test
    public void testRead_EmptyPath() {
        assertThrows(IllegalArgumentException.class, () -> FileReader.read("", 3));
    }
}
