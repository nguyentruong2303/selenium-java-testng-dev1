package javaTester;

public class Topic_06_String {


    public static void main(Topic_06_String[] args) {

    String url = "http://the-internet.herokuapp.com/basic_auth";

    String[] arrayString = url.split("//");

    String newURL = arrayString[0] + "admin" + ":" + "admin" + "@" + arrayString[1];
    System.out.println(newURL);


    }

}
