package com.minh.nguyen.validator.annotation;

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
public @interface Format  {
    public static final String N_A = "N/A";

    enum FormatType {
    	NUMBER,
		TIME,
		DATE,
		EMAIL,
		PHONENUMBER
	}


	FormatType type();

	String displayFieldName() default N_A;

	String pattern() default N_A;
}
