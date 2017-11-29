package ru.craftautoweb.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.craftautoweb.entities.UserEntity;
import ru.craftautoweb.models.User;

@Component
public class UserFormValidator extends BaseEntityFormValidator {
    public UserFormValidator() {
        typeForm = "userForm";
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserEntity item = (UserEntity)o;

        super.validate(item, errors);
        ValidationUtils.rejectIfEmpty(errors, "password", "NotEmpty.userForm.password");

        if(item.getName().length() < 3 && item.getName().length() > 0) {
            errors.rejectValue("name", "Length.userForm.name");
        }
        // Проверяем, что если новый пользователь, то он не существует
        else {
            UserEntity user = UserEntity.getByName(item.getName());
            if(user != null
                    &&  !user.getId().equals(item.getId())) {
                errors.rejectValue("name", "Exists.userForm.name");
            }
        }
    }
}
