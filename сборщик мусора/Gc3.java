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

public class Gc3 {
    public static void main(String[] args) throws Exception {
        System.out.println("четные сохраняются, нечетные удаляются \n");
        
        // Список для хранения НЕЧЕТНЫХ объектов (они останутся)
        List<AutoObject> aliveObjects = new ArrayList<>();
        
        System.out.println("Создаем 100 объектов\n");
        
        for (int i = 1; i <= 100; i++) {
            AutoObject obj = new AutoObject(i);
            
            // Четные сохраняем в список 
            if (i % 2 == 0) {
                aliveObjects.add(obj);
                
            }
            
          
        }
        
        System.out.println("\n сохранено четных объектов: " + aliveObjects.size());
        System.out.println(" Нечетные объекты стали мусором (на них нет ссылок)\n");
        
        System.out.println("Вызываем GC");
        System.gc();
        
        Thread.sleep(2000);
        
        System.out.println("\nРЕЗУЛЬТАТ ");
        System.out.println("Четные объекты (живые):");
        for (AutoObject obj : aliveObjects) {
            System.out.println(" Объект " + obj.getId() + "остался");
        }
        
    }
}

class AutoObject {
    
    private int id;
    private int[] data = new int[5000]; // для нагрузки на память
    
    public AutoObject(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
   
    protected void finalize() {
        System.out.println("нечетный объект " + id + " удалился");
    }
}
