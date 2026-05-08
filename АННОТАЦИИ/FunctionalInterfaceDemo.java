package annotationexamples.builtin;

@FunctionalInterface
interface StringConverter {
    String convert(int number);
    
    default void log() {
        System.out.println("Logging from default method");
    }
}

public class FunctionalInterfaceDemo {
    public static void demo() {
        System.out.println("@FunctionalInterface with default method");
        
        StringConverter converter = num -> "Number: " + num;
        System.out.println(converter.convert(42));
        
        converter.log();
        
        System.out.println();
    }
}