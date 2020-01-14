
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

}
