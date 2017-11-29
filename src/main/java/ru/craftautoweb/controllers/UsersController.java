package ru.craftautoweb.controllers;

import org.hibernate.Session;
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
import ru.craftautoweb.entities.RolesEntity;
import ru.craftautoweb.entities.UserEntity;
import ru.craftautoweb.utils.HibernateUtil;
import ru.craftautoweb.utils.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import ru.craftautoweb.validators.UserFormValidator;

/**
 * Created by User on 18.11.2016.
 */
@Controller
public class UsersController extends BaseController {
    // Переменные для определения RequestMapping (должны быть final)
    private static final String C_TYPE_DESC = "user";
    private static final String C_TYPE_URL = "Users";
    private static final String C_TYPE_JS = "User";

    // Остальное наследуем и устанавливаем из родителя через конструктор
    public UsersController() {
        C_TYPE = "Пользователь";
        C_TYPE_OFF = "пользователя";
        C_TYPE_LIST = "Пользователи";
        C_TYPE_URL_BASE = C_TYPE_URL;
        C_TYPE_JS_BASE = C_TYPE_JS;
        C_TYPE_DESC_BASE = C_TYPE_DESC;
    }

    @Autowired
    UserFormValidator itemFormValidator;

    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        binder.setValidator(itemFormValidator);
    }


    @Override
    protected void SetDefaultModel(Model model) {

        model.addAttribute("roleList", RolesEntity.GetMapRoles());
    }

    @RequestMapping(value = "/Directories/" + C_TYPE_URL, method = RequestMethod.GET)
    public String List(Model model) {
        SetDefaultModel(model);
        model.addAttribute("Title", C_TYPE_LIST);
        model.addAttribute("dicType", C_TYPE_JS);
        return "base/generalDictionary";
    }

    @RequestMapping(value = "/Directories/Create" + C_TYPE_JS, method = RequestMethod.GET)
    public String Create(Model model) {
        UserEntity item = new UserEntity();
        ModelForCreateAction(model, item);
        return "dictionary/" + C_TYPE_DESC;
    }

    @RequestMapping(value = "/Directories/Edit" + C_TYPE_JS + "/{id}", method = RequestMethod.GET)
    public String Edit(@PathVariable int id, Model model) {
        UserEntity item = UserEntity.getById(id);
        if (item == null) {
            throw new ResourceNotFoundException();
        }

        ModelForEditAction(model, item);
        return "dictionary/" + C_TYPE_DESC;
    }

    @RequestMapping(value = "/Directories/Create" + C_TYPE_JS,
            method=RequestMethod.POST)
    public String CreateAction(
            @ModelAttribute(C_TYPE_DESC + "Form") @Validated UserEntity item,
            BindingResult result, Model model,
            final RedirectAttributes redirectAttributes) {
        return SaveOrUpdate(null, item, result, model, redirectAttributes);
    }

    @RequestMapping(value = "/Directories/Edit" + C_TYPE_JS + "/{id}",
            method = RequestMethod.POST)
    public String EditAction(
            @PathVariable Integer id,
            @ModelAttribute(C_TYPE_DESC + "Form") @Validated UserEntity item,
            BindingResult result, Model model,
            final RedirectAttributes redirectAttributes) {
        return SaveOrUpdate(id, item, result, model, redirectAttributes);
    }
/*
    private String SaveOrUpdate(Integer id, UserEntity item,
                                BindingResult result, Model model,
                                final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute(C_TYPE_DESC + "Form", item);
            if (id == null) {
                ModelForCreateAction(model, item);
            } else {
                ModelForEditAction(model, item);
            }
            return "dictionary/" + C_TYPE_DESC;
        } else {
            redirectAttributes.addFlashAttribute("newid", item.Save());
            return "redirect:/Directories/" + C_TYPE_URL;
        }
    }

    private void ModelForCreateAction(Model model, UserEntity item) {
        SetDefaultModel(model);
        model.addAttribute("Title", "Создание " + C_TYPE_OFF);
        model.addAttribute("TypeOp", "Создать");
        model.addAttribute("dicType", C_TYPE);
        model.addAttribute("actionUrl", "Create" + C_TYPE_JS);
        model.addAttribute(C_TYPE_DESC + "Form", item);
    }

    private void ModelForEditAction(Model model, UserEntity item) {
        SetDefaultModel(model);
        model.addAttribute("Title", "Редактирование " + C_TYPE_OFF);
        model.addAttribute("TypeOp", "Редактирование");
        model.addAttribute("dicType", C_TYPE);
        model.addAttribute("actionUrl", "Edit" + C_TYPE_JS + "/" + item.getId());
        model.addAttribute(C_TYPE_DESC + "Form", item);
    }
    */
}
