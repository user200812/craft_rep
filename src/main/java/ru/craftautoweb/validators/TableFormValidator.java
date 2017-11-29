package ru.craftautoweb.validators;

import org.springframework.stereotype.Component;
import ru.craftautoweb.entities.TableEntity;

/**
 * Created by User on 27.11.2016.
 */
@Component
public class TableFormValidator extends BaseEntityFormValidator {
    public TableFormValidator() {
        typeForm = "tableForm";
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TableEntity.class.equals(aClass);
    }
}
