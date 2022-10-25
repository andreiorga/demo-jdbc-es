package ro.esolutions.query;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Filter {

    String select();

    String from();

    String where() default "";

    String groupBy() default "";

}
