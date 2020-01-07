
package simplejsonparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestJSONString {

    @Test
    void givenTextBetweenQuotationMarksShouldParseSuccessfully() {
        JSONString jsonString = new JSONString();
        jsonString.parseFrom("\"Test string\"");
        assertTrue(jsonString.success());
        assertEquals("Test string", jsonString.getValue());
    }
}