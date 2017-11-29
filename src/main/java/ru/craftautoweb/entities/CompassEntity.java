package ru.craftautoweb.entities;

import org.hibernate.Session;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Администратор on 21.11.2016.
 */
@Entity
@Table(name = "Compass")
public class CompassEntity extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compass_seq")
    @SequenceGenerator(name = "compass_seq",
            sequenceName = "compass_seq",
            allocationSize = 1)
    */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "Comment", nullable = true, length = 50)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "isDeleted", nullable = true)
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompassEntity that = (CompassEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }
    public Integer Save() {
        // Получаем объект сесси для работы с базой
        // и открываем транзакцию
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        if(id == null) {
            // Сохраняем в БД
            id = (Integer) session.save(this);
        }
        else {
            CompassEntity updatedItem = getById(id);
            updatedItem.name = name;
            updatedItem.comment = comment;
            updatedItem.isDeleted = 0;
            // Сохраняем в БД
            session.update(updatedItem);
        }
        session.getTransaction().commit();
        session.close();

        return id;
    }

    public static CompassEntity getById(int id) {
        // Получаем объект сесси для работы с базой
        Session session = HibernateUtil.getSession();

        // формируем запрос
        String sql = "from CompassEntity where id=:id";

        // и выполняем его
        List<CompassEntity> list = session.createQuery(sql).
                setParameter("id", id).
                list();
        session.close();

        // Возвращаем элемент
        if(list.size() > 0) {
            return list.get(0);
        }
        else {
            return null;
        }
    }

    public static void Delete(int id) {
        // Получаем объект сесси для работы с базой
        // и открываем транзакцию
        Session session = HibernateUtil.getSession();

        // Обновляем запись
        CompassEntity item = (CompassEntity)session.get(CompassEntity.class, new Integer(id));
        if(item != null) {
            item.UpdateForDelete(session);
        }
        session.close();
    }
}
