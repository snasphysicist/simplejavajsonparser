
package simplejsonparser;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJSONObject {

    @Test
    public void givenValidJSONWithStringShouldParseSuccessfully() {
        String jsonString = "{\"key\":\"Test string\"}";
        JSONObject jsonObject = new JSONObject();
        String remainder = jsonObject.parseFrom(jsonString);
        assertEquals("", remainder);
        assertTrue(jsonObject.success());
        HashMap<String, JSONElement> parsed = jsonObject.getObject();
        assertNotNull(parsed.get("key"));
        assertTrue(parsed.get("key") instanceof JSONString);
    }

}
