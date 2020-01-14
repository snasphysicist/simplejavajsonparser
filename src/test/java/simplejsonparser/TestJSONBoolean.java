
package simplejsonparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class TestJSONBoolean {

    @Test
    void givenTrueBooleanAtStartOfJSONStringShouldParseSuccessfully() {
        String jsonString = "true}";
        JSONBoolean parsed = new JSONBoolean();
        parsed.parseFrom(jsonString);
        assertTrue(parsed.success());
        assertTrue(parsed.getValue());
    }

    @Test
    void givenFalseBooleanAtStartOfJSONStringShouldParseSuccessfully() {
        String jsonString = "false}";
        JSONBoolean parsed = new JSONBoolean();
        parsed.parseFrom(jsonString);
        assertTrue(parsed.success());
        assertFalse(parsed.getValue());
    }

    @Test
    void givenWhitespaceThenBooleanAtStartOfJSONStringShouldParseSuccessfully() {
        String jsonString = "  \t  \n  \r    true   }";
        JSONBoolean parsed = new JSONBoolean();
        parsed.parseFrom(jsonString);
        assertTrue(parsed.success());
        assertTrue(parsed.getValue());
    }

    @Test
    void givenBooleansWithIncorrectCapitalisationShouldNotParseSuccessfully() {
        String[] incorrectCases = new String[]{
                "TRUE",
                "FALSE",
                "True",
                "False",
                "TrUe",
                "FaLsE"
        };
        for (String incorrect : incorrectCases) {
            String jsonString = String.format(
                    "%s}",
                    incorrect
            );
            JSONBoolean parser = new JSONBoolean();
            parser.parseFrom(jsonString);
            assertFalse(parser.success());
        }
    }

}
