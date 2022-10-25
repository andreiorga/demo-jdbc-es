package ro.esolutions.query;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Where {

    String value();

    boolean addParam() default true;

    boolean mandatory() default false;

}
