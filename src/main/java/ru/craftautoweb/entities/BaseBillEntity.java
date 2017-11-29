package ru.craftautoweb.entities;

import org.hibernate.Session;
import org.springframework.security.acls.model.NotFoundException;
import ru.craftautoweb.utils.HibernateUtil;

import java.sql.Timestamp;
import java.util.List;

import static ru.craftautoweb.entities.UserEntity.GetIdCurrentUser;

/**
 * Created by Администратор on 02.12.2016.
 */
public abstract class BaseBillEntity {
    protected Integer id;
    protected Integer number;
    protected Timestamp startDate;
    protected Timestamp endDate;
    protected Timestamp createDate;
    protected Boolean isPaid;
    protected Integer idOwner;
    protected Integer idTypeCash;

    public Integer getIdTypeCash() {
        return idTypeCash;
    }

    public void setIdTypeCash(Integer idOwner) {
        this.idTypeCash = idTypeCash;
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

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

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (isPaid != null ? isPaid.hashCode() : 0);
        return result;
    }

    protected abstract BaseBillEntity Merge();

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
        finally {
            session.close();
        }

        return id;
    }

}
