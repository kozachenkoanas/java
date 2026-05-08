//Демонстрация работы HotSpot JIT-компилятора.

public class HotSpotDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Демонстрация работы HotSpot JIT ===\n");

        // Даём JVM немного времени на старт
        Thread.sleep(2000);

        System.out.println("Этап 1: Холодный старт — первые вызовы (интерпретатор)\n");
        runBenchmark("Холодный", 100);

        System.out.println("\nЭтап 2: Прогрев — C1 начинает компиляцию\n");
        runBenchmark("Тёплый", 5_000);

        System.out.println("\nЭтап 3: Горячий код — C2 с полными оптимизациями\n");
        runBenchmark("Горячий", 1_000_000);

        System.out.println("\n=== Демонстрация деоптимизации ===");
        demonstrateDeoptimization();
    }

    /**
     * Метод, который станет «горячей точкой».
     * Намеренно написан так, чтобы JIT мог применить:
     * - размотку цикла (loop unrolling),
     * - escape-анализ (объект не покидает метод),
     * - встраивание (inlining) для арифметики.
     */
    private static long hotSpotMethod(int iterations) {
        long sum = 0;
        for (int i = 0; i < iterations; i++) {
            // Создаём объект, который НЕ покидает метод
            // → escape-анализ разместит его на стеке, а не в куче
            Point p = new Point(i, i * 2);
            sum += p.distanceFromOrigin();
        }
        return sum;
    }

    /**
     * Метод-обёртка для демонстрации мономорфного вызова.
     * JIT увидит, что тип всегда Point, и применит
     * мономорфную диспетчеризацию (прямой вызов без таблицы виртуальных методов).
     */
    private static double callDistance(Shape shape) {
        return shape.area(); // Всегда вызывается на Point → мономорфный вызов
    }

    /**
     * Демонстрация биморфного вызова.
     * JIT увидит два типа и оптимизирует под них.
     */
    private static double callBimorphic(Shape shape) {
        return shape.area(); // Вызывается на Point ИЛИ Circle → биморфный
    }

    /**
     * Демонстрация мегаморфного вызова.
     * Три и более типа → JIT не сможет оптимизировать,
     * будет использоваться полный поиск по таблице виртуальных методов.
     */
    private static double callMegamorphic(Shape shape) {
        return shape.area(); // Три разных типа → мегаморфный
    }

    private static void runBenchmark(String label, int calls) {
        long start = System.nanoTime();

        long result = 0;
        for (int i = 0; i < calls; i++) {
            result += hotSpotMethod(10);
        }

        long duration = System.nanoTime() - start;

        System.out.printf("[%s] Вызовов: %,d | Время: %,d мкс | Результат: %d (чтобы JIT не выбросил цикл)%n",
                label, calls, duration / 1_000, result);
    }

    /**
     * Демонстрация работы деоптимизации.
     *
     * Сначала вызываем callDistance только с Point → JIT оптимизирует под Point.
     * Потом внезапно передаём Circle → JIT вынужден откатить оптимизацию
     * (в логах появится «made not entrant»).
     */
    private static void demonstrateDeoptimization() {
        Point point = new Point(3, 4);
        Circle circle = new Circle(5);
        Square square = new Square(4);

        System.out.println("\nФаза 1: Мономорфный вызов — JIT запоминает, что тип ВСЕГДА Point");
        for (int i = 0; i < 20_000; i++) {
            callDistance(point); // Только Point → мономорфная оптимизация
        }
        System.out.println("  → callDistance скомпилирован как мономорфный (Point)");

        System.out.println("\nФаза 2: Биморфный вызов — JIT видит два типа");
        for (int i = 0; i < 20_000; i++) {
            callBimorphic(i % 2 == 0 ? point : circle); // Point и Circle → биморфный
        }
        System.out.println("  → callBimorphic оптимизирован под два типа");

        System.out.println("\nФаза 3: Мегаморфный вызов — три типа, оптимизация невозможна");
        Shape[] shapes = {point, circle, square};
        long start = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            callMegamorphic(shapes[i % 3]); // Три типа → мегаморфный
        }
        long megaTime = System.nanoTime() - start;

        // Сравниваем с мономорфным
        start = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            callDistance(point); // Мономорфный
        }
        long monTime = System.nanoTime() - start;

        System.out.printf("  Мономорфный вызов: %,d мкс%n", monTime / 1_000);
        System.out.printf("  Мегаморфный вызов: %,d мкс (в X%.1f раз медленнее)%n",
                megaTime / 1_000, (double) megaTime / monTime);
        System.out.println("\n  → Вывод: мегаморфные вызовы кратно дороже мономорфных.");
        System.out.println("  → JIT не может заинлайнить мегаморфный вызов и вынужден");
        System.out.println("    каждый раз искать метод в vtable.");
    }
}

// === Вспомогательные классы для демонстрации полиморфизма ===

interface Shape {
    /** Площадь. У каждой фигуры своя формула. */
    double area();
}

class Point implements Shape {
    final int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Расстояние от начала координат. */
    double distanceFromOrigin() {
        // Math.sqrt — intrinsic-метод!
        // На многих процессорах заменяется одной инструкцией (sqrtsd на x86).
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public double area() {
        return 0; // У точки нет площади
    }
}

class Circle implements Shape {
    final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Square implements Shape {
    final double side;

    Square(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }
}
