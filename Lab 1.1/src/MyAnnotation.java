import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    int a() default  1;
    int b() default  2;
}
