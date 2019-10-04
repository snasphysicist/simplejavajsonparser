
package simplejsonparser;

public class SimpleJSONParser {

    public static void main(String[] args) {
        String json = "{\"test\":true}";
        JSONObject parser = new JSONObject();
        json = parser.parseFrom(json);
        System.out.println(json);
        System.out.println(parser.success());
    }

}
