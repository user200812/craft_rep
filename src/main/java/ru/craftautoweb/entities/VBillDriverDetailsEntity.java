package ru.craftautoweb.entities;

import org.hibernate.Session;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 03.12.2016.
 */
@Entity
@Table(name = "V_BillDriverDetails")
public class VBillDriverDetailsEntity {
    private Integer idBill;
    private Integer idDoc;
    private Timestamp dateTime;
    private Integer number;
    private String customer;
    private String nums;
    private String route;
    private Double taxa;
    private Integer typeCash;
    private Double reduction;
    private Double commission;
    private Double toPay;

    @Basic
    @Column(name = "idBill", nullable = true)
    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    @Id
    @Basic
    @Column(name = "idDoc", nullable = false)
    public Integer getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }

    @Basic
    @Column(name = "DateTime", nullable = true)
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Basic
    //@Column(name = "Numb", nullable = false)
    @Column(name = "Number", nullable = false)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "Customer", nullable = true, length = 50)
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Basic
    @Column(name = "Nums", nullable = true, length = 50)
    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    @Basic
    @Column(name = "Route", nullable = true, length = 1024)
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Basic
    @Column(name = "Taxa", nullable = true)
    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    @Basic
    @Column(name = "TypeCash", nullable = true)
    public Integer getTypeCash() {
        return typeCash;
    }

    public void setTypeCash(Integer typeCash) {
        this.typeCash = typeCash;
    }

    @Basic
    @Column(name = "Reduction", nullable = true, precision = 0)
    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }

    @Basic
    @Column(name = "Commission", nullable = true, precision = 0)
    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    @Basic
    @Column(name = "toPay", nullable = true, precision = 0)
    public Double getToPay() {
        return toPay;
    }

    public void setToPay(Double toPay) {
        this.toPay = toPay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VBillDriverDetailsEntity that = (VBillDriverDetailsEntity) o;

        if (idBill != null ? !idBill.equals(that.idBill) : that.idBill != null) return false;
        if (idDoc != null ? !idDoc.equals(that.idDoc) : that.idDoc != null) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (nums != null ? !nums.equals(that.nums) : that.nums != null) return false;
        if (route != null ? !route.equals(that.route) : that.route != null) return false;
        if (taxa != null ? !taxa.equals(that.taxa) : that.taxa != null) return false;
        if (typeCash != null ? !typeCash.equals(that.typeCash) : that.typeCash != null) return false;
        if (reduction != null ? !reduction.equals(that.reduction) : that.reduction != null) return false;
        if (commission != null ? !commission.equals(that.commission) : that.commission != null) return false;
        if (toPay != null ? !toPay.equals(that.toPay) : that.toPay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBill != null ? idBill.hashCode() : 0;
        result = 31 * result + (idDoc != null ? idDoc.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (nums != null ? nums.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + (taxa != null ? taxa.hashCode() : 0);
        result = 31 * result + (typeCash != null ? typeCash.hashCode() : 0);
        result = 31 * result + (reduction != null ? reduction.hashCode() : 0);
        result = 31 * result + (commission != null ? commission.hashCode() : 0);
        result = 31 * result + (toPay != null ? toPay.hashCode() : 0);
        return result;
    }
    public static List<VBillDriverDetailsEntity> getDetailsByBillId(Session session, Integer id) {
        // формируем запрос
        String sql = "from VBillDriverDetailsEntity where idBill=:idBill order by id";

        // и выполняем его
        List<VBillDriverDetailsEntity> list = session.createQuery(sql).
                setParameter("idBill", id).
                list();
        return list;
    }
}
