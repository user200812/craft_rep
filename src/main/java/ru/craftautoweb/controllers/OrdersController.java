package ru.craftautoweb.controllers;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.craftautoweb.entities.*;
import ru.craftautoweb.utils.CustomTimestampEditor;
import ru.craftautoweb.utils.HibernateUtil;
import ru.craftautoweb.utils.ResourceNotFoundException;
import ru.craftautoweb.validators.OrderFormValidator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
public class OrdersController extends BaseController {

    @Autowired
    OrderFormValidator itemFormValidator;

    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder, WebRequest request) {
        for (String param : request.getParameterMap().keySet()) {
            binder.registerCustomEditor(Timestamp.class, param,
                    new CustomTimestampEditor(param.equals("time")));
        }
        binder.setValidator(itemFormValidator);
    }

    @RequestMapping(
            value = {
                    "/Orders/Index",
                    "/Orders"
            }, method = RequestMethod.GET)
    public String ListOrders(Model model) {
        // Получаем объект сесси для работы с базой
        Session session = HibernateUtil.getSession();

        // получаем запрос на получение результатов
        List<ResultsEntity> list = session.createQuery("from ResultsEntity order by id").
                list();
        session.close();

        // Формируем аттрибуты
        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        model.addAttribute("Title", "Журнал заказов");
        model.addAttribute("startDate", new SimpleDateFormat("dd.MM.yyyy").format(startDate.getTime()));
        model.addAttribute("endDate", new SimpleDateFormat("dd.MM.yyyy").format(endDate.getTime()));
        model.addAttribute("results", list);
        //model.addAttribute("newid", "0");
        return "orders/list";
    }

    protected void SetDefaultModel(Model model) {

    }

    protected void SetDefaultModel(Model model, DocumentsEntity item) {
        model.addAttribute("agencies", AgencyEntity.GetMapAgencies(item.getAgency()));
        model.addAttribute("drivers", DriverEntity.GetMapDrivers(item.getDriver()));
        model.addAttribute("typesCash", TypesCashEntity.GetMapTypesCash());
    }

    @RequestMapping(value = "/Orders/Create", method = RequestMethod.GET)
    public String Create(Model model) {
        DocumentsEntity item = new DocumentsEntity();
        ModelForCreateAction(model, item);
        return "orders/edit";
    }

    @RequestMapping(value = "/Orders/Edit/{id}", method = RequestMethod.GET)
    public String Edit(@PathVariable int id, Model model) {
        DocumentsEntity item = DocumentsEntity.getById(id);
        if (item == null) {
            throw new ResourceNotFoundException();
        }

        ModelForEditAction(model, item);
        return "orders/edit";
    }

    @RequestMapping(value = "/Orders/Create",
            method = RequestMethod.POST)
    public String CreateAction(
            @ModelAttribute("orderForm") @Validated DocumentsEntity item,
            BindingResult result, Model model,
            final RedirectAttributes redirectAttributes) {
        return SaveOrUpdate(null, item, result, model, redirectAttributes);
    }

    @RequestMapping(value = "/Orders/Edit/{id}",
            method = RequestMethod.POST)
    public String EditAction(
            @PathVariable Integer id,
            @ModelAttribute("orderForm") @Validated DocumentsEntity item,
            BindingResult result, Model model,
            final RedirectAttributes redirectAttributes) {
        return SaveOrUpdate(id, item, result, model, redirectAttributes);
    }

    protected void ModelForCreateAction(Model model, DocumentsEntity item) {
        SetDefaultModel(model, item);
        model.addAttribute("Title", "Создание заказа");
        model.addAttribute("TypeOp", "Создать");
        model.addAttribute("actionUrl", "Create");
        model.addAttribute("orderForm", item);
    }

    protected void ModelForEditAction(Model model, DocumentsEntity item) {
        SetDefaultModel(model, item);
        model.addAttribute("Title", "Редактирование заказа");
        model.addAttribute("TypeOp", "Редактирование");
        model.addAttribute("actionUrl", "Edit/" + item.getId());
        model.addAttribute("result", item.Result());
        model.addAttribute("user", item.UserChanged());
        model.addAttribute("orderForm", item);
    }

    protected String SaveOrUpdate(Integer id, DocumentsEntity item,
                                  BindingResult result, Model model,
                                  final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("orderForm", item);
            if (id == null) {
                ModelForCreateAction(model, item);
            } else {
                ModelForEditAction(model, item);
            }
            return "orders/edit";
        } else {
            Integer newid = item.Save();
            redirectAttributes.addFlashAttribute("newid", newid);
            if (newid != null && id == null) {
                redirectAttributes.addFlashAttribute("message", "Заказ успешно сохранен.\nНомер заказа: " + item.getNumber());
            }
            return "redirect:/Orders";
        }
    }

}
