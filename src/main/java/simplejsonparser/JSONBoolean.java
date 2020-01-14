
package simplejsonparser;

public class JSONBoolean
        implements JSONElement {

    private static final char[] INITIAL_CHARACTERS = {'t', 'f'};

    private boolean value;
    private boolean parsedSuccessfully;

    public boolean atStartOf(String toParse) {
        toParse = StringManipulation.stripLeadingJSONWhitespace(toParse);
        if (toParse.length() > 0) {
            return (
                    toParse.charAt(0) == INITIAL_CHARACTERS[0]
                    || toParse.charAt(0) == INITIAL_CHARACTERS[1]
            );
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
            json = StringManipulation.stripLeadingJSONWhitespace(json);
            if (json.startsWith("true")) {
                value = true;
                parsedSuccessfully = true;
                return json.substring(4);
            }
            else if (json.startsWith("false")) {
                value = false;
                parsedSuccessfully = true;
                return json.substring(5);
            } else {
                parsedSuccessfully = false;
                return json;
            }
        }
    }

    public boolean getValue() {
        return value;
    }

    public boolean success() {
        return parsedSuccessfully;
    }

}
