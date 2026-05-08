package annotationexamples.jpa;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.Date;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Entity { 
    String name() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Table { 
    String name() default "";
    String schema() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface GeneratedValue { 
    String strategy() default "AUTO";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column { 
    String name() default "";
    boolean nullable() default true;
    boolean unique() default false;
    int length() default 255;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Transient { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Enumerated { 
    String value() default "ORDINAL";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Temporal { 
    String value() default "TIMESTAMP";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Version { }

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface ManyToOne { 
    String fetch() default "EAGER";
    boolean optional() default true;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface OneToMany { 
    String mappedBy() default "";
    String cascade() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JoinColumn { 
    String name() default "";
    String referencedColumnName() default "";
}

@Entity(name = "User")
@Table(name = "users", schema = "public")
class UserEntity {
    
    @Id
    @GeneratedValue(strategy = "IDENTITY")
    private Long id;
    
    @Column(name = "full_name", nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Enumerated(value = "STRING")
    private UserStatus status;
    
    @Temporal(value = "TIMESTAMP")
    private Date createdAt;
    
    @Transient
    private String temporaryData;
    
    @Version
    private Integer version;
    
    @ManyToOne(fetch = "LAZY", optional = false)
    @JoinColumn(name = "role_id")
    private RoleEntity role;
    
    @OneToMany(mappedBy = "user", cascade = "ALL")
    private java.util.List<OrderEntity> orders;
}

enum UserStatus {
    ACTIVE, INACTIVE, BLOCKED
}

@Entity
class RoleEntity {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
}

@Entity
class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}

public class JpaAnnotationsDemo {
    public static void demo() {
        System.out.println("JPA АННОТАЦИИ");
        
        Class<UserEntity> clazz = UserEntity.class;
        
        // Entity и Table
        System.out.println("Класс: " + clazz.getSimpleName());
        if (clazz.isAnnotationPresent(Entity.class)) {
            Entity entity = clazz.getAnnotation(Entity.class);
            System.out.println("@Entity(name=\"" + entity.name() + "\")");
        }
        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getAnnotation(Table.class);
            System.out.println("@Table(name=\"" + table.name() + "\", schema=\"" + table.schema() + "\")");
        }
        
        // Поля
        System.out.println("\nПоля:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.print("  " + field.getName() + ": ");
            
            if (field.isAnnotationPresent(Id.class)) {
                System.out.print("@Id ");
            }
            if (field.isAnnotationPresent(GeneratedValue.class)) {
                GeneratedValue gv = field.getAnnotation(GeneratedValue.class);
                System.out.print("@GeneratedValue(strategy=" + gv.strategy() + ") ");
            }
            if (field.isAnnotationPresent(Column.class)) {
                Column col = field.getAnnotation(Column.class);
                System.out.print("@Column(");
                if (!col.name().isEmpty()) System.out.print("name=" + col.name() + ", ");
                System.out.print("nullable=" + col.nullable() + ", ");
                System.out.print("unique=" + col.unique() + ", ");
                System.out.print("length=" + col.length());
                System.out.print(") ");
            }
            if (field.isAnnotationPresent(Enumerated.class)) {
                Enumerated en = field.getAnnotation(Enumerated.class);
                System.out.print("@Enumerated(" + en.value() + ") ");
            }
            if (field.isAnnotationPresent(Temporal.class)) {
                Temporal temp = field.getAnnotation(Temporal.class);
                System.out.print("@Temporal(" + temp.value() + ") ");
            }
            if (field.isAnnotationPresent(Transient.class)) {
                System.out.print("@Transient ");
            }
            if (field.isAnnotationPresent(Version.class)) {
                System.out.print("@Version ");
            }
            if (field.isAnnotationPresent(ManyToOne.class)) {
                ManyToOne mto = field.getAnnotation(ManyToOne.class);
                System.out.print("@ManyToOne(fetch=" + mto.fetch() + ", optional=" + mto.optional() + ") ");
            }
            if (field.isAnnotationPresent(OneToMany.class)) {
                OneToMany otm = field.getAnnotation(OneToMany.class);
                System.out.print("@OneToMany(mappedBy=\"" + otm.mappedBy() + "\", cascade=" + otm.cascade() + ") ");
            }
            if (field.isAnnotationPresent(JoinColumn.class)) {
                JoinColumn jc = field.getAnnotation(JoinColumn.class);
                System.out.print("@JoinColumn(name=\"" + jc.name() + "\") ");
            }
            System.out.println();
        }
        System.out.println();
    }
}