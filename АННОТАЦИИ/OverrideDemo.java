package annotationexamples.builtin;

class Animal {
    public void makeSound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks: Woof! Woof!");
    }
}

public class OverrideDemo {
    public static void demo() {
        System.out.println("@Override");
        Dog dog = new Dog();
        dog.makeSound();
        System.out.println();
    }
}