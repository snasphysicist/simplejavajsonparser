
package simplejsonparser;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJSONObject {

    private static final String KEY = "key";

    private static final String STRING_VALUE = "Test string";
    private static final Integer INTEGER_VALUE = 19;
    private static final Double DOUBLE_VALUE = 43.383543;
    private static final Boolean BOOLEAN_VALUE = false;
    private static final Object NULL_VALUE = null;

    @Test
    void givenValidJSONWithStringValueShouldParseSuccessfully() {
        String jsonString = String.format(
                "{\"%s\":\"%s\"}",
                KEY,
                STRING_VALUE
        );
        JSONObject jsonObject = new JSONObject();
        String remainder = jsonObject.parseFrom(jsonString);
        assertEquals("", remainder);
        assertTrue(jsonObject.success());
        HashMap<String, JSONElement> parsed = jsonObject.getObject();
        assertNotNull(parsed.get(KEY));
        assertTrue(parsed.get(KEY) instanceof JSONString);
        String value = ((JSONString) parsed.get(KEY)).getValue();
        assertEquals(STRING_VALUE, value);
    }

    @Test
    void givenValidJSONWithIntegerValueShouldParseSuccessfully() {
        String jsonString = String.format(
                "{\"%s\":%d}",
                KEY,
                INTEGER_VALUE
        );
        JSONObject jsonObject = new JSONObject();
        String remainder = jsonObject.parseFrom(jsonString);
        assertEquals("", remainder);
        assertTrue(jsonObject.success());
        HashMap<String, JSONElement> parsed = jsonObject.getObject();
        assertNotNull(parsed.get(KEY));
        assertTrue(parsed.get(KEY) instanceof JSONNumber);
        Integer value = ((JSONNumber) parsed.get(KEY)).castToInteger();
        assertEquals(INTEGER_VALUE, value);
    }

    @Test
    void givenValidJSONWithDoubleValueShouldParseSuccessfully() {
        String jsonString = String.format(
                "{\"%s\":%.06f}",
                KEY,
                DOUBLE_VALUE
        );
        JSONObject jsonObject = new JSONObject();
        String remainder = jsonObject.parseFrom(jsonString);
        assertEquals("", remainder);
        assertTrue(jsonObject.success());
        HashMap<String, JSONElement> parsed = jsonObject.getObject();
        assertNotNull(parsed.get(KEY));
        assertTrue(parsed.get(KEY) instanceof JSONNumber);
        Double value = ((JSONNumber) parsed.get(KEY)).castToDouble();
        assertEquals(DOUBLE_VALUE, value);
    }

}
