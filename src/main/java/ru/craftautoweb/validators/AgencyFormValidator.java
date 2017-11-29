package ru.craftautoweb.validators;

import org.springframework.stereotype.Component;
import ru.craftautoweb.entities.AgencyEntity;

/**
 * Created by User on 27.11.2016.
 */
@Component
public class AgencyFormValidator extends BaseEntityFormValidator {
    public AgencyFormValidator() {
        typeForm = "agencyForm";
    }

    public boolean supports(Class<?> aClass) {
        return AgencyEntity.class.equals(aClass);
    }
}
