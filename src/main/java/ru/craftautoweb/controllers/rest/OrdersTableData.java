package ru.craftautoweb.controllers.rest;

import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.craftautoweb.entities.*;
import ru.craftautoweb.utils.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static ru.craftautoweb.entities.UserEntity.GetIdCurrentUser;

@RestController
public class OrdersTableData {
    @RequestMapping(
            value = {"/Orders/AjaxGetJsonDataForTypeahead"},
            method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity AjaxGetJsonDataForTypeahead(
            @RequestParam(value = "type", defaultValue = "") String type,
            @RequestParam(value = "search", defaultValue = "") String search) {

        if(type.equals("compass")) type = "Compass";
        if(type.equals("route")) type = "Route";
        if(type.equals("auto")) type = "Auto";
        if(type.equals("table")) type = "Table";
        if(type.equals("driver")) type = "Driver";
        if(type.equals("agency")) type = "Agency";
        if(type.equals("result")) type = "Results";

        // Получаем объект сессии для работы с базой
        Session session = HibernateUtil.getSession();

        String sql = "select name from " + type + "Entity where isDeleted=0";
        sql += " and name like '%" + search + "%'";
        sql += " order by 1";

        List<String> list = session.createQuery(sql).
                list();
        session.close();

        return new ResponseEntity<List<String>>(
                list,
                HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/Orders/AjaxGetTableData"},
            method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity List(
            @RequestParam(value = "ordertype", defaultValue = "asc") String ordertype,
            @RequestParam(value = "orderfield", defaultValue = "Datetime") String orderfield,
            @RequestParam(value = "dateStart", defaultValue = "") String dateStart,
            @RequestParam(value = "dateEnd", defaultValue = "") String dateEnd,
            @RequestParam(value = "number", defaultValue = "") String number,
            @RequestParam(value = "customer", defaultValue = "") String customer,
            @RequestParam(value = "route", defaultValue = "") String route,
            @RequestParam(value = "driver", defaultValue = "") String driver,
            @RequestParam(value = "agency", defaultValue = "") String agency,
            @RequestParam(value = "toggleuser", defaultValue = "") String toggleuser,
            @RequestParam(value = "newid", defaultValue = "0") int newid,
            @RequestParam(value = "activePage", defaultValue = "1") int activePage,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "search", defaultValue = "") String search) {

        // Получаем объект сессии для работы с базой
        Session session = HibernateUtil.getSession();

        // меняем тип сортировки для применения в запросе
        ordertype = ordertype.equals("desc") ? ordertype : "";

        // формируем запрос
        String sql = "from VDocumentsEntity where 1=1";
        // Фильтруем по номеру
        if (!number.equals(""))
            sql += " and number=" + number;
        // Фильтруем по заказ для
        if (!customer.equals(""))
            sql += " and customer like '%" + customer + "%'";
        // Фильтруем по маршруту
        if (!route.equals(""))
            sql += " and route like '%" + route + "%'";
        // Фильтруем по принципалу
        if (!driver.equals(""))
            sql += " and driver like '%" + driver + "%'";
        // Фильтруем по заказчику
        if (!agency.equals(""))
            sql += " and agency like '%" + agency + "%'";

        // Фильтруем по пользователю
        if (!toggleuser.equals("")) {
            String user = SecurityContextHolder.
                    getContext().
                    getAuthentication().
                    getName();
            int logonIdUser = CraftUtils.GetLogonUserId(user);
            if (logonIdUser != 0)
                sql += " and owner=" + logonIdUser;
        }

        // Фильтруем по дате начала периода
        Date start;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            start = formatter.parse(dateStart);
            sql += " and DateTime>=:dateStart";
        } catch (ParseException e) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, 1);
            start = c.getTime();
        }

        // Фильтруем по дате конца периода
        Date end;
        try {
            end = formatter.parse(dateEnd);
            end = CraftUtils.addDays(end, 1);
            sql += " and DateTime<:dateEnd";
        } catch (ParseException e) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, 1);
            end = c.getTime();
        }

        // Делаем фильтр по search
        if (!search.equals("")) {
            sql += " and (Number like '%" + search + "%'";
            sql += " or nums like '%" + search + "%'";
            sql += " or customer like '%" + search + "%'";
            sql += " or taxa like '%" + search + "%'";
            sql += " or typecashstring like '%" + search + "%'";
            sql += " or telephones like '%" + search + "%'";
            sql += " or auto like '%" + search + "%'";
            sql += " or agency like '%" + search + "%'";
            sql += " or result like '%" + search + "%'";
            sql += " or driver like '%" + search + "%'";
            sql += " or route like '%" + search + "%'";
            sql += " or 'Table' like '%" + search + "%'";
            sql += " or DateTime like '%" + search + "%'";
            sql += " or compass like '%" + search + "%')";
        }

        // Сортируем
        sql += " order by " + orderfield + " " + ordertype;

        // и выполняем его
        List<VDocumentsEntity> list = session.createQuery(sql).
                setParameter("dateStart", start).
                setParameter("dateEnd", end).
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
        String rows = GetBaseTable(list, newid);

        return new ResponseEntity<RestfulResult>(
                new RestfulResult(pagination.GetPagination(), rows, pagination.numPages, pagination.activePage),
                HttpStatus.OK);
    }

    private String GetBaseTable(List<VDocumentsEntity> list, int newid) {

        String rows = "";

        for (VDocumentsEntity item : list) {
            rows += item.GetRowHtml(newid);
        }
        return rows;
    }

    private int GetActivePage(List<VDocumentsEntity> list, int newid, int pageSize) {
        int index = 0;
        boolean isFound = false;
        // Определяем позицию нового элемента в списке
        for (VDocumentsEntity item : list) {
            index++;
            if (item.getIdDoc() == newid) {
                isFound = true;
                break;
            }
        }
        // возвращаем номер страницы с элементом
        return isFound ? index / pageSize + (index % pageSize == 0 ? 0 : 1) : 0;
    }

    @RequestMapping(
            value = {"/Orders/AjaxDelete"},
            method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity Delete(@RequestParam(value = "ordersIds[]", defaultValue = "") int[] ids) {
        // Получаем объект сесси для работы с базой
        // и открываем транзакцию
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        for (int i = 0; i < ids.length; i++) {
            DocumentsEntity order = new DocumentsEntity();
            order.setId(ids[i]);
            session.delete(order);
        }
        session.getTransaction().commit();
        session.close();
        return new ResponseEntity(new Boolean(true), HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/Orders/AjaxSetResult"},
            method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity SetResult(
            @RequestParam(value = "ordersIds[]", defaultValue = "") int[] ids,
            @RequestParam(value = "result", defaultValue = "") Integer result) {
        // Получаем объект сесси для работы с базой
        // и открываем транзакцию
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        for (int i = 0; i < ids.length; i++) {
            DocumentsEntity order = DocumentsEntity.getById(ids[i]);
            order.setResult(result);
            order.setResultcre(GetIdCurrentUser());
            session.update(order);
        }
        session.getTransaction().commit();
        session.close();
        return new ResponseEntity(new Boolean(true), HttpStatus.OK);
    }
}
