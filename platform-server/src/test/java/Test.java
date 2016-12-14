import java.util.Iterator;

public class Test {

    public static void main(String[] args){
        Iterator<String> iter = (Iterator<String>) System.getProperties().propertyNames();
        Thread.currentThread().setName("Test");
        while(iter.hasNext()){
            String key = iter.next();
            System.out.println(key + " ===> " + System.getProperty(key));
        }
    }
}
