package ru.craftautoweb.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.craftautoweb.models.*;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping(method = RequestMethod.GET, value={"", "/", "/Home"})
public class HomeController extends BaseController {
    @RequestMapping(method = RequestMethod.GET, value={"", "/", "/Home"})
    public ModelAndView Home(Model model) {
        model.addAttribute("Title", "Главная страница");
        String user = "";
        String MainActionTitle, MainActionURL;
        if (isAuthenticated()){  // есть авторизация
            MainActionTitle = "Выход";
            MainActionURL = "Logout";
        }
        else {
            MainActionTitle = "Войти";
            MainActionURL = "Login";
        }
        model.addAttribute("MainActionTitle", MainActionTitle);
        model.addAttribute("MainActionURL", MainActionURL);
        return new ModelAndView("home/home");
    }

    @RequestMapping(method = RequestMethod.GET, value={"/About"})
    public String About(Model model) {
        model.addAttribute("Title", "О программе");
        return "home/about";
    }
    protected void SetDefaultModel(Model model) {
    }
}
