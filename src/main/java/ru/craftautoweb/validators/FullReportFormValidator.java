package ru.craftautoweb.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.craftautoweb.models.FullReportModel;

/**
 * Created by User on 04.12.2016.
 */
@Component
public class FullReportFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return FullReportModel.class.equals(aClass) ;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "startDate", "NotEmpty.fullReportForm.startDate");
        ValidationUtils.rejectIfEmpty(errors, "endDate", "NotEmpty.fullReportForm.endDate");
    }
}
