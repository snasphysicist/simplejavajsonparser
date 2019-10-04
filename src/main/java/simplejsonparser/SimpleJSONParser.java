
package simplejsonparser;

public class SimpleJSONParser {

    public static void main(String[] args) {

        System.out.println("BOOLEAN TRUE");
        String json = "{\"test\":true}";
        JSONObject parser = new JSONObject();
        json = parser.parseFrom(json);
        System.out.println(json);
        System.out.println(parser.success());

        System.out.println("BOOLEAN FALSE");
        json = "{\"test\":false}";
        parser = new JSONObject();
        json = parser.parseFrom(json);
        System.out.println(json);
        System.out.println(parser.success());

        System.out.println("NULL");
        json = "{\"test\":null}";
        parser = new JSONObject();
        json = parser.parseFrom(json);
        System.out.println(json);
        System.out.println(parser.success());


        System.out.println("STRING");
        json = "{\"test\":\"test2\"}";
        parser = new JSONObject();
        json = parser.parseFrom(json);
        System.out.println(json);
        System.out.println(parser.success());

        System.out.println("DOUBLE");
        json = "{\"test\":1.23}";
        parser = new JSONObject();
        json = parser.parseFrom(json);
        System.out.println(json);
        System.out.println(parser.success());

        System.out.println("INT");
        json = "{\"test\":10}";
        parser = new JSONObject();
        json = parser.parseFrom(json);
        System.out.println(json);
        System.out.println(parser.success());

        System.out.println("OBJECT");
        json = "{\"test\":{\"test2\":true}}";
        parser = new JSONObject();
        json = parser.parseFrom(json);
        System.out.println(json);
        System.out.println(parser.success());

        System.out.println("ARRAY");
        json = "{\"test\":[1,2,3,4]}";
        parser = new JSONObject();
        json = parser.parseFrom(json);
        System.out.println(json);
        System.out.println(parser.success());
        System.out.println(parser.getObject().get("test"));

    }

}
