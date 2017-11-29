package ru.craftautoweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.craftautoweb.entities.BillDriversEntity;
import ru.craftautoweb.entities.BillsEntity;
import ru.craftautoweb.utils.CustomTimestampEditor;
import ru.craftautoweb.utils.ResourceNotFoundException;
import ru.craftautoweb.validators.BillDetailsValidator;

import java.sql.Timestamp;

/**
 * Created by User on 03.12.2016.
 */
@Controller
public class BillDetailsController extends BaseController{
    @Autowired
    BillDetailsValidator itemFormValidator;

    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Timestamp.class,
                new CustomTimestampEditor(false));
        binder.setValidator(itemFormValidator);
    }

    @RequestMapping(value = "/Bills/Details/{id}", method = RequestMethod.GET)
    public String Details(@PathVariable int id, Model model) {
        BillsEntity item = BillsEntity.getById(id);
        if (item == null) {
            throw new ResourceNotFoundException();
        }
        ModelForBillDetails(model, item);

        return "bills/details";
    }

    @RequestMapping(value = "/Bills/Details/{id}", method = RequestMethod.POST)
    public String DetailsAction(
            @PathVariable Integer id,
            @ModelAttribute("billdetailsmodel") @Validated BillsEntity item,
            BindingResult result, Model model,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelForBillDetails(model, item);
            return "bills/details";
        } else {
            redirectAttributes.addFlashAttribute("newid", item.Save());
            return "redirect:/Bills/List";
        }
    }

    private void ModelForBillDetails(Model model, BillsEntity item) {
        SetDefaultModel(model);
        model.addAttribute("actionUrl", "Bills/Details/" + item.getId());
        model.addAttribute("id", item.getId());
        model.addAttribute("billdetailsmodel", item);
        model.addAttribute("billdetails", BillsEntity.getById(item.getId()));
    }

    @RequestMapping(value = "/Bills/DetailsDriver/{id}", method = RequestMethod.GET)
    public String DetailsDriver(@PathVariable int id, Model model) {
        BillDriversEntity item = BillDriversEntity.getById(id);
        if (item == null) {
            throw new ResourceNotFoundException();
        }
        ModelForBillDriverDetails(model, item);

        return "bills/detailsdriver";
    }

    @RequestMapping(value = "/Bills/DetailsDriver/{id}", method = RequestMethod.POST)
    public String DetailsDriverAction(
            @PathVariable Integer id,
            @ModelAttribute("billdetailsmodel") @Validated BillDriversEntity item,
            BindingResult result, Model model,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelForBillDriverDetails(model, item);
            return "bills/detailsdriver";
        } else {
            redirectAttributes.addFlashAttribute("newid", item.Save());
            return "redirect:/Bills/ListDriver";
        }
    }

    private void ModelForBillDriverDetails(Model model, BillDriversEntity item) {
        SetDefaultModel(model);
        model.addAttribute("actionUrl", "Bills/DetailsDriver/" + item.getId());
        model.addAttribute("id", item.getId());
        model.addAttribute("billdetailsmodel", item);
        model.addAttribute("billdetails", BillDriversEntity.getById(item.getId()));
    }

    @Override
    protected void SetDefaultModel(Model model) {
        model.addAttribute("Title", "Просмотр счета");
    }
}
