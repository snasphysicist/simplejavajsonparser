
package simplejsonparser;

import java.util.LinkedList;

public class JSONArray
    implements JSONElement {

    private static final char INITIAL_CHARACTER = '[';
    private JSONElement[] elements;
    private boolean parsedSuccessfully;
    private String json;

    public boolean atStartOf(String toParse) {
        toParse = StringManipulation.stripLeadingJSONWhitespace(toParse);
        if (toParse.length() > 0) {
            return toParse.charAt(0) == INITIAL_CHARACTER;
        } else {
            return false;
        }
    }

    // Returns string not parsed, empty if success, otherwise not empty
    public String parseFrom(String originalJson) {

        json = originalJson;

        LinkedList<JSONElement> parsedElements = new LinkedList<JSONElement>();
        JSONElement nextValue;

        // Until we reach the close brace or run out of characters
        while (
                json.charAt(0) != ']'
                        & json.length() > 0
        ) {
            // Strip whitespace
            json = StringManipulation.stripLeadingJSONWhitespace(json);
            // Parse a value
            nextValue = parseNextValue();
            // Fail if value could not be parsed
            if (nextValue == null) {
                parsedSuccessfully = false;
                return originalJson;
            }
            // Store key and value
            parsedElements.add(nextValue);
            // Strip whitespace
            json = StringManipulation.stripLeadingJSONWhitespace(json);
            // If nextChar = ], done
            if (json.charAt(0) == ']') {
                // Throw away the bracket
                parsedSuccessfully = true;
                // Cast LinkedList to array
                elements = parsedElements.toArray(new JSONElement[]{});
                return json.substring(1);
            }
            // Else, should find a comma
            if (json.charAt(0) != ',') {
                parsedSuccessfully = false;
                return originalJson;
            }
            // Step over comma
            json = StringManipulation.removeFirstCharacter(json);
        }
        // Shouldn't end up here!
        parsedSuccessfully = false;
        return originalJson;
    }


    private JSONElement parseNextValue() {
        JSONElement element;
        // Try boolean
        element = new JSONBoolean();
        if (element.atStartOf(json)) {
            json = element.parseFrom(json);
            if (element.success()) {
                return element;
            }
        }
        // Try null
        element = new JSONNull();
        if (element.atStartOf(json)) {
            json = element.parseFrom(json);
            if (element.success()) {
                return element;
            }
        }
        // Try string
        element = new JSONString();
        if (element.atStartOf(json)) {
            json = element.parseFrom(json);
            if (element.success()) {
                return element;
            }
        }
        // Try number
        element = new JSONNumber();
        if (element.atStartOf(json)) {
            json = element.parseFrom(json);
            if (element.success()) {
                return element;
            }
        }
        // Try object
        element = new JSONObject();
        if (element.atStartOf(json)) {
            json = element.parseFrom(json);
            if (element.success()) {
                return element;
            }
        }
        // Try array
        element = new JSONArray();
        if (element.atStartOf(json)) {
            json = element.parseFrom(json);
            if (element.success()) {
                return element;
            }
        }
        // Null if failed
        return null;
    }

    public boolean success() {
        return parsedSuccessfully;
    }

}
