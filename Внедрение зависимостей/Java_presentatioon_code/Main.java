import anti_patterns.*;
import good.DependencyMaker;
import injection_types.InjectionDemo;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n==========ANTIPATERNS==========");
        ControlFreak.demo();
        ServiceLocator.demo();
        BastardInjection.demo();
        ConcreteDependency.demo();

        System.out.println("\n==========Pure DI==========");
        DependencyMaker.demo(); //,просто чтобы не писать здесь же compoition root - единственное место в приложении, где компоненты (классы) соеднияются вместе

        System.out.println("\n==========INJECTION TYPES==========");
        InjectionDemo.demo();
    }
}
