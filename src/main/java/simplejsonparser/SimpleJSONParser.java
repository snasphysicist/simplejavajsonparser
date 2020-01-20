
package simplejsonparser;

public class SimpleJSONParser {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.parseFrom("{\"key\":[10[,\"string\",false]}");
        System.out.println(jsonObject.success());
    }

}
