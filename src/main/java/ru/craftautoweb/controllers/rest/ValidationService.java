package ru.craftautoweb.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.craftautoweb.entities.AgencyEntity;
import ru.craftautoweb.entities.UserEntity;

/**
 * Created by User on 27.11.2016.
 */
@RestController
public class ValidationService {
    @RequestMapping(
            value = {"/Validation/IsUserExists"},
            method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity IsUserExists(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "id", defaultValue = "") Integer id) {
        UserEntity user = UserEntity.getByName(name);
        if(user != null
                &&  !user.getId().equals(id)) {
            return new ResponseEntity<Boolean>(
                    Boolean.valueOf(false),
                    HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(
                Boolean.valueOf(true),
                HttpStatus.OK);
    }
    @RequestMapping(
            value = {"/Validation/isValidTypeCash"},
            method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity isValidTypeCash(
            @RequestParam(value = "agency", defaultValue = "") Integer idAgency,
            @RequestParam(value = "typeCash", defaultValue = "") Integer idTypeCash) {
        return new ResponseEntity<Boolean>(
                Boolean.valueOf(AgencyEntity.isValidTypeCash(idAgency, idTypeCash)),
                HttpStatus.OK);
    }
}
