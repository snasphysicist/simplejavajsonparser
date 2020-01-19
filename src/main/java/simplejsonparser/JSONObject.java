
package simplejsonparser;

import java.util.HashMap;

public class JSONObject
    implements JSONElement {

    private static final char INITIAL_CHARACTER = '{';

    private HashMap<String, JSONElement> members;
    private boolean parsedSuccessfully;
    private String json;

    JSONObject() {
        members = new HashMap<String, JSONElement>();
    }

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

        JSONString nextKey;
        JSONElement nextValue;

        // Remove opening brace, if present
        json = StringManipulation.stripLeadingJSONWhitespace(json);
        if (json.startsWith("{")) {
            json = StringManipulation.removeFirstCharacter(json);
        } else {
            parsedSuccessfully = false;
            return originalJson;
        }

        // Until we reach the close brace or run out of characters
        while (
                json.charAt(0) != '}'
                & json.length() > 0
        ) {
            // Strip whitespace
            json = StringManipulation.stripLeadingJSONWhitespace(json);
            // Parse a key
            nextKey = new JSONString();
            if (nextKey.atStartOf(json)) {
                json = nextKey.parseFrom(json);
                // Fail if could not parse
                if (!nextKey.success()) {
                    parsedSuccessfully = false;
                    return originalJson;
                }
            }
            // Strip whitespace
            json = StringManipulation.stripLeadingJSONWhitespace(json);
            // Should find a colon
            if (json.charAt(0) != ':') {
                parsedSuccessfully = false;
                return originalJson;
            }
            // Move over the colon
            json = StringManipulation.removeFirstCharacter(json);
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
            members.put(nextKey.getValue(), nextValue);
            // Strip whitespace
            json = StringManipulation.stripLeadingJSONWhitespace(json);
            // If string empty, fail (no closing brace)
            if (json.length() == 0) {
                parsedSuccessfully = false;
                return originalJson;
            }
            // If nextChar = }, done
            if (json.charAt(0) == '}') {
                // Throw away the brace
                parsedSuccessfully = true;
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

    public HashMap<String, JSONElement> getObject() {
        return members;
    }

}

