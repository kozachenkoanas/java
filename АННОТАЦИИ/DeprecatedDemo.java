package annotationexamples.builtin;

public class DeprecatedDemo {
    
    @Deprecated
    public static void oldMethod() {
        System.out.println("This method is deprecated");
    }
    
    @Deprecated(since = "2.0", forRemoval = true)
    public static void veryOldMethod() {
        System.out.println("Will be removed in future");
    }
    
    public static void demo() {
        System.out.println("@Deprecated");
        System.out.println("(Компилятор покажет предупреждения)");
        oldMethod();
        veryOldMethod();
        System.out.println();
    }
}