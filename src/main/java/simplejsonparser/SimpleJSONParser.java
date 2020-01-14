
package simplejsonparser;

public class SimpleJSONParser {

    public static void main(String[] args) {
        JSONBoolean jsonBoolean = new JSONBoolean();
        System.out.println(
                jsonBoolean.parseFrom(
                        "  \t  \n"
                        + "    \n"
                        + "    true   }"
                )
        );
    }

}
