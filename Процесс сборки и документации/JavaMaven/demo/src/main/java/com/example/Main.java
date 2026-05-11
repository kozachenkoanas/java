package com.example;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Главный класс программы.
 * Точка входа в приложение.
 */
public class Main {
    
    /**
     * Точка входа в программу.
     * 
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        PrintWriter fileOut = null;
        
        try {
            // Создаём папку logs, если её нет
            //Files.createDirectories(Paths.get("logs"));
            
            // Открываем файл для записи
            fileOut = new PrintWriter(new FileWriter("logs/result.log"));
            
            // Выводим результат на консоль И в файл
            printAndSave(fileOut, "=== Демонстрация ручной сборки Java ===");
            printAndSave(fileOut, "");
            
            Calculator calc = new Calculator();
            
            printAndSave(fileOut, "Математические операции:");
            printAndSave(fileOut, "10 + 5 = " + calc.add(10, 5));
            printAndSave(fileOut, "10 - 5 = " + calc.subtract(10, 5));
            printAndSave(fileOut, "10 * 5 = " + calc.multiply(10, 5));
            printAndSave(fileOut, "42 / 5 = " + calc.divide(42, 5));
            printAndSave(fileOut, "");
            
            printAndSave(fileOut, calc.getVersion());
            printAndSave(fileOut, "");
            
            // Информация о времени запуска
            printAndSave(fileOut, "--- Информация о запуске ---");
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            printAndSave(fileOut, "Время запуска: " + timestamp);
            printAndSave(fileOut, "Версия сборки: 1.0");
        
            
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            if (fileOut != null) {
                fileOut.close();
            }
        }
    }
    
    /**
     * Выводит текст на консоль и сохраняет в файл.
     * 
     * @param out объект для записи
     * @param text текст для вывода
     */
    private static void printAndSave(PrintWriter out, String text) {
        System.out.println(text);
        out.println(text);
    }
}