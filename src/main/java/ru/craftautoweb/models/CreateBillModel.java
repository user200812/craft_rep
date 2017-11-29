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
public class CreateBillModel extends BaseCreateBillModel {
    private Integer agency;

    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    @Override
    public StoredProcedureQuery getStoredProcedureQuery(Session session) {
        System.out.println("call SP_CreateBills");
        StoredProcedureQuery query = session.createStoredProcedureQuery("SP_CreateBills").
                registerStoredProcedureParameter("idAgency", Integer.class, ParameterMode.IN).
                setParameter("idAgency", agency == null ? 0 : agency);
        return query;
        //return null;
    }

/*
    public ProcedureCall getStoredProcedureCall(Session session) {
        ProcedureCall call = session.createStoredProcedureCall("SP_CreateBills");
        call.registerParameter("@idAgency", Integer.class, ParameterMode.IN).
                bindValue(agency == null ? 0 : agency);

        return call;
    }
*/
}
