package ru.craftautoweb.entities;

import org.hibernate.Session;
import org.springframework.security.acls.model.NotFoundException;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import static ru.craftautoweb.entities.UserEntity.GetIdCurrentUser;

/**
 * Created by User on 24.11.2016.
 */
@Entity
@Table(name = "BillDrivers")
public class BillDriversEntity extends BaseBillEntity {

    private Integer driver;
    private String driverName;
    private String typeCash;
    private List<VBillDriverDetailsEntity> details;
    private VBillDriverDetailsEntity total;


    public BillDriversEntity() {
        isPaid = false;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    //@Column(name = "Numb", nullable = true)
    @Column(name = "Number", nullable = true)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "startDate", nullable = true)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "endDate", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "createDate", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "isPaid", nullable = false)
    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillDriversEntity that = (BillDriversEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (isPaid != null ? !isPaid.equals(that.isPaid) : that.isPaid != null) return false;
        if (driver != null ? !driver.equals(that.driver) : that.driver != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (isPaid != null ? isPaid.hashCode() : 0);
        return result;
    }

    public static BillDriversEntity getById(int id) {
        // Получаем объект сесси для работы с базой
        Session session = HibernateUtil.getSession();
        BillDriversEntity item = null;
        if (id > 0) {
            item =
                    (BillDriversEntity) session.get(
                            BillDriversEntity.class,
                            new Integer(id));
            item.details = VBillDriverDetailsEntity.getDetailsByBillId(session, item.id);
            item.setTotal();
            item.driverName = "";
            if (item.driver != null) {
                DriverEntity d = (DriverEntity) session.get(DriverEntity.class, item.driver);
                if (d != null) {
                    item.driverName = d.FIO();
                }
            }
            item.typeCash = "";
            if (item.idTypeCash != null) {
                TypesCashEntity t = (TypesCashEntity) session.get(TypesCashEntity.class, item.idTypeCash);
                if (t != null) {
                    item.typeCash = t.getName();
                }
            }
        }
        session.close();
        return item;
    }

    @Basic
    @Column(name = "Driver", nullable = true)
    public Integer getDriver() {
        return driver;
    }

    public void setDriver(Integer driver) {
        this.driver = driver;
    }

    @Basic
    @Column(name = "TypeCash", nullable = true)
    public Integer getIdTypeCash() {
        return idTypeCash;
    }

    public void setIdTypeCash(Integer idTypeCash) {
        this.idTypeCash = idTypeCash;
    }

    @Basic
    @Column(name = "Owner", nullable = true)
    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    protected BaseBillEntity Merge() {
        BillDriversEntity billsBillDriversEntity = getById(id);
        billsBillDriversEntity.number = number;
        billsBillDriversEntity.createDate = createDate;
        return billsBillDriversEntity;
    }

    @Transient
    public String getDriverName() {
        return driverName;
    }

    @Transient
    public String getTypeCash() {
        return typeCash;
    }

    @Transient
    public List<VBillDriverDetailsEntity> getDetails() {
        return details;
    }


    @Transient
    public VBillDriverDetailsEntity getTotal() {
        return total;
    }

    private void setTotal() {
        total = new VBillDriverDetailsEntity();
        total.setTaxa(0.0);
        total.setToPay(0.0);
        total.setReduction(0.0);
        total.setCommission(0.0);
        for (VBillDriverDetailsEntity item : details) {
            total.setTaxa(total.getTaxa() + item.getTaxa());
            total.setToPay(total.getToPay() + item.getToPay());
            total.setReduction(total.getReduction() + item.getReduction());
            total.setCommission(total.getCommission() + item.getCommission());
        }
    }

}
