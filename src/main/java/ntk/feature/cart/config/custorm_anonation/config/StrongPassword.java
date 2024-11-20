package ntk.feature.cart.config.custorm_anonation.config;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ntk.feature.cart.config.custorm_anonation.logic.PasswordValidator;

import java.lang.annotation.*;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StrongPassword {
    String message() default "Password: you entered password is not strong enough";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
