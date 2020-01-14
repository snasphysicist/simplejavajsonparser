
package simplejsonparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestJSONNull {

    @Test
    void givenNullAtStartOfJSONStringShouldParseSuccesfully() {
        String jsonString = "null}";
        JSONNull jsonNull = new JSONNull();
        jsonNull.parseFrom(jsonString);
        assertTrue(jsonNull.success());
        assertNull(jsonNull.getValue());
    }

    @Test
    void givenWhitespaceThenNullAtStartOfJSONStringShouldParseSuccesfully() {
        String jsonString = "  \t \r  \n\n null \n\n \r\t }  ";
        JSONNull jsonNull = new JSONNull();
        jsonNull.parseFrom(jsonString);
        assertTrue(jsonNull.success());
        assertNull(jsonNull.getValue());
    }

}
