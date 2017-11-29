package ru.craftautoweb.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.craftautoweb.models.LoginUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Администратор on 17.11.2016.
 */
@Controller
public class LoginController extends BaseController {

    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/Login"})
    public String Login(Model model) {
        model.addAttribute("Title", "Вход пользователя");
        return "home/login";
    }

    /*
        @RequestMapping(method = RequestMethod.GET, value={"/Login/{error}"})
        public String LoginError(@PathVariable boolean error, Model model) {
            model.addAttribute("Title", "Вход пользователя");
            return "home/login";
        }
    */
    protected void SetDefaultModel(Model model) {
    }
}
