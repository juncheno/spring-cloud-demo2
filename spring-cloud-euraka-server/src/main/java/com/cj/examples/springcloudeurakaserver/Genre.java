package com.cj.examples.springcloudeurakaserver;


import javax.inject.Qualifier;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@Target(value = {ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
public @interface Genre {

    Color color() default Color.TAN;
    public enum Color { RED, BLACK, TAN }
}
