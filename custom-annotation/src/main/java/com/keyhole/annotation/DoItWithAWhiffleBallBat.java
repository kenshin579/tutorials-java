package com.keyhole.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When you can't do it like this or do it like that,
 * do it with a whiffle ball bat.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoItWithAWhiffleBallBat {

    /**
     * @return - Should we be doing it with a whiffle ball bat.
     */
    boolean shouldDoItWithAWhiffleBallBat() default false;

    /**
     * @return - Sweet, which type of whiffle ball bat?
     */
    WhiffleBallBat batType() default WhiffleBallBat.YELLOW_PLASTIC;
}