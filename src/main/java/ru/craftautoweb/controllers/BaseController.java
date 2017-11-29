package ru.craftautoweb.controllers;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.craftautoweb.entities.AutoEntity;
import ru.craftautoweb.entities.BaseEntity;

import java.util.Calendar;

public abstract class BaseController {
    protected String C_TYPE;
    protected String C_TYPE_OFF;
    protected String C_TYPE_LIST;
    protected String C_TYPE_DESC_BASE;
    protected String C_TYPE_URL_BASE;
    protected String C_TYPE_JS_BASE;

    @ModelAttribute
    public void addSecurityAttributes(ModelMap model){
        // Аттрибут для отображения текущего пользователя
        String loginUser;

        // Аттрибут для отображения текущего года
        int year = Calendar.getInstance().get(Calendar.YEAR);

        // Проверяем авторизацию
        if (isAuthenticated()){  // есть авторизация
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String user = auth.getName();
            loginUser = "<ul class=\"nav navbar-nav navbar-right\"><li><a>Здравствуйте, " + user + "!</a></li>" +
                    "<li><a href=\"Logout\" title=\"Выйти\"><span class=\"glyphicon glyphicon-log-out\"></span></a></li></ul>";
        }
        else {
            loginUser = "<ul class=\"nav navbar-nav navbar-right\">\n" +
                    "<li><a href=\"Login\" title=\"Выполнить вход\"><span class=\"glyphicon glyphicon-log-in\"></span></a></li>\n" +
                    "</ul>\n";
        }

        // Добавляем аттрибуты
        model.addAttribute("LoginMenu", loginUser);
        model.addAttribute("CurrentYear", year);
    }

    protected static  boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null
                && auth.isAuthenticated()
                && (auth instanceof AnonymousAuthenticationToken) == false;
    }

    protected String SaveOrUpdate(Integer id, BaseEntity item,
                                BindingResult result, Model model,
                                final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute(C_TYPE_DESC_BASE + "Form", item);
            if (id == null) {
                ModelForCreateAction(model, item);
            } else {
                ModelForEditAction(model, item);
            }
            return "dictionary/" + C_TYPE_DESC_BASE;
        } else {
            redirectAttributes.addFlashAttribute("newid", item.Save());
            return "redirect:/Directories/" + C_TYPE_URL_BASE;
        }
    }

    protected void ModelForCreateAction(Model model, BaseEntity item) {
        SetDefaultModel(model);
        model.addAttribute("Title", "Создание " + C_TYPE_OFF);
        model.addAttribute("TypeOp", "Создать");
        model.addAttribute("dicType", C_TYPE);
        model.addAttribute("actionUrl", "Create" + C_TYPE_JS_BASE);
        model.addAttribute(C_TYPE_DESC_BASE + "Form", item);
    }

    protected void ModelForEditAction(Model model, BaseEntity item) {
        SetDefaultModel(model);
        model.addAttribute("Title", "Редактирование " + C_TYPE_OFF);
        model.addAttribute("TypeOp", "Редактирование");
        model.addAttribute("dicType", C_TYPE);
        model.addAttribute("actionUrl", "Edit" + C_TYPE_JS_BASE + "/" + item.getId());
        model.addAttribute(C_TYPE_DESC_BASE + "Form", item);
    }
    protected abstract void SetDefaultModel(Model model);

}
