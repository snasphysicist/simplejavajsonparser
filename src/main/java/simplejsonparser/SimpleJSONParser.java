
package simplejsonparser;

public class SimpleJSONParser {

    public static void main(String[] args) {
        JSONString jsonString = new JSONString();
        System.out.println(jsonString.parseFrom("\"A\"\""));
    }

}
