package ru.craftautoweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.craftautoweb.entities.TypesCashEntity;
import ru.craftautoweb.entities.VOperatingStatementEntity;
import ru.craftautoweb.models.FullReportModel;
import ru.craftautoweb.utils.CustomTimestampEditor;
import ru.craftautoweb.utils.ResourceNotFoundException;
import ru.craftautoweb.validators.FullReportFormValidator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 04.12.2016.
 */
@Controller
public class FullReportController extends BaseController {
    @Autowired
    FullReportFormValidator itemFormValidator;

    //Set a form validator
    @InitBinder("fullreport")
    protected void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Timestamp.class,
                new CustomTimestampEditor(false));
        binder.setValidator(itemFormValidator);
    }

    @RequestMapping(value = "/Bills/FullReport", method = RequestMethod.GET)
    public String FullReport(Model model) {
        FullReportModel item = new FullReportModel();
        if (item == null) {
            throw new ResourceNotFoundException();
        }
        ModelForFullReport(model, item);

        return "bills/fullreport";
    }

    @RequestMapping(value = "/Bills/FullReport", method = RequestMethod.POST)
    public String FullReportAction(@ModelAttribute("fullreport") @Validated FullReportModel item,
                                   BindingResult result, Model model,
                                   final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            ModelForFullReport(model, item);
            return "bills/fullreport";
        } else {
            ModelForFullReportPrint(model, item);
            return "bills/fullreportprint";
        }
    }

    private void ModelForFullReport(Model model, FullReportModel item) {
        SetDefaultModel(model);
        model.addAttribute("fullreport", item);
    }

    private void ModelForFullReportPrint(Model model, FullReportModel item) {
        SetDefaultModel(model);
        model.addAttribute("fullreport", item);
        model.addAttribute("nowDate", new Date());
        List<VOperatingStatementEntity> list = VOperatingStatementEntity.GetListFullReport(
                item.getStartDate(),
                item.getEndDate(),
                item.getIdTypeCash());

        model.addAttribute("fullreportdetails", list);
        model.addAttribute("total", VOperatingStatementEntity.GetTotal(list));
    }

    @Override
    protected void SetDefaultModel(Model model) {
        model.addAttribute("Title", "Итоговый отчет");
        model.addAttribute("typesCash", TypesCashEntity.GetMapTypesCash());
    }
}
