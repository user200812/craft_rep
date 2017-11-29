package ru.craftautoweb.models;

import org.hibernate.Session;
//import org.hibernate.procedure.ProcedureCall;
import javax.persistence.ParameterMode;
import org.springframework.stereotype.Component;
import javax.persistence.StoredProcedureQuery;

/**
 * Created by Администратор on 02.12.2016.
 */
@Component
public class CreateBillDriverModel extends BaseCreateBillModel {
    private Integer driver;

    public Integer getDriver() {
        return driver;
    }

    public void setDriver(Integer driver) {
        this.driver = driver;
    }

    @Override
    public StoredProcedureQuery getStoredProcedureQuery(Session session) {
        System.out.println("call SP_CreateBillDrivers");
        StoredProcedureQuery query = session.createStoredProcedureQuery("SP_CreateBillDrivers").
                registerStoredProcedureParameter("idDriver", Integer.class, ParameterMode.IN).
                setParameter("idDriver", driver == null ? 0 : driver);
        return query;
        //return null;
    }

/*
    public ProcedureCall getStoredProcedureCall(Session session) {
        ProcedureCall call = session.createStoredProcedureCall("SP_CreateBillDrivers");
        call.registerParameter("@idDriver", Integer.class, ParameterMode.IN).
                bindValue(driver == null ? 0 : driver);

        return call;
    }
*/
}
