package zacharie.gestion_projet_logiciel.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProjectDates {
    String message() default "La date de début doit être antérieure à la date de fin";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

