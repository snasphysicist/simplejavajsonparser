
package simplejsonparser;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TestJSONArray {

    private static final Integer INTEGER_VALUE = 10;
    private static final String STRING_VALUE = "TE[ES]T";
    private static final Boolean BOOLEAN_VALUE = false;

    @Test
    void givenArrayAtStartOfJSONStringShouldParseSuccessfully() {
        String jsonString = String.format(
                "[%d,\"%s\",%b]}",
                INTEGER_VALUE,
                STRING_VALUE,
                BOOLEAN_VALUE
        );
        JSONArray jsonArray = new JSONArray();
        String remainder = jsonArray.parseFrom(jsonString);
        assertTrue(jsonArray.success());
        assertEquals("}", remainder);
        JSONElement[] elements = jsonArray.getElements();
        assertEquals(3, elements.length);
        assertTrue(elements[0] instanceof JSONNumber);
        assertTrue(elements[1] instanceof JSONString);
        assertTrue(elements[2] instanceof JSONBoolean);
        assertEquals(INTEGER_VALUE, ((JSONNumber) elements[0]).castToInteger());
        assertEquals(STRING_VALUE, ((JSONString) elements[1]).getValue());
        assertEquals(BOOLEAN_VALUE, ((JSONBoolean) elements[2]).getValue());
    }

    @Test
    void givenArrayWithExtraWhitespaceAtStartOfJSONStringShouldParseSuccessfully() {
        String whitespaceString = String.format(
                " \n \r %s  \t  ",
                STRING_VALUE
        );
        String jsonString = String.format(
                "  [ \n\r  %d\t \t, \"%s\"  \r\r\t  ,\n %b  \t\r  ]}",
                INTEGER_VALUE,
                whitespaceString,
                BOOLEAN_VALUE
        );
        JSONArray jsonArray = new JSONArray();
        String remainder = jsonArray.parseFrom(jsonString);
        assertTrue(jsonArray.success());
        assertEquals("}", remainder);
        JSONElement[] elements = jsonArray.getElements();
        assertEquals(3, elements.length);
        assertTrue(elements[0] instanceof JSONNumber);
        assertTrue(elements[1] instanceof JSONString);
        assertTrue(elements[2] instanceof JSONBoolean);
        System.out.println(elements[0]);
        assertEquals(INTEGER_VALUE, ((JSONNumber) elements[0]).castToInteger());
        assertEquals(whitespaceString, ((JSONString) elements[1]).getValue());
        assertEquals(BOOLEAN_VALUE, ((JSONBoolean) elements[2]).getValue());
    }

    @Test
    void givenArrayWithImproperSyntaxAtStartOfJSONStringShouldNotParseSuccessfully() {
        String[] jsonStrings = new String[] {
                String.format(
                        "%d,\"%s\",%b]}",
                        INTEGER_VALUE,
                        STRING_VALUE,
                        BOOLEAN_VALUE
                ),
                String.format(
                        "[%d,\"%s\",%b}",
                        INTEGER_VALUE,
                        STRING_VALUE,
                        BOOLEAN_VALUE
                ),
                String.format(
                        "[%d,]\"%s\",%b]}",
                        INTEGER_VALUE,
                        STRING_VALUE,
                        BOOLEAN_VALUE
                ),
                String.format(
                        "[%d[,\"%s\",%b]}",
                        INTEGER_VALUE,
                        STRING_VALUE,
                        BOOLEAN_VALUE
                ),
                String.format(
                        "[%d,[\"%s\",%b]}",
                        INTEGER_VALUE,
                        STRING_VALUE,
                        BOOLEAN_VALUE
                ),
                String.format(
                        "[%d,,\"%s\",%b]}",
                        INTEGER_VALUE,
                        STRING_VALUE,
                        BOOLEAN_VALUE
                ),
                String.format(
                        "[%d\"%s\",%b]}",
                        INTEGER_VALUE,
                        STRING_VALUE,
                        BOOLEAN_VALUE
                ),
                String.format(
                        "[%d,\"%s\",%b,]}",
                        INTEGER_VALUE,
                        STRING_VALUE,
                        BOOLEAN_VALUE
                ),
                String.format(
                        ",[%d,\"%s\",%b]}",
                        INTEGER_VALUE,
                        STRING_VALUE,
                        BOOLEAN_VALUE
                ),
                String.format(
                        "[[%d,\"%s\",%b]}",
                        INTEGER_VALUE,
                        STRING_VALUE,
                        BOOLEAN_VALUE
                )
        };
        for (String incorrect : jsonStrings) {
            JSONArray jsonArray = new JSONArray();
            jsonArray.parseFrom(incorrect);
            assertFalse(jsonArray.success());
        }
    }

}
