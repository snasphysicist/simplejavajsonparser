
package simplejsonparser;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestJSONObject {

    private static final String KEY = "key";
    private static final String NESTED_KEY = "nestedKey";

    private static final String STRING_VALUE = "Test\t\n\r string";
    private static final Integer INTEGER_VALUE = 19;
    private static final Double DOUBLE_VALUE = 43.3843;
    private static final Boolean BOOLEAN_VALUE = false;
    private static final Object NULL_VALUE = null;

    /*
     * Basic sanity checks for
     * json containing each type
     */

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

    @Test
    void givenValidJSONWithBooleanValueShouldParseSuccessfully() {
        String jsonString = String.format(
                "{\"%s\":%b}",
                KEY,
                BOOLEAN_VALUE
        );
        JSONObject jsonObject = new JSONObject();
        String remainder = jsonObject.parseFrom(jsonString);
        assertEquals("", remainder);
        assertTrue(jsonObject.success());
        HashMap<String, JSONElement> parsed = jsonObject.getObject();
        assertNotNull(parsed.get(KEY));
        assertTrue(parsed.get(KEY) instanceof JSONBoolean);
        Boolean value = ((JSONBoolean) parsed.get(KEY)).getValue();
        assertEquals(BOOLEAN_VALUE, value);
    }

    @Test
    void givenValidJSONWithNullValueShouldParseSuccessfully() {
        String jsonString = String.format(
                "{\"%s\":%s}",
                KEY,
                NULL_VALUE
        );
        JSONObject jsonObject = new JSONObject();
        String remainder = jsonObject.parseFrom(jsonString);
        assertEquals("", remainder);
        assertTrue(jsonObject.success());
        HashMap<String, JSONElement> parsed = jsonObject.getObject();
        assertNotNull(parsed.get(KEY));
        assertTrue(parsed.get(KEY) instanceof JSONNull);
        Object value = ((JSONNull) parsed.get(KEY)).getValue();
        assertEquals(NULL_VALUE, value);
    }

    /*
     * Behaviours with regard to json syntax
     * e.g. whitespace, quoting, brackets
     */

    @Test
    void givenValidJSONWithWhitespacePaddingShouldParseSuccessfully() {
        String jsonString = String.format(
                "  {\t\"%s\"\n  : \r\n   \"%s\" }   \t\t\n",
                KEY,
                STRING_VALUE
        );
        JSONObject jsonObject = new JSONObject();
        String remainder = jsonObject.parseFrom(jsonString);
        assertEquals("   \t\t\n", remainder);
        assertTrue(jsonObject.success());
        HashMap<String, JSONElement> parsed = jsonObject.getObject();
        assertNotNull(parsed.get(KEY));
        assertTrue(parsed.get(KEY) instanceof JSONString);
        String value = ((JSONString) parsed.get(KEY)).getValue();
        assertEquals(STRING_VALUE, value);
    }

    @Test
    void givenInvalidJSONKeyWithIncorrectQuotesShouldNotParseSuccessfully() {
        String[] jsonStrings = new String[]{
                String.format(
                        "{%s:\"%s\"}",
                        KEY,
                        STRING_VALUE
                ),
                String.format(
                        "{\"%s:\"%s\"}",
                        KEY,
                        STRING_VALUE
                ),
                String.format(
                        "{%s\":\"%s\"}",
                        KEY,
                        STRING_VALUE
                ),
                String.format(
                        "{\"%s\"\":\"%s\"}",
                        KEY,
                        STRING_VALUE
                ),
                String.format(
                        "{\"\"%s\":\"%s\"}",
                        KEY,
                        STRING_VALUE
                )
        };
        for (String jsonString : jsonStrings) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.parseFrom(jsonString);
            assertFalse(jsonObject.success());
        }
    }

    @Test
    void givenInvalidJSONStringWithImbalancedBracketsShouldNotParseSuccessfully() {
        String[] jsonStrings = new String[]{
                String.format(
                        "\"%s\":\"%s\"}",
                        KEY,
                        STRING_VALUE
                ),
                String.format(
                        "{\"%s\":\"%s\"",
                        KEY,
                        STRING_VALUE
                ),
                String.format(
                        "{\"%s\"}:\"%s\"}",
                        KEY,
                        STRING_VALUE
                ),
                String.format(
                        "{\"%s\":}\"%s\"}",
                        KEY,
                        STRING_VALUE
                ),
                String.format(
                        "{\"%s\"{:\"%s\"}",
                        KEY,
                        STRING_VALUE
                ),
                String.format(
                        "{\"%s\":{\"%s\"}",
                        KEY,
                        STRING_VALUE
                ),
                String.format(
                        "{{\"%s\":\"%s\"}",
                        KEY,
                        STRING_VALUE
                ),
                String.format(
                        "{}\"%s\":\"%s\"}",
                        KEY,
                        STRING_VALUE
                )
        };
        for (String jsonString : jsonStrings) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.parseFrom(jsonString);
            assertFalse(jsonObject.success());
        }
    }

    @Test
    void givenJsonStringWithNestedObjectsShouldParseSuccessfully() {
        String jsonString = String.format(
                "{\"%s\":{\"%s\":%d}}",
                KEY,
                NESTED_KEY,
                INTEGER_VALUE
        );
        JSONObject outer = new JSONObject();
        outer.parseFrom(jsonString);
        assertTrue(outer.success());
        JSONElement inner = outer.getObject().get(KEY);
        assertNotNull(inner);
        assertTrue(inner instanceof JSONObject);
        JSONElement value = ((JSONObject) inner).getObject().get(NESTED_KEY);
        assertNotNull(value);
        assertTrue(value instanceof JSONNumber);
        assertEquals(INTEGER_VALUE, ((JSONNumber) value).castToInteger());
    }

    @Test
    void givenJsonStringWithNestedArraysShouldParseSuccessfully() {
        String jsonString = String.format(
                "{\"%s\":[[\"%s\",%b],[%.4f, %s]]}",
                KEY,
                STRING_VALUE,
                BOOLEAN_VALUE,
                DOUBLE_VALUE,
                NULL_VALUE
        );
        JSONObject jsonObject = new JSONObject();
        jsonObject.parseFrom(jsonString);
        assertTrue(jsonObject.success());
        JSONElement outerValue = jsonObject.getObject().get(KEY);
        assertNotNull(outerValue);
        assertTrue(outerValue instanceof JSONArray);
        JSONElement[] elements = ((JSONArray) outerValue).getElements();
        for (JSONElement element : elements) {
            assertTrue(element instanceof JSONArray);
        }
        JSONElement[] innerElements1 = ((JSONArray) elements[0]).getElements();
        JSONElement[] innerElements2 = ((JSONArray) elements[1]).getElements();
        assertNotNull(innerElements1);
        assertNotNull(innerElements2);
        assertTrue(innerElements1[0] instanceof JSONString);
        assertTrue(innerElements1[1] instanceof JSONBoolean);
        assertTrue(innerElements2[0] instanceof JSONNumber);
        assertTrue(innerElements2[1] instanceof JSONNull);
        assertEquals(STRING_VALUE, ((JSONString) innerElements1[0]).getValue());
        assertEquals(BOOLEAN_VALUE, ((JSONBoolean) innerElements1[1]).getValue());
        assertEquals(DOUBLE_VALUE, ((JSONNumber) innerElements2[0]).castToDouble());
        assertEquals(NULL_VALUE, ((JSONNull) innerElements2[1]).getValue());
    }

}
