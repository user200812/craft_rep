package ru.craftautoweb.entities;

import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Администратор on 21.11.2016.
 */
@Entity
@Table(name = "Users")
public class UserEntity extends BaseEntity {
    private String password;
    private String fio;
    private Integer role;
    private Integer countWrongPassword;
    private Boolean isBlocked;
    private Boolean isNeedChangePass;
    private Integer sessionId;

    public UserEntity() { super();
        isBlocked = true;
        role = 1;
        isNeedChangePass = false;
    }

    @Id
    @Column(name = "id")
/*
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Users_seq")
    @SequenceGenerator(name = "Users_seq",
            sequenceName = "Users_seq",
            allocationSize = 1)
*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override public Integer getId() {
        return id;
    }

    @Override public void setId(Integer id) {
        this.id = id;
    }


    @Basic
    @Column(name = "Username", nullable = false, length = 50)
    @Override public String getName() {
        return name;
    }

    @Override public void setName(String username) {
        this.name = username;
    }

    @Basic
    @Column(name = "Password", nullable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "FIO", nullable = true, length = 50)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Basic
    @Column(name = "Role", nullable = true)
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Basic
    @Column(name = "CountWrongPassword", nullable = true)
    public Integer getCountWrongPassword() {
        return countWrongPassword;
    }

    public void setCountWrongPassword(Integer countWrongPassword) {
        this.countWrongPassword = countWrongPassword;
    }

    @Basic
    @Column(name = "isBlocked", nullable = false)
    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    @Basic
    @Column(name = "isNeedChangePass", nullable = false)
    public Boolean getNeedChangePass() {
        return isNeedChangePass;
    }

    public void setNeedChangePass(Boolean needChangePass) {
        isNeedChangePass = needChangePass;
    }

    @Basic
    @Column(name = "isDeleted", nullable = true)
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Basic
    @Column(name = "session_id", nullable = true)
    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (countWrongPassword != null ? !countWrongPassword.equals(that.countWrongPassword) : that.countWrongPassword != null)
            return false;
        if (isBlocked != null ? !isBlocked.equals(that.isBlocked) : that.isBlocked != null) return false;
        if (isNeedChangePass != null ? !isNeedChangePass.equals(that.isNeedChangePass) : that.isNeedChangePass != null)
            return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        if (sessionId != null ? !sessionId.equals(that.sessionId) : that.sessionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (countWrongPassword != null ? countWrongPassword.hashCode() : 0);
        result = 31 * result + (isBlocked != null ? isBlocked.hashCode() : 0);
        result = 31 * result + (isNeedChangePass != null ? isNeedChangePass.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (sessionId != null ? sessionId.hashCode() : 0);
        return result;
    }


    public static UserEntity getById(int id) {
        UserEntity user = null;
        // Возвращаем элемент
        if(id > 0) {
            // Получаем объект сессии для работы с базой
            Session session = HibernateUtil.getSession();
            user = (UserEntity)session.get(UserEntity.class, new Integer(id));
            user.isBlocked = !user.isBlocked;
            session.close();
        }
        return user;
    }
    /*
    public static int getRoleByName(String name) {
        UserEntity user = getByName(name);
        int role = 0;
        if(user != null)
            role = user.getRole();
        return role;
    }
    */
    public static void Delete(int id) {
        // Получаем объект сесси для работы с базой
        // и открываем транзакцию
        Session session = HibernateUtil.getSession();

        // Обновляем запись
        UserEntity item = (UserEntity)session.get(UserEntity.class, new Integer(id));
        if(item != null) {
            item.UpdateForDelete(session);
        }
        session.close();
    }

    public Integer Save() {
        // Получаем объект сесси для работы с базой
        // и открываем транзакцию
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();

            if (id == null) {
                isBlocked = !isBlocked;
                // Сохраняем в БД
                id = (Integer) session.save(this);
            } else {
                UserEntity updatedItem = getById(id);
                updatedItem.name = name;
                updatedItem.comment = comment;
                updatedItem.isDeleted = 0;
                updatedItem.password = password;
                updatedItem.fio = fio;
                updatedItem.role = role;
                updatedItem.countWrongPassword = 0;
                updatedItem.isBlocked = !isBlocked;
                updatedItem.isNeedChangePass = false;
                // Сохраняем в БД
                session.merge(updatedItem);
            }
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }

        return id;
    }

    public static Integer GetIdCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        UserEntity user = UserEntity.getByName(userName);
        Integer idOwner = null;
        if(user != null) {
            idOwner = user.getId();
        }
        return idOwner;
    }



    public static UserEntity getByName(String name) {
        // Получаем объект сесси для работы с базой
        Session session = HibernateUtil.getSession();

        // формируем запрос
        String sql = "from UserEntity where lower(name)=lower(:name)";

        // и выполняем его
        List<UserEntity> list = session.createQuery(sql).
                setParameter("name", name).
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

    public static UserEntity IsValid(String name, String password) {
        UserEntity user = getByName(name);
        return user != null
                && user.getPassword().equals(password)
                && user.isDeleted == 0
                && !user.isBlocked ? user : null;
    }
}
