package ru.craftautoweb.entities;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.util.StringUtils;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import static ru.craftautoweb.entities.UserEntity.GetIdCurrentUser;

/**
 * Created by User on 24.11.2016.
 */
@Entity
@Table(name = "Bills")
public class BillsEntity extends BaseBillEntity {

    private Integer agency;
    private String agencyName;
    private String agencyJuristicPerson;

    private List<VBillDetailsEntity> details;
    private VBillDetailsEntity total;

    public BillsEntity() {
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
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "startDate", nullable = true)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "endDate", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @DateTimeFormat(pattern = "dd.MM.yyyy")
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

        BillsEntity that = (BillsEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (isPaid != null ? !isPaid.equals(that.isPaid) : that.isPaid != null) return false;
        if (agency != null ? !agency.equals(that.agency) : that.agency != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (agency != null ? agency.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (isPaid != null ? isPaid.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "Agency", nullable = true)
    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
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

    public static BillsEntity getById(int id) {
        // Получаем объект сессии для работы с базой
        Session session = HibernateUtil.getSession();
        BillsEntity item = null;
        // Возвращаем элемент
        if (id > 0) {
            item =
                    (BillsEntity) session.get(
                            BillsEntity.class,
                            new Integer(id));
            item.details = VBillDetailsEntity.getDetailsByBillId(session, item.id);
            item.setTotal();
            item.agencyName = "";
            if (item.agency != null) {
                AgencyEntity a = (AgencyEntity) session.get(AgencyEntity.class, item.agency);
                if (a != null) {
                    item.agencyName = a.getName();
                    item.agencyJuristicPerson = a.getJuristicPerson();
                }
            }
        }
        session.close();
        return item;
    }

    @Transient
    public String getAgencyName() {
        return agencyName;
    }

    @Transient
    public List<VBillDetailsEntity> getDetails() {
        return details;
    }

    @Transient
    public VBillDetailsEntity getTotal() {
        return total;
    }

    private void setTotal() {
        total = new VBillDetailsEntity();
        total.setTaxa(0.0);
        total.setToPay(0.0);
        total.setReduction(0.0);
        for (VBillDetailsEntity item : details) {
            total.setTaxa(total.getTaxa() + item.getTaxa());
            total.setToPay(total.getToPay() + item.getToPay());
            total.setReduction(total.getReduction() + item.getReduction());
        }
    }

    public BillsEntity Merge() {
        BillsEntity billsEntity = getById(id);
        billsEntity.number = number;
        billsEntity.createDate = createDate;
        return billsEntity;
    }

    @Transient
    public String getAgencyJuristicPerson() {
        return agencyJuristicPerson == null
                || !StringUtils.hasText(agencyJuristicPerson)
                ? agencyName
                : agencyJuristicPerson;
    }

 /*
    public Integer Save() {
        // Получаем объект сесси для работы с базой
        // и открываем транзакцию
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            idOwner = GetIdCurrentUser();
            if (id == null) {
                throw new NotFoundException("Не найден счет id = " + id);
            } else {
                // Сохраняем в БД
                session.update(Merge());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
        }

        return id;
    }*/
}
