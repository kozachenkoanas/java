package annotationexamples.jpa;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.time.LocalDateTime;

// JPA аннотации
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface GeneratedValue { 
    String strategy() default "AUTO";
}

// Hibernate специфичные аннотации
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface CreationTimestamp { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface UpdateTimestamp { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Formula { 
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NaturalId { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface ColumnDefault { 
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface SQLDelete { 
    String sql();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Where { 
    String clause();
}

@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
class HibernateUser {
    
    @Id
    @GeneratedValue(strategy = "IDENTITY")
    private Long id;
    
    @NaturalId
    private String email;
    
    @Formula("(SELECT COUNT(*) FROM orders WHERE user_id = id)")
    private int orderCount;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @ColumnDefault("false")
    private boolean deleted;
    
    private String name;
    
    // Для демонстрации
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public int getOrderCount() { return orderCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public boolean isDeleted() { return deleted; }
    public String getName() { return name; }
}

public class HibernateAnnotationsDemo {
    public static void demo() {
        System.out.println("HIBERNATE SPECIFIC AННОТАЦИИ");
        
        Class<HibernateUser> clazz = HibernateUser.class;
        
        // Class-level аннотации
        System.out.println("Аннотации класса:");
        if (clazz.isAnnotationPresent(SQLDelete.class)) {
            SQLDelete sd = clazz.getAnnotation(SQLDelete.class);
            System.out.println("  @SQLDelete(sql=\"" + sd.sql() + "\") - мягкое удаление");
        }
        if (clazz.isAnnotationPresent(Where.class)) {
            Where w = clazz.getAnnotation(Where.class);
            System.out.println("  @Where(clause=\"" + w.clause() + "\") - фильтр записей");
        }
        
        // Field-level аннотации
        System.out.println("\nАннотации полей:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.print("  " + field.getName() + ": ");
            
            if (field.isAnnotationPresent(Id.class)) {
                System.out.print("@Id ");
            }
            if (field.isAnnotationPresent(GeneratedValue.class)) {
                GeneratedValue gv = field.getAnnotation(GeneratedValue.class);
                System.out.print("@GeneratedValue(strategy=" + gv.strategy() + ") ");
            }
            if (field.isAnnotationPresent(NaturalId.class)) {
                System.out.print("@NaturalId (естественный ключ) ");
            }
            if (field.isAnnotationPresent(Formula.class)) {
                Formula f = field.getAnnotation(Formula.class);
                System.out.print("@Formula(\"" + f.value() + "\") (вычисляемое поле) ");
            }
            if (field.isAnnotationPresent(CreationTimestamp.class)) {
                System.out.print("@CreationTimestamp (авто-дата создания) ");
            }
            if (field.isAnnotationPresent(UpdateTimestamp.class)) {
                System.out.print("@UpdateTimestamp (авто-дата обновления) ");
            }
            if (field.isAnnotationPresent(ColumnDefault.class)) {
                ColumnDefault cd = field.getAnnotation(ColumnDefault.class);
                System.out.print("@ColumnDefault(\"" + cd.value() + "\") (значение по умолчанию) ");
            }
            System.out.println();
        }
        
        System.out.println("\nЧто делают Hibernate аннотации:");
        System.out.println("  @SQLDelete - перехватывает DELETE и делает UPDATE");
        System.out.println("  @Where - автоматически добавляет условие во все SELECT");
        System.out.println("  @NaturalId - альтернативный уникальный ключ");
        System.out.println("  @Formula - SQL выражение для виртуального поля");
        System.out.println("  @CreationTimestamp - автоматическая установка времени создания");
        System.out.println("  @UpdateTimestamp - автоматическое обновление времени");
        System.out.println("  @ColumnDefault - значение по умолчанию в БД\n");
    }
}