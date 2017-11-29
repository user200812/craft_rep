package ru.craftautoweb.models;


import org.hibernate.Session;
//import org.hibernate.procedure.ProcedureCall;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.StoredProcedureQuery;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Администратор on 02.12.2016.
 */
public abstract class BaseCreateBillModel {
    protected Date startDate;
    protected Date endDate;
    protected Integer typeCash;

    public BaseCreateBillModel() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        this.startDate = startDate.getTime();
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.endDate = endDate.getTime();
    }

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getTypeCash() {
        return typeCash;
    }

    public void setTypeCash(Integer typeCash) {
        this.typeCash = typeCash;
    }

    public abstract StoredProcedureQuery getStoredProcedureQuery(Session session);
    //public abstract ProcedureCall getStoredProcedureCall(Session session);
}
