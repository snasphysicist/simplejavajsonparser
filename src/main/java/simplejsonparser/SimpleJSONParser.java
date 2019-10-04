
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
        JSONArray parsed = (JSONArray) parser.getObject().get("test");
        JSONElement[] values = parsed.getElements();
        System.out.println(values.length);

        System.out.println("OBJECT -> ARRAY -> OBJECT");
        json = "{\"test\":[{\"test2\":12},{\"test3\":\"string\"}]}";
        parser = new JSONObject();
        json = parser.parseFrom(json);
        System.out.println(json);
        System.out.println(parser.success());
        parsed = (JSONArray) parser.getObject().get("test");
        values = parsed.getElements();
        System.out.println(values.length);
        for (JSONElement value : values) {
            System.out.println(value instanceof JSONObject);
            System.out.println(((JSONObject) value).getObject().get("test2"));
            System.out.println(((JSONObject) value).getObject().get("test3"));
        }

    }

}
