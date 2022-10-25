package ro.esolutions.query;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Having {

    String value();

    boolean addParam() default true;

    boolean mandatory() default false;

}
