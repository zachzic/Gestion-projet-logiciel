package zacharie.gestion_projet_logiciel.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueProjectNameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueProjectName {
    String message() default "Le nom du projet doit être unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

