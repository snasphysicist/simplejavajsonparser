
package simplejsonparser;

import java.util.regex.Pattern;

public class JSONNumber
        implements JSONElement {

    private static final String NUMBER_FIRST_CHARACTER_REGEX = "\\d|-";
    private boolean parsedSuccessfully;
    private String value;

    public boolean atStartOf(String toParse) {
        toParse = StringManipulation.stripLeadingJSONWhitespace(toParse);
        if (toParse.length() > 0) {
            return (
                    Pattern.matches(
                            NUMBER_FIRST_CHARACTER_REGEX,
                            toParse.substring(0,1)
                    )
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
            // Number can be followed by , or ] or }
            int i = 1;
            while (
                    i < json.length()
                            & !(
                                    json.charAt(i) == ','
                                    || json.charAt(i) == ']'
                                    || json.charAt(i) == '}'
                            )
            ) {
                i++;
            }
            // If we didn't reach a closing character
            if (i == json.length()) {
                parsedSuccessfully = false;
                return json;
            } else {
                value = json.substring(0,i);
                parsedSuccessfully = true;
                return json.substring(i+1);
            }
        }
    }

    public Integer castToInteger() {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean isInteger() {
        return castToInteger() != null;
    }

    public Double castToDouble() {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean isDouble() {
        return castToDouble() != null;
    }

    public String getValue() {
        return value;
    }

    public boolean success() {
        return parsedSuccessfully;
    }

}
