package ru.craftautoweb.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.craftautoweb.entities.AgencyEntity;
import ru.craftautoweb.entities.DocumentsEntity;

/**
 * Created by Администратор on 28.11.2016.
 */
@Component
public class OrderFormValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return DocumentsEntity.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        DocumentsEntity item = (DocumentsEntity)o;
        if(item.getId() != null){
            ValidationUtils.rejectIfEmpty(errors, "number", "NotEmpty.orderForm.number");
        }
        ValidationUtils.rejectIfEmpty(errors, "date", "NotEmpty.orderForm.date");
        ValidationUtils.rejectIfEmpty(errors, "time", "NotEmpty.orderForm.time");
        ValidationUtils.rejectIfEmpty(errors, "customer", "NotEmpty.orderForm.customer");
        ValidationUtils.rejectIfEmpty(errors, "nums", "NotEmpty.orderForm.nums");
        ValidationUtils.rejectIfEmpty(errors, "typeCash", "NotEmpty.orderForm.typeCash");
        ValidationUtils.rejectIfEmpty(errors, "telephones", "NotEmpty.orderForm.telephones");
        ValidationUtils.rejectIfEmpty(errors, "agency", "NotEmpty.orderForm.agency");
        ValidationUtils.rejectIfEmpty(errors, "route", "NotEmpty.orderForm.route");
        ValidationUtils.rejectIfEmpty(errors, "table", "NotEmpty.orderForm.table");
        ValidationUtils.rejectIfEmpty(errors, "dateRelease", "NotEmpty.orderForm.daterelease");
        ValidationUtils.rejectIfEmpty(errors, "compass", "NotEmpty.orderForm.compass");

        // Проверяем тип оплаты
        Integer idAgency = item.getAgency();
        Integer idTypeCash = item.getTypeCash();
        if (!AgencyEntity.isValidTypeCash(idAgency, idTypeCash)) {
            errors.rejectValue("typeCash", "Wrong.orderForm.typeCash");
        }
    }
}
