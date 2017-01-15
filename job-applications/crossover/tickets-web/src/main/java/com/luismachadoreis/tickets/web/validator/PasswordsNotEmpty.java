package com.luismachadoreis.tickets.web.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Luis Machado Reis
 */
@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordsNotEmptyValidator.class)
@Documented
public @interface PasswordsNotEmpty {

    String message() default "PasswordsNotEmpty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String triggerFieldName() default "";

    String passwordFieldName() default "";

    String passwordVerificationFieldName() default "";
}
