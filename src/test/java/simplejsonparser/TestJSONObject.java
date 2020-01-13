
package simplejsonparser;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJSONObject {

    @Test
    public void givenValidJSONWithStringValueShouldParseSuccessfully() {
        String jsonString = "{\"key\":\"Test string\"}";
        JSONObject jsonObject = new JSONObject();
        String remainder = jsonObject.parseFrom(jsonString);
        assertEquals("", remainder);
        assertTrue(jsonObject.success());
        HashMap<String, JSONElement> parsed = jsonObject.getObject();
        assertNotNull(parsed.get("key"));
        assertTrue(parsed.get("key") instanceof JSONString);
    }

    @Test
    public void givenValidJSONWithIntegerValueShouldParseSuccessfully() {
        String jsonString = "{\"key\":1}";
        JSONObject jsonObject = new JSONObject();
        String remainder = jsonObject.parseFrom(jsonString);
        assertEquals("", remainder);
        assertTrue(jsonObject.success());
        HashMap<String, JSONElement> parsed = jsonObject.getObject();
        assertNotNull(parsed.get("key"));
        assertTrue(parsed.get("key") instanceof JSONNumber);
        Integer value = ((JSONNumber) parsed.get("key")).castToInteger();
        assertEquals(1, value);
    }

}
