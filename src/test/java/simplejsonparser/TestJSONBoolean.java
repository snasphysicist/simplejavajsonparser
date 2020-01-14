
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

}
