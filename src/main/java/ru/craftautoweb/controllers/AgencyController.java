package ru.craftautoweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.craftautoweb.entities.AgencyEntity;
import ru.craftautoweb.entities.TypesAgencyEntity;
import ru.craftautoweb.utils.ResourceNotFoundException;
import ru.craftautoweb.validators.AgencyFormValidator;

@Controller
public class AgencyController extends BaseController {

    // Переменные для определения RequestMapping (должны быть final)
    private static final String C_TYPE_DESC = "agency";
    private static final String C_TYPE_URL = "Agencies";
    private static final String C_TYPE_JS = "Agency";

    // Остальное наследуем и устанавливаем из родителя через конструктор
    public AgencyController() {
        C_TYPE = "Заказчик";
        C_TYPE_OFF = "заказчика";
        C_TYPE_LIST = "Заказчики";
        C_TYPE_DESC_BASE = C_TYPE_DESC;
        C_TYPE_URL_BASE = C_TYPE_URL;
        C_TYPE_JS_BASE = C_TYPE_JS;
    }


    @Autowired
    AgencyFormValidator itemFormValidator;

    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        binder.setValidator(itemFormValidator);
    }


    protected void SetDefaultModel(Model model) {
        model.addAttribute("typeList", TypesAgencyEntity.GetMapTypesAgency());
    }

    @RequestMapping(value = "/Directories/" + C_TYPE_URL, method = RequestMethod.GET)
    public String List(Model model) {
        SetDefaultModel(model);
        model.addAttribute("Title", C_TYPE_LIST);
        model.addAttribute("dicType", C_TYPE_JS);
        return "dictionary/agencies";
    }

    @RequestMapping(value = "/Directories/Create" + C_TYPE_JS, method = RequestMethod.GET)
    public String Create(Model model) {
        AgencyEntity item = new AgencyEntity();
        ModelForCreateAction(model, item);
        return "dictionary/" + C_TYPE_DESC;
    }

    @RequestMapping(value = "/Directories/Edit" + C_TYPE_JS + "/{id}", method = RequestMethod.GET)
    public String Edit(@PathVariable int id, Model model) {
        AgencyEntity item = AgencyEntity.getById(id);
        if (item == null) {
            throw new ResourceNotFoundException();
        }

        ModelForEditAction(model, item);
        return "dictionary/" + C_TYPE_DESC;
    }

    @RequestMapping(value = "/Directories/Create" + C_TYPE_JS,
            method = RequestMethod.POST)
    public String CreateAction(
            @ModelAttribute(C_TYPE_DESC + "Form") @Validated AgencyEntity item,
            BindingResult result, Model model,
            final RedirectAttributes redirectAttributes) {
        return SaveOrUpdate(null, item, result, model, redirectAttributes);
    }

    @RequestMapping(value = "/Directories/Edit" + C_TYPE_JS + "/{id}",
            method = RequestMethod.POST)
    public String EditAction(
            @PathVariable Integer id,
            @ModelAttribute(C_TYPE_DESC + "Form") @Validated AgencyEntity item,
            BindingResult result, Model model,
            final RedirectAttributes redirectAttributes) {
        return SaveOrUpdate(id, item, result, model, redirectAttributes);
    }
}
