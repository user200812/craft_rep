package ru.craftautoweb.entities;

import org.hibernate.Session;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by User on 03.12.2016.
 */
@Entity
@Table(name = "V_BillDetails")
public class VBillDetailsEntity {
    private Integer idBill;
    private Timestamp dateTime;
    private Integer id;
    private Integer number;
    private String customer;
    private String nums;
    private String route;
    private Double taxa;
    private String auto;
    private String name;
    private String juristicPerson;
    private String director;
    private Double reduction;
    private Double toPay;

    @Basic
    @Column(name = "idBill", nullable = true)
    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    @Basic
    @Column(name = "DateTime", nullable = true)
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Id
    @Basic
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "Auto", nullable = true, length = 50)
    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "JuristicPerson", nullable = true, length = 256)
    public String getJuristicPerson() {
        return juristicPerson;
    }

    public void setJuristicPerson(String juristicPerson) {
        this.juristicPerson = juristicPerson;
    }

    @Basic
    @Column(name = "Director", nullable = true, length = 256)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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

        VBillDetailsEntity that = (VBillDetailsEntity) o;

        if (idBill != null ? !idBill.equals(that.idBill) : that.idBill != null) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (nums != null ? !nums.equals(that.nums) : that.nums != null) return false;
        if (route != null ? !route.equals(that.route) : that.route != null) return false;
        if (taxa != null ? !taxa.equals(that.taxa) : that.taxa != null) return false;
        if (auto != null ? !auto.equals(that.auto) : that.auto != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (juristicPerson != null ? !juristicPerson.equals(that.juristicPerson) : that.juristicPerson != null)
            return false;
        if (director != null ? !director.equals(that.director) : that.director != null) return false;
        if (reduction != null ? !reduction.equals(that.reduction) : that.reduction != null) return false;
        if (toPay != null ? !toPay.equals(that.toPay) : that.toPay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBill != null ? idBill.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (nums != null ? nums.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + (taxa != null ? taxa.hashCode() : 0);
        result = 31 * result + (auto != null ? auto.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (juristicPerson != null ? juristicPerson.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (reduction != null ? reduction.hashCode() : 0);
        result = 31 * result + (toPay != null ? toPay.hashCode() : 0);
        return result;
    }

    public static List<VBillDetailsEntity> getDetailsByBillId(Session session, Integer id) {
        // формируем запрос
        String sql = "from VBillDetailsEntity where idBill=:idBill order by id";

        // и выполняем его
        List<VBillDetailsEntity> list = session.createQuery(sql).
                setParameter("idBill", id).
                list();
        return list;
    }
}
