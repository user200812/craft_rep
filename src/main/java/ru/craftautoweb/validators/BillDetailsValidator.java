package ru.craftautoweb.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.craftautoweb.entities.BillDriversEntity;
import ru.craftautoweb.entities.BillsEntity;

/**
 * Created by User on 04.12.2016.
 */
@Component
public class BillDetailsValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return BillsEntity.class.equals(aClass)
                || BillDriversEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "number", "NotEmpty.billDetailsForm.number");
        ValidationUtils.rejectIfEmpty(errors, "createDate", "NotEmpty.billDetailsForm.createDate");
    }
}
