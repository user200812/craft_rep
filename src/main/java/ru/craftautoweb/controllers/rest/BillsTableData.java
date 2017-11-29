package ru.craftautoweb.controllers.rest;

import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.craftautoweb.entities.BaseBillEntity;
import ru.craftautoweb.entities.VBaseBillEntity;
import ru.craftautoweb.entities.BillDriversEntity;
import ru.craftautoweb.entities.BillsEntity;
import ru.craftautoweb.utils.HibernateUtil;
import ru.craftautoweb.utils.Pagination;

import java.util.List;

/**
 * Created by Администратор on 22.11.2016.
 */
@RestController
public class BillsTableData {
    @RequestMapping(
            value = {"/Bills/AjaxGetDataBills",
                    "/Bills/AjaxGetDataBillDrivers"},
            method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity GetDataBills(
            @RequestParam(value = "ordertype", defaultValue = "asc") String ordertype,
            @RequestParam(value = "orderfield", defaultValue = "period") String orderfield,
            @RequestParam(value = "details", defaultValue = "") String details,
            @RequestParam(value = "newid", defaultValue = "0") int newid,
            @RequestParam(value = "activePage", defaultValue = "1") int activePage,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        // Получаем объект сессии для работы с базой
        Session session = HibernateUtil.getSession();

        // меняем тип сортировки для применения в запросе
        ordertype = ordertype.equals("desc") ? ordertype : "";

        // формируем запрос
        String sql = "from VBill" + details + "sEntity";

        // Сортируем
        if (orderfield.equals("period"))
            sql += " order by startDate " + ordertype + ", endDate " + ordertype;
        else
            sql += " order by " + orderfield + " " + ordertype;

        // и выполняем его
        List<VBaseBillEntity> list = session.createQuery(sql).
                list();
        session.close();

        // Получаем количество записей в запросе
        int countRecs = list.size();

        // Меняем при необходимости страницу если newid установлен
        if (newid != 0) {
            int newactivepage = GetActivePage(list, newid, pageSize);
            if (newactivepage != 0) {
                activePage = newactivepage;
            }
        }
        Pagination pagination;
        pagination = new Pagination(pageSize, activePage, countRecs);
        int maxRec = pagination.skip() + pageSize;
        maxRec = maxRec > countRecs ? countRecs : maxRec;
        list = list.subList(pagination.skip(), maxRec);

        // Формируем таблицу
        String rows = GetBaseTable(list, details, newid);

        return new ResponseEntity<RestfulResult>(
                new RestfulResult(pagination.GetPagination(), rows, pagination.numPages, pagination.activePage),
                HttpStatus.OK);
    }

    private String GetBaseTable(List<VBaseBillEntity> list, String details, int newid) {

        String rows = "";

        for (VBaseBillEntity item : list) {
            rows += item.GetRowHtml(details, newid);
        }
        return rows;
    }

    private int GetActivePage(List<VBaseBillEntity> list, int newid, int pageSize) {
        int index = 0;
        boolean isFound = false;
        // Определяем позицию нового элемента в списке
        for (VBaseBillEntity item : list) {
            index++;
            if (item.getId() == newid) {
                isFound = true;
                break;
            }
        }
        // возвращаем номер страницы с элементом
        return isFound ? index / pageSize + (index % pageSize == 0 ? 0 : 1) : 0;
    }

    @RequestMapping(
            value = {"/Bills/AjaxDelete"},
            method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity DeleteBill(@RequestParam(value = "billIds[]", defaultValue = "") int[] ids,
                              @RequestParam(value = "type", defaultValue = "") String type) {
        // Получаем объект сесси для работы с базой
        // и открываем транзакцию
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        for (int i = 0; i < ids.length; i++) {
            if (type.equals("Bills")) {
                BillsEntity bill = new BillsEntity();
                bill.setId(ids[i]);
                session.delete(bill);
            } else {
                BillDriversEntity bill = new BillDriversEntity();
                bill.setId(ids[i]);
                session.delete(bill);
            }

        }
        session.getTransaction().commit();
        session.close();
        return new ResponseEntity(new Boolean(true), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/Bills/AjaxSetPaid"},
            method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity SetPaid(@RequestParam(value = "id", defaultValue = "") int id,
                           @RequestParam(value = "type", defaultValue = "") String type) {
        // Получаем объект сесси для работы с базой
        // и открываем транзакцию
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        BaseBillEntity bill;
        if (type.equals("Bills")) {
            bill = (BillsEntity)session.get(BillsEntity.class, id);
        } else {
            bill = (BillDriversEntity)session.get(BillDriversEntity.class, id);
        }
        bill.setPaid(!bill.getPaid());
        session.update(bill);
        session.getTransaction().commit();
        session.close();

        return new ResponseEntity(new Boolean(true), HttpStatus.OK);
    }
}