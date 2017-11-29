package ru.craftautoweb.controllers;

import com.github.moneytostr.MoneyToStr;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.craftautoweb.entities.BillDriversEntity;
import ru.craftautoweb.entities.BillsEntity;
import ru.craftautoweb.utils.ResourceNotFoundException;

/**
 * Created by User on 04.12.2016.
 */
@Controller
public class BillReportController {

    @RequestMapping(value = "/Bills/Print/{id}", method = RequestMethod.GET)
    public String Print(@PathVariable int id, Model model) {
        BillsEntity item = BillsEntity.getById(id);
        if (item == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("bill", item);

        return "bills/print";
    }


    @RequestMapping(value = "/Bills/PrintDriver/{id}", method = RequestMethod.GET)
    public String PrintDriver(@PathVariable int id, Model model) {
        BillDriversEntity item = BillDriversEntity.getById(id);
        if (item == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("bill", item);
        MoneyToStr moneyToStr = new MoneyToStr(MoneyToStr.Currency.RUR, MoneyToStr.Language.RUS, MoneyToStr.Pennies.NUMBER);
        model.addAttribute("totalTaxaStr", moneyToStr.convert(item.getTotal().getTaxa()));
        model.addAttribute("totalCommissionStr", moneyToStr.convert(item.getTotal().getCommission()));
        model.addAttribute("totalReductionStr", moneyToStr.convert(item.getTotal().getReduction()));
        model.addAttribute("totaltoPayStr", moneyToStr.convert(item.getTotal().getToPay()));
        return "bills/printdriver";
    }

}
