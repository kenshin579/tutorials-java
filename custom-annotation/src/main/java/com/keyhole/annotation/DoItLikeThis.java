package com.keyhole.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation created for doing it like this.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoItLikeThis {

    /**
     * @return - The description.
     */
    String description() default "";

    /**
     * @return - The action.
     */
    String action() default "";

    /**
     * @return - Should we be doing it like this.
     */
    boolean shouldDoItLikeThis() default false;

}