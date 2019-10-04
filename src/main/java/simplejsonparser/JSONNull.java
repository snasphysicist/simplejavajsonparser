
package simplejsonparser;

public class JSONNull
        implements JSONElement {

    private static final char INITIAL_CHARACTER = 'n';

    private Object value;
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
            if (json.startsWith("null")) {
                value = null;
                parsedSuccessfully = true;
                return json.substring(4);
            } else {
                parsedSuccessfully = false;
                return json;
            }
        }
    }

    public Object getValue() {
        return value;
    }

    public boolean success() {
        return parsedSuccessfully;
    }

}
