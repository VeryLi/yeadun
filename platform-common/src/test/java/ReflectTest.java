import java.lang.reflect.*;

public class ReflectTest {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Demo demo ;
        Class cla = null;
        Constructor[] constructor = null;
        try {
            cla = Class.forName("Demo");

        } catch (Exception e) {
            e.printStackTrace();
        }
        Class[] parameters = {String.class, String.class};
        try {
            constructor = cla.getDeclaredConstructors();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1. according to constructor, create a instance.
        Class[] para = {String.class, String.class};
        demo = (Demo) cla.getConstructor(para).newInstance("Id", "Name");
        demo.setInput("input");
        demo.execute();
        print(demo.getId() + " -> " + demo.getName() + " -> " + demo.getOutput());

        // 2 get Fields
        Field[] fields = cla.getDeclaredFields();
        for(Field f : fields){
            print( f.toGenericString() + " -> " + f.getGenericType().toString() + " -> " + f.getName());
        }

        // 3. get Method
        Method[] methods = cla.getMethods();
        Class[] partypes = {};
        Method m = cla.getDeclaredMethod("getId");
        m.invoke(demo);
    }

    private static void print(String msg){
        System.out.println(msg);
    }
}
