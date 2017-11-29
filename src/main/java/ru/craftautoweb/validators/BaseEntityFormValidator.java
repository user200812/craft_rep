package ru.craftautoweb.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.craftautoweb.entities.BaseEntity;

/**
 * Created by User on 27.11.2016.
 */
public abstract class BaseEntityFormValidator implements Validator{
    protected String typeForm;


    public void validate(Object o, Errors errors) {
        BaseEntity item = (BaseEntity)o;
        ValidationUtils.rejectIfEmpty(errors, "name", "NotEmpty." + typeForm + ".name");
    }
}
