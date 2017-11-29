package ru.craftautoweb.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by User on 04.12.2016.
 */
@Component
public class FullReportModel {
    private Timestamp startDate;

    private Timestamp endDate;

    private Integer idTypeCash;

    public FullReportModel() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        this.startDate = new Timestamp(startDate.getTime().getTime());
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.endDate = new Timestamp(endDate.getTime().getTime());
    }

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public Timestamp getStartDate() {
        return startDate;
    }

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Integer getIdTypeCash() {
        return idTypeCash;
    }

    public void setIdTypeCash(Integer idTypeCash) {
        this.idTypeCash = idTypeCash;
    }
}
