
package simplejsonparser;

public class JSONString
    implements JSONElement {

    private static final char INITIAL_CHARACTER = '\"';

    private String value;
    private boolean parsedSuccessfully;

    public boolean atStartOf(String toParse) {
        toParse = StringManipulation.stripLeadingJSONWhitespace(toParse);
        if (toParse.length() > 0) {
            return toParse.charAt(0) == INITIAL_CHARACTER;
        } else {
            return false;
        }
    }

    // Returns *rest* of string
    public String parseFrom(String json) {
        if (!atStartOf(json)) {
            parsedSuccessfully = false;
            return json;
        } else {

            // Go until we find the closing "
            int i = 1;
            while (
                    i < json.length()
                    & (
                            json.charAt(i) != '"'
                            || (
                                    json.charAt(i) == '"'
                                    & json.charAt(i-1) == '\\'   // Account for escaped quotes (ignore)
                            )
                    )
            ) {
                i++;
            }
            // If no closing "
            if (i == json.length()) {
                parsedSuccessfully = false;
                return json;
            } else {
                value = json.substring(1, i); // Lose the quotation marks
                return json.substring(i+1);
            }
        }
    }

    public String getValue() {
        return value;
    }

    public boolean success() {
        return parsedSuccessfully;
    }

}
