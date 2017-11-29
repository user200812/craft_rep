package ru.craftautoweb.entities;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "V_BillDrivers")
public class VBillDriversEntity extends VBaseBillEntity {
    private Integer idDriver;

    public VBillDriversEntity() { super();
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Basic
    @Column(name = "endDate", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
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
    @Column(name = "TypeCash", nullable = true)
    public Integer getTypeCash() {
        return typeCash;
    }

    public void setTypeCash(Integer typeCash) {
        this.typeCash = typeCash;
    }

    @Basic
    @Column(name = "OwnUser", nullable = false, length = 50)
    public String getOwnUser() {
        return ownUser;
    }

    public void setOwnUser(String ownUser) {
        this.ownUser = ownUser;
    }

    @Basic
    @Column(name = "Name", nullable = true, length = 564)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "idDriver", nullable = false)
    public Integer getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(Integer idDriver) {
        this.idDriver = idDriver;
    }

    @Basic
    @Column(name = "toPay", nullable = true, precision = 0)
    public Double getToPay() {
        return toPay;
    }

    public void setToPay(Double toPay) {
        this.toPay = toPay;
    }

    @Basic
    @Column(name = "Type", nullable = false, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "isPaid", nullable = true, length = 1)
    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VBillDriversEntity that = (VBillDriversEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (typeCash != null ? !typeCash.equals(that.typeCash) : that.typeCash != null) return false;
        if (ownUser != null ? !ownUser.equals(that.ownUser) : that.ownUser != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (idDriver != null ? !idDriver.equals(that.idDriver) : that.idDriver != null) return false;
        if (toPay != null ? !toPay.equals(that.toPay) : that.toPay != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (isPaid != null ? !isPaid.equals(that.isPaid) : that.isPaid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (typeCash != null ? typeCash.hashCode() : 0);
        result = 31 * result + (ownUser != null ? ownUser.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idDriver != null ? idDriver.hashCode() : 0);
        result = 31 * result + (toPay != null ? toPay.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (isPaid != null ? isPaid.hashCode() : 0);
        return result;
    }
}
