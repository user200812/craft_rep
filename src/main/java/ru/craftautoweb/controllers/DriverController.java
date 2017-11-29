package ru.craftautoweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.craftautoweb.entities.AutoEntity;
import ru.craftautoweb.entities.DriverEntity;
import ru.craftautoweb.utils.ResourceNotFoundException;
import ru.craftautoweb.validators.DriverFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DriverController extends BaseController {

    // Переменные для определения RequestMapping (должны быть final)
    private static final String C_TYPE_DESC = "driver";
    private static final String C_TYPE_URL = "Drivers";
    private static final String C_TYPE_JS = "Driver";

    // Остальное наследуем и устанавливаем из родителя через конструктор
    public DriverController() {
        C_TYPE = "Принципал";
        C_TYPE_OFF = "принципала";
        C_TYPE_LIST = "Принципалы";
        C_TYPE_DESC_BASE = C_TYPE_DESC;
        C_TYPE_URL_BASE = C_TYPE_URL;
        C_TYPE_JS_BASE = C_TYPE_JS;
    }


    @Autowired
    DriverFormValidator itemFormValidator;

    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        binder.setValidator(itemFormValidator);
    }


    protected void SetDefaultModel(Model model) {
    }

    @RequestMapping(value = "/Directories/" + C_TYPE_URL, method = RequestMethod.GET)
    public String List(Model model) {
        SetDefaultModel(model);
        model.addAttribute("Title", C_TYPE_LIST);
        model.addAttribute("dicType", C_TYPE_JS);
        return "dictionary/drivers";
    }

    @RequestMapping(value = "/Directories/Create" + C_TYPE_JS, method = RequestMethod.GET)
    public String Create(Model model) {
        DriverEntity item = new DriverEntity();
        ModelForCreateAction(model, item);
        return "dictionary/" + C_TYPE_DESC;
    }

    @RequestMapping(value = "/Directories/Edit" + C_TYPE_JS + "/{id}", method = RequestMethod.GET)
    public String Edit(@PathVariable int id, Model model) {
        DriverEntity item = DriverEntity.getById(id);
        if (item == null) {
            throw new ResourceNotFoundException();
        }

        ModelForEditAction(model, item);
        return "dictionary/" + C_TYPE_DESC;
    }

    @RequestMapping(value = "/Directories/Create" + C_TYPE_JS,
            method = RequestMethod.POST)
    public String CreateAction(
            @ModelAttribute(C_TYPE_DESC + "Form") @Validated DriverEntity item,
            BindingResult result, Model model,
            final RedirectAttributes redirectAttributes) {
        return SaveOrUpdate(null, item, result, model, redirectAttributes);
    }

    @RequestMapping(value = "/Directories/Edit" + C_TYPE_JS + "/{id}",
            method = RequestMethod.POST)
    public String EditAction(
            @PathVariable Integer id,
            @ModelAttribute(C_TYPE_DESC + "Form") @Validated DriverEntity item,
            BindingResult result, Model model,
            final RedirectAttributes redirectAttributes) {
        return SaveOrUpdate(id, item, result, model, redirectAttributes);
    }
}
