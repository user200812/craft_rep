package ru.craftautoweb.controllers.rest;

import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.craftautoweb.entities.*;
import ru.craftautoweb.utils.HibernateUtil;
import ru.craftautoweb.utils.Pagination;

import java.util.Iterator;
import java.util.List;

@RestController
public class DirectoriesTableData {

    @RequestMapping(value="/Directories/AjaxGetTableData", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity GetTableData (
            @RequestParam(value="isShowDeleted") boolean isShowDeleted,
            @RequestParam(value="dictype") String dictype,
            @RequestParam(value="ordertype", defaultValue="asc") String ordertype,
            @RequestParam(value="orderfield", defaultValue="Name") String orderfield,
            @RequestParam(value="newid", defaultValue="0") int newid,
            @RequestParam(value="activePage", defaultValue="1") int activePage,
            @RequestParam(value="pageSize", defaultValue="10") int pageSize,
            @RequestParam(value="search", defaultValue="") String search) {

        // Получаем объект сесси для работы с базой
        Session session = HibernateUtil.getSession();

        // меняем тип сортировки для применения в запросе
        ordertype = ordertype.equals("desc") ? ordertype : "";
        orderfield = dictype.equals("User") ? "Username" : orderfield;

        // формируем запрос
        String sql = "from " + dictype + "Entity where 1=1";
        if(!isShowDeleted)
            sql += " and isDeleted=0";

        // Накладываем фильтр
        if(dictype.equals("Agency")) {
            sql += " and (Name like '%" + search + "%'";
            sql += " or JuristicPerson like '%" + search + "%'";
            sql += " or Telephone like '%" + search + "%'";
            sql += " or Fax like '%" + search + "%'";
            sql += " or Email like '%" + search + "%'";
            sql += " or ContactPerson like '%" + search + "%'";
            sql += " or ContactPersonCellular like '%" + search + "%'";
            sql += " or Director like '%" + search + "%'";
            sql += " or DirectorCellular like '%" + search + "%'";
            sql += " or Address like '%" + search + "%')";

        }
        else if(dictype.equals("Driver")) {
            sql += " and (concat(Surname, ' ', Name, ' ', Patronymic) like '%" + search + "%'";
            sql += " or Telephone like '%" + search + "%'";
            sql += " or Contract like '%" + search + "%'";
            sql += " or Car like '%" + search + "%'";
            sql += " or Conditioner like '%" + search + "%'";
            sql += " or Schedule like '%" + search + "%'";
            sql += " or District like '%" + search + "%'";
            sql += " or Number like '%" + search + "%'";
            sql += " or Color like '%" + search + "%'";
            sql += " or Year like '%" + search + "%'";
            sql += " or Email like '%" + search + "%'";
            sql += " or District like '%" + search + "%')";
        }
        else if (dictype.equals("User")) {
            sql += " and Username like '%" + search + "%'";
        }
        else {
            sql += " and Name like '%" + search + "%'";
        }

        // Сортируем
        if(!orderfield.equals("FIO"))
            sql += " order by " + orderfield + " " + ordertype;
        else
            sql += " order by concat(Surname, ' ', Name, ' ', Patronymic) " + ordertype;

        // и выполняем его
        List<BaseEntity> list = session.createQuery(sql).
                list();
        session.close();

        // Сортируем отдельно для ФИО принципала
        /*
        if(orderfield.equals("FIO")) {
            if(ordertype.equals("desc"))
                list.sort(DriverEntity.ComparatorDesc);
            else
                list.sort(DriverEntity.ComparatorAsc);
        }
        */

        // Получаем количество записей в запросе
        int countRecs = list.size();

        // Меняем при необходимости страницу если newid установлен
        if (newid != 0)
        {
            int newactivepage = GetActivePage(list, newid, pageSize);
            if (newactivepage != 0)
            {
                activePage = newactivepage;
            }
        }
        Pagination pagination;
        pagination = new Pagination(pageSize, activePage, countRecs);
        int maxRec = pagination.skip() + pageSize;
        maxRec = maxRec > countRecs ? countRecs : maxRec;
        list = list.subList(pagination.skip(), maxRec);

        // Формируем таблицу
        String rows = GetBaseTable(list, dictype, newid);

        return new ResponseEntity<RestfulResult>(
                new RestfulResult(pagination.GetPagination(), rows, pagination.numPages, pagination.activePage),
                HttpStatus.OK);
    }

    private String GetBaseTable(List<BaseEntity> list, String dictype, int newid) {

        String rows = "";

        for (BaseEntity item : list) {
            rows += item.GetRowHtml(dictype, newid);
        }
        return rows;
    }

    private int GetActivePage(List<BaseEntity> list, int newid, int pageSize) {
        int index = 0;
        boolean isFound = false;
        // Определяем позицию нового элемента в списке
        for (BaseEntity item : list) {
            index++;
            if (item.getId() == newid)
            {
                isFound = true;
                break;
            }
        }
        // возвращаем номер страницы с элементом
        return isFound ? index / pageSize + (index % pageSize == 0 ? 0 : 1) : 0;
    }

    @RequestMapping(value="/Directories/DeleteUser", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity DeleteUser (
            @RequestParam(value="id") int id) {
        UserEntity.Delete(id);
        return new ResponseEntity<Boolean>(new Boolean(true), HttpStatus.OK);
    }

    @RequestMapping(value="/Directories/DeleteAgency", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity DeleteAgency (
            @RequestParam(value="id") int id) {
        AgencyEntity.Delete(id);
        return new ResponseEntity<Boolean>(new Boolean(true), HttpStatus.OK);
    }

    @RequestMapping(value="/Directories/DeleteRoute", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity DeleteRoute (
            @RequestParam(value="id") int id) {
        RouteEntity.Delete(id);
        return new ResponseEntity<Boolean>(new Boolean(true), HttpStatus.OK);
    }

    @RequestMapping(value="/Directories/DeleteCompass", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity DeleteCompass(
            @RequestParam(value="id") int id) {
        CompassEntity.Delete(id);
        return new ResponseEntity<Boolean>(new Boolean(true), HttpStatus.OK);
    }

    @RequestMapping(value="/Directories/DeleteTable", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity DeleteTable (
            @RequestParam(value="id") int id) {
        TableEntity.Delete(id);
        return new ResponseEntity<Boolean>(new Boolean(true), HttpStatus.OK);
    }

    @RequestMapping(value="/Directories/DeleteAuto", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity DeleteAuto (
            @RequestParam(value="id") int id) {
        AutoEntity.Delete(id);
        return new ResponseEntity<Boolean>(new Boolean(true), HttpStatus.OK);
    }
}
