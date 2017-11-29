package ru.craftautoweb.controllers;

import org.hibernate.Session;
//import org.hibernate.procedure.ProcedureCall;
import javax.persistence.ParameterMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import ru.craftautoweb.entities.AgencyEntity;
import ru.craftautoweb.entities.DriverEntity;
import ru.craftautoweb.entities.TypesCashEntity;
import ru.craftautoweb.models.BaseCreateBillModel;
import ru.craftautoweb.models.CreateBillDriverModel;
import ru.craftautoweb.models.CreateBillModel;
import ru.craftautoweb.utils.HibernateUtil;
import ru.craftautoweb.validators.CreateBillFormValidator;

import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static ru.craftautoweb.entities.UserEntity.GetIdCurrentUser;


@Controller
public class BillsController extends BaseController {

    @Autowired
    CreateBillFormValidator itemFormValidator;

    //Set a form validator
    @InitBinder
    protected void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd.MM.yyyy"), true));
        binder.setValidator(itemFormValidator);
    }

    @RequestMapping(value = "/Bills/List", method = RequestMethod.GET)
    public String ListBills(Model model) {
        model.addAttribute("Title", "Журнал счетов");
        model.addAttribute("Type", "Bills");
        model.addAttribute("TypeCreate", "CreateBill");
        model.addAttribute("TableHeader", "Заказчик");
        model.addAttribute("newid", "0");
        return "bills/list";
    }

    @RequestMapping(value = "/Bills/ListDriver", method = RequestMethod.GET)
    public String ListDriverBills(Model model) {
        model.addAttribute("Title", "Журнал заказов водителю");
        model.addAttribute("Type", "BillDrivers");
        model.addAttribute("TypeCreate", "CreateBillDriver");
        model.addAttribute("TableHeader", "Принципал");
        model.addAttribute("newid", "0");
        return "bills/list";
    }

    @RequestMapping(value = "/Bills/CreateBill", method = RequestMethod.GET)
    public String CreateBill(Model model) {
        CreateBillModel item = new CreateBillModel();
        SetDefaultModelBill(model, item);
        return "bills/createbill";
    }


    @RequestMapping(value = "/Bills/CreateBillDriver", method = RequestMethod.GET)
    public String CreateBillDriver(Model model) {
        CreateBillDriverModel item = new CreateBillDriverModel();
        SetDefaultModelBillDriver(model, item);
        return "bills/createbilldriver";
    }

    @RequestMapping(value = "/Bills/CreateBill",
            method = RequestMethod.POST)
    public String CreateBill(
            @ModelAttribute("bill") @Validated CreateBillModel item,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            SetDefaultModelBill(model, item);
            return "bills/createbill";
        } else {
            GenerateBills(item);
            return "redirect:/Bills/List";
        }
    }

    @RequestMapping(value = "/Bills/CreateBillDriver",
            method = RequestMethod.POST)
    public String CreateBillDriver(
            @ModelAttribute("bill") @Validated CreateBillDriverModel item,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            SetDefaultModelBillDriver(model, item);
            return "bills/createbilldriver";
        } else {
            GenerateBills(item);
            return "redirect:/Bills/ListDriver";
        }
    }

    private void GenerateBills(BaseCreateBillModel item) {
        Integer number = null;
        Integer newid = null;
        Session session = HibernateUtil.getSession();

/*        ProcedureCall call = item.getStoredProcedureCall(session);
        call.registerParameter("@startDate", Date.class, ParameterMode.IN).
                bindValue(item.getStartDate());
        call.registerParameter("@endDate", Date.class, ParameterMode.IN).
                bindValue(item.getEndDate());
        call.registerParameter("@userID", Integer.class, ParameterMode.IN).
                bindValue(GetIdCurrentUser());
        call.registerParameter("@idTypeCash", Integer.class, ParameterMode.IN).
                bindValue(item.getTypeCash());
        call.registerParameter("@Number", Integer.class, ParameterMode.INOUT).
                bindValue(number);
        call.registerParameter("@NewID", Integer.class, ParameterMode.INOUT).
                bindValue(newid);


        try {
            call.getOutputs();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            session.close();
        }*/

        StoredProcedureQuery query = item.getStoredProcedureQuery(session);

        query = query.
                registerStoredProcedureParameter("startDate", Timestamp.class, ParameterMode.IN).
                setParameter("startDate", item.getStartDate()).
                registerStoredProcedureParameter("endDate", Timestamp.class, ParameterMode.IN).
                setParameter("endDate", item.getEndDate()).
                registerStoredProcedureParameter("userID", Integer.class, ParameterMode.IN).
                setParameter("userID", GetIdCurrentUser()).
                registerStoredProcedureParameter("idTypeCash", Integer.class, ParameterMode.IN).
                setParameter("idTypeCash", item.getTypeCash()).
                registerStoredProcedureParameter("Number", Integer.class, ParameterMode.INOUT).
                setParameter("Number", number).
                registerStoredProcedureParameter("NewID", Integer.class, ParameterMode.INOUT).
                setParameter("NewID", newid);

        try {
            query.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            session.close();
        }

     /*
        session.createNativeQuery("exec " + sp_name + " :startDate, :endDate, null, :idCombobox, :idTypeCash, :number, :newid").
                setParameter("startDate", item.getStartDate()).
                setParameter("endDate", item.getEndDate()).
                setParameter("idCombobox", idCombobox).
                setParameter("idTypeCash", item.getTypeCash()).
                setParameter("number", number).
                setParameter("New_ID", newid).list();
*/
    }

    protected void SetDefaultModelBill(
            Model model,
            CreateBillModel item) {
        SetDefaultModel(model);
        model.addAttribute("bill", item);
        model.addAttribute("Title", "Детализация к счету");
        model.addAttribute("agencies", AgencyEntity.GetMapAgencies(item.getAgency()));
    }

    protected void SetDefaultModelBillDriver(
            Model model,
            CreateBillDriverModel item) {
        SetDefaultModel(model);
        model.addAttribute("bill", item);
        model.addAttribute("Title", "Детализация к счету водителю");
        model.addAttribute("drivers", DriverEntity.GetMapDrivers(item.getDriver()));
    }

    @Override
    protected void SetDefaultModel(Model model) {
        model.addAttribute("typesCash", TypesCashEntity.GetMapTypesCash());
    }
}
