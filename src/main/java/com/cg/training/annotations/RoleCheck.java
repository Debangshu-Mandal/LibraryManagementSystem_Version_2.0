package com.cg.training.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Custom annotation to specify the required role for accessing or performing actions on the annotated class.
 * Retention policy is RUNTIME, so the annotation is available at runtime for reflection-based processing.
 * Can only be applied to types (classes or interfaces).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RoleCheck {

    /**
     * Specifies the role required to access or use the annotated class.
     * Defaults to "Admin".
     * Returns: The required role as a String
     */
    String role() default "Admin";
}
