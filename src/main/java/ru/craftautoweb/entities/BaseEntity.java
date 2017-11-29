package ru.craftautoweb.entities;

import org.hibernate.Session;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;
import java.util.Comparator;

import static ru.craftautoweb.utils.CraftUtils.tdcell;

/**
 * Created by Администратор on 21.11.2016.
 */

public abstract class BaseEntity {
    protected Integer id;
    protected String name;
    protected Integer isDeleted;
    protected String comment;

    protected BaseEntity() {
        isDeleted = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override public String toString() {
        String str = "id = " + id + "" +
                "; name = " + name +
                "; isDeleted = " + isDeleted + ";";
        return str;
    }

    public String GetRowHtml(String dictype, int newid) {
        String newidclass = newid == id? " class='newid' " : "";
        String rows;
        String href = "";
        if (isDeleted != null && isDeleted == 0)
        {
            href = "Directories/Edit" + dictype + "/" + id;
            rows = "<tr " + newidclass + " data-id=" + id + ">";
            rows = rows + tdcell("<a data-toggle='modal' data-target='#delete' class='glyphicon glyphicon-remove-circle delete-link' data-href='Directories/Delete" + dictype + "?id=" + id + "' title='Удалить' item-id=" + id + "></a>");
        }
        else
        {
            rows = "<tr class=isDeleted title=Удален>";
            rows = rows + tdcell("");
        }
        rows = rows + tdcell(name, href);
        rows = rows + "</tr>";
        return rows;
    }

    public abstract Integer Save();

    protected void UpdateForDelete(Session session) {
        session.beginTransaction();
        isDeleted = Integer.valueOf(1);
        session.update(this);
        session.getTransaction().commit();
    }
}
