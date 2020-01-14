
package simplejsonparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestJSONNull {

    @Test
    void givenNullAtStartOfJSONStringShouldParseSuccessfully() {
        String jsonString = "null}";
        JSONNull jsonNull = new JSONNull();
        jsonNull.parseFrom(jsonString);
        assertTrue(jsonNull.success());
        assertNull(jsonNull.getValue());
    }

    @Test
    void givenWhitespaceThenNullAtStartOfJSONStringShouldParseSuccessfully() {
        String jsonString = "  \t \r  \n\n null \n\n \r\t }  ";
        JSONNull jsonNull = new JSONNull();
        jsonNull.parseFrom(jsonString);
        assertTrue(jsonNull.success());
        assertNull(jsonNull.getValue());
    }

    @Test
    void givenIncorrectlyCapitalisedNullShouldNotParseSuccessfully() {
        String[] incorrectCases = new String[]{
                "Null",
                "NULL",
                "NuLl"
        };
        for (String incorrect : incorrectCases) {
            String jsonString = String.format(
                    "%s}",
                    incorrect
            );
            JSONNull jsonNull = new JSONNull();
            jsonNull.parseFrom(jsonString);
            assertFalse(jsonNull.success());
        }
    }
    
}
