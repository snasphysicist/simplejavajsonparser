
package simplejsonparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class TestJSONNumber {

    private static final Integer POSITIVE_INTEGER = 29;
    private static final Integer NEGATIVE_INTEGER = -1943;
    private static final Double POSITIVE_DOUBLE = 219.3841;
    private static final Double NEGATIVE_DOUBLE = -7620.2947;

    @Test
    void givenPositiveIntegerAtStartOfJSONStringShouldParseSuccessfully() {
        String jsonString = String.format(
                "%d}",
                POSITIVE_INTEGER
        );
        JSONNumber parser = new JSONNumber();
        String remainder = parser.parseFrom(jsonString);
        assertTrue(parser.success());
        assertEquals("}", remainder);
        assertTrue(parser.isInteger());
        assertEquals(POSITIVE_INTEGER, parser.castToInteger());
    }

    @Test
    void givenNegativeIntegerAtStartOfJSONStringShouldParseSuccessfully() {
        String jsonString = String.format(
                "%d}",
                NEGATIVE_INTEGER
        );
        JSONNumber parser = new JSONNumber();
        String remainder = parser.parseFrom(jsonString);
        assertTrue(parser.success());
        assertEquals("}", remainder);
        assertTrue(parser.isInteger());
        assertEquals(NEGATIVE_INTEGER, parser.castToInteger());
    }

    @Test
    void givenPositiveFloatAtStartOfJSONStringShouldParseSuccessfully() {
        String jsonString = String.format(
                "%.4f}",
                POSITIVE_DOUBLE
        );
        JSONNumber parser = new JSONNumber();
        String remainder = parser.parseFrom(jsonString);
        assertTrue(parser.success());
        assertEquals("}", remainder);
        assertFalse(parser.isInteger());
        assertTrue(parser.isDouble());
        assertEquals(POSITIVE_DOUBLE, parser.castToDouble());
    }

    @Test
    void givenNegativeFloatAtStartOfJSONStringShouldParseSuccessfully() {
        String jsonString = String.format(
                "%.4f}",
                NEGATIVE_DOUBLE
        );
        JSONNumber parser = new JSONNumber();
        String remainder = parser.parseFrom(jsonString);
        assertTrue(parser.success());
        assertEquals("}", remainder);
        assertFalse(parser.isInteger());
        assertTrue(parser.isDouble());
        assertEquals(NEGATIVE_DOUBLE, parser.castToDouble());
    }

}
