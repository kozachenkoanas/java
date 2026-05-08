/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gc;

/**
 *
 * @author Виолетта
 */


import java.util.ArrayList;
import java.util.List;

public class Gc2 {
    public static void main(String[] args) throws Exception {
        System.out.println("НАГЛЯДНАЯ РАБОТА GC\n");
        
        Runtime runtime = Runtime.getRuntime();
        
        for (int experiment = 1; experiment <= 3; experiment++) {
            System.out.println("\n Эксперимент " + experiment );
            
            // Создаем временный список (внутри блока)
            List<byte[]> tempList = new ArrayList<>();
            
            // Заполняем список 10 объектами по 13 МБ
            for (int i = 1; i <= 10; i++) {
                tempList.add(new byte[13 * 1024 * 1024]);
                System.out.println("   Добавлен объект " + i + " (13 МБ)");
               
            }
            
            long used = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
            System.out.println("   Занято памяти: " + used + " МБ");
            
           
            //все 10 объектов - мусор
        }
        
        System.out.println("\nВсе временные списки вышли из области видимости");
     
        
        Thread.sleep(2000);
        
        // Проверяем память ДО вызова GC
        long beforeGC = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
        System.out.println("Память ДО вызова GC: " + beforeGC + " МБ");
        
        System.out.println("\nВызываем GC");
        System.gc();
        
        Thread.sleep(1000);
        
        // Проверяем память ПОСЛЕ GC
        long afterGC = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
        System.out.println("Память ПОСЛЕ GC: " + afterGC + " МБ");
        System.out.println("ОСВОБОЖДЕНО: " + (beforeGC - afterGC) + " МБ");
    }
    
}
