package javagradle2;

/**
 * Калькулятор для демонстрации процесса сборки и документации Java.
 * <p>
 * Этот класс предоставляет базовые арифметические операции
 * и показывает, как правильно документировать код с помощью JavaDoc.
 * </p>
 * 
 * @author Gregory
 * @version 1.0
 * @since 2026-04-08
 * @see Main
 */
public class Calculator {
    
    /**
     * Вычисляет сумму двух целых чисел.
     * 
     * @param a первое слагаемое
     * @param b второе слагаемое
     * @return сумма a + b
     * @throws IllegalArgumentException если сумма переполняет int (демонстрация)
     */
    public int add(int a, int b) {
        long result = (long)a + (long)b;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Переполнение int при сложении");
        }
        return (int)result;
    }
    
    /**
     * Вычисляет разность двух чисел.
     * 
     * @param a уменьшаемое
     * @param b вычитаемое
     * @return результат a - b
     */
    public int subtract(int a, int b) {
        return a - b;
    }
    
    /**
     * Вычисляет произведение двух чисел.
     * 
     * @param a первый множитель
     * @param b второй множитель
     * @return произведение a * b
     */
    public int multiply(int a, int b) {
        return a * b;
    }
    
    /**
     * Вычисляет целое от деления.
     * 
     * @param a делимое
     * @param b делитель
     * @return результат деления a / b
     * @throws ArithmeticException если b == 0
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль не допускается");
        }
        return a / b;
    }
    
    //Информация о версии
    public String getVersion() {
        return "Calculator v1.0 - Build: 2026-04-08";
    }
}