package ru.craftautoweb.validators;


import org.springframework.stereotype.Component;
import ru.craftautoweb.entities.RouteEntity;

/**
 * Created by User on 27.11.2016.
 */
@Component
public class RouteFormValidator extends BaseEntityFormValidator {
    public RouteFormValidator() {
        typeForm = "routeForm";
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RouteEntity.class.equals(aClass);
    }
}
