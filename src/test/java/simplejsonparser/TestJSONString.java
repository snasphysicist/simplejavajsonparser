
package simplejsonparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestJSONString {

    private final static String VALUE = "Test \n\r\t string";

    @Test
    void givenTextBetweenQuotationMarksShouldParseSuccessfully() {
        JSONString jsonString = new JSONString();
        jsonString.parseFrom(
                String.format(
                        "\"%s\"",
                        VALUE
                )
        );
        assertTrue(jsonString.success());
        assertEquals(VALUE, jsonString.getValue());
    }

    @Test
    void givenTextBetweenIncorrectQuotationMarksShouldNotParseSuccessfully() {
        String[] jsonStrings = new String[] {
                String.format(
                        "%s\"",
                        VALUE
                ),
                String.format(
                        "\"%s",
                        VALUE
                ),
                String.format(
                        "'%s'",
                        VALUE
                )
        };
        for (String jsonString : jsonStrings) {
            JSONString parsed = new JSONString();
            parsed.parseFrom(jsonString);
            assertFalse(parsed.success());
        }
    }

}