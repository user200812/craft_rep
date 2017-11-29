package ru.craftautoweb.validators;

import org.springframework.stereotype.Component;
import ru.craftautoweb.entities.DriverEntity;

/**
 * Created by User on 27.11.2016.
 */
@Component
public class DriverFormValidator extends BaseEntityFormValidator {
    public DriverFormValidator() {
        typeForm = "driverForm";
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return DriverEntity.class.equals(aClass);
    }
}
