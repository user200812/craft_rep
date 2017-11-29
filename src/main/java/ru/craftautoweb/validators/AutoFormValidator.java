package ru.craftautoweb.validators;

import org.springframework.stereotype.Component;
import ru.craftautoweb.entities.AutoEntity;

/**
 * Created by User on 27.11.2016.
 */
@Component
public class AutoFormValidator extends BaseEntityFormValidator {
    public AutoFormValidator() {
        typeForm = "autoForm";
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AutoEntity.class.equals(aClass);
    }
}
