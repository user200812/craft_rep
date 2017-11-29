package ru.craftautoweb.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.craftautoweb.models.BaseCreateBillModel;
import ru.craftautoweb.models.CreateBillDriverModel;
import ru.craftautoweb.models.CreateBillModel;

/**
 * Created by Администратор on 02.12.2016.
 */
@Component
public class CreateBillFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateBillModel.class.equals(aClass)
                || CreateBillDriverModel.class.equals(aClass) ;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "startDate", "NotEmpty.createBillForm.startDate");
        ValidationUtils.rejectIfEmpty(errors, "endDate", "NotEmpty.createBillForm.endDate");
        ValidationUtils.rejectIfEmpty(errors, "typeCash", "NotEmpty.createBillForm.typeCash");
    }
}
