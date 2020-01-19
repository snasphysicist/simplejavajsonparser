
package simplejsonparser;

public class SimpleJSONParser {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.parseFrom("{\"key\":  [ \n\r  10\t \t, \" \r\n\t \"  \r\r\t  ,\n true  \t\r  ]}");
        JSONElement[] elements = ((JSONArray) jsonObject.getObject().get("key")).getElements();
        JSONNumber number = ((JSONNumber) elements[0]);
        System.out.println(number.castToInteger());
    }

}
