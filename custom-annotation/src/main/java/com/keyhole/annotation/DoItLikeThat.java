package com.keyhole.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation created for doing it like that
 * instead of like this.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoItLikeThat {

    /**
     * @return - Should we be doing it like that.
     */
    boolean shouldDoItLikeThat();

    /**
     * @return - List of user roles that can do it like that.
     */
    String[] roles() default {};

}
