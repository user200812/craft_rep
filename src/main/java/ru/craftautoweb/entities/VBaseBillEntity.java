package ru.craftautoweb.entities;

import org.hibernate.Session;
import ru.craftautoweb.utils.HibernateUtil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import static ru.craftautoweb.utils.CraftUtils.tdcell;

/**
 * Created by Администратор on 22.11.2016.
 */
public class VBaseBillEntity {
    protected Integer id;
    protected String period;
    protected Integer number;
    protected String name;
    protected String type;
    protected Double toPay;
    protected String isPaid;
    protected Timestamp startDate;
    protected Timestamp endDate;
    protected Timestamp createDate;
    protected Integer typeCash;
    protected String ownUser;

    protected VBaseBillEntity() {
        number = Integer.valueOf(0);
        toPay = Double.valueOf(0);
        isPaid = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
        setPeriod();
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
        setPeriod();
    }

    private void setPeriod() {
        if(startDate != null && endDate != null) {
            String strStartDate = new SimpleDateFormat("dd.MM.yyyy").format(startDate);
            String strEndDate = new SimpleDateFormat("dd.MM.yyyy").format(endDate);
            period = strStartDate + " \u2013 " + strEndDate;
        }
    }

    public String GetRowHtml(String details, int newid) {
        String rows;
        String newidclass = newid == id ? "newid" : "";
        String newidname = newid == id ? " name=newid" : "";
        String href = "Bills/Details"+  details + "/" + id;

        rows = "<tr" + newidname + " class='" + newidclass + "' data-id=" + id + ">";
        rows = rows + tdcell("<input onclick=checkbx(this) type=\"checkbox\" title=\"Выделить\" id=" + id + " />");
        rows = rows + tdcell(number.toString(), href);
        rows = rows + tdcell(period, href);
        rows = rows + tdcell(name, href);
        rows = rows + tdcell(type, href);
        rows = rows + tdcell(toPay, href);
        rows = rows + tdcell("<input " + (new String("V").equals(isPaid) ? "checked" : "") + " class=\"toggle-paid\" type=\"checkbox\" data-toggle=\"toggle\" data-on=\"Да\" data-off=\"Нет\" id=\"pay" + id + "\">");
        rows = rows + "</tr>";

        return rows;
    }
}
