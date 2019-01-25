package kr.jdm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * String 문자열을 주입하기 위해 선언하는 어노테이션입니다.
 * FIELD에만 선언가능하고 JVM이 어노테이션 정보를 참조합니다.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringInjector {
    String value() default "This is StringInjector.";
}