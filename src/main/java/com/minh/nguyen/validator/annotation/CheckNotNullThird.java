package com.minh.nguyen.validator.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Mr.Minh
 * @since 08/02/2018
 * Purpose:
 */
@Target({ METHOD })
@Retention(RUNTIME)
public @interface CheckNotNullThird {
}
