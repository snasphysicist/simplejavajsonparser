
package main.simplejsonparser;

interface JSONElement {

    boolean atStartOf(String toParse);
    String parseFrom(String json);
    boolean success();

}
