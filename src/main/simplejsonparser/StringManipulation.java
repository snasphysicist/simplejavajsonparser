
package main.simplejsonparser;

class StringManipulation {

    private static final char[] JSON_WHITESPACE = {'\t', '\n', ' ', '\r'};

    static String stripLeadingJSONWhitespace(String from) {
        String stripped = from;
        while (
                stripped.length() > 0
                & isJsonWhitespace(stripped.charAt(0))
        ) {
            stripped = removeFirstCharacter(stripped);
        }
        return stripped;
    }

    private static boolean isJsonWhitespace(char character) {
        for (char whitespace : JSON_WHITESPACE) {
            if (whitespace == character) {
                return true;
            }
        }
        return false;
    }

    static String removeFirstCharacter(String from) {
        return from.substring(1);
    }

}
