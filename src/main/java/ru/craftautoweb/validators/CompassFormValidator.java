package ru.craftautoweb.validators;

import org.springframework.stereotype.Component;
import ru.craftautoweb.entities.CompassEntity;

/**
 * Created by User on 27.11.2016.
 */
@Component
public class CompassFormValidator extends BaseEntityFormValidator {
    public CompassFormValidator() {
        typeForm = "compassForm";
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CompassEntity.class.equals(aClass);
    }
}
