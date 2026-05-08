package annotationexamples.builtin;

import java.util.ArrayList;
import java.util.List;

public class SuppressWarningsDemo {
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void suppressExample() {
        System.out.println("Предупреждения подавлены");
        List list = new ArrayList();
        list.add("hello");
        list.add("world");
        System.out.println("List: " + list);
    }
    
    public static void demo() {
        System.out.println("@SuppressWarnings");
        suppressExample();
        System.out.println();
    }
}