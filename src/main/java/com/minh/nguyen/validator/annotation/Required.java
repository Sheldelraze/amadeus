package com.minh.nguyen.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Mr.Minh
 * @since 04/01/2018
 * Purpose:
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Required {
	String displayFieldName() default "N/A";
}
