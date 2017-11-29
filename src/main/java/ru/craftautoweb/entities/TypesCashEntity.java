package ru.craftautoweb.entities;

import org.hibernate.Session;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Администратор on 28.11.2016.
 */
@Entity
@Table(name = "TypesCash")
public class TypesCashEntity {
    private Integer id;
    private String name;

    @Id
    @Column(name = "id", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypesCashEntity that = (TypesCashEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public static Map<Integer, String> GetMapTypesCash() {
        // Получаем объект сесси для работы с базой
        Session session = HibernateUtil.getSession();

        // формируем запрос
        String sql = "from TypesCashEntity order by name";

        // и выполняем его
        List<TypesCashEntity> list = session.createQuery(sql).
                list();
        session.close();

        Map<Integer, String> mapList = new LinkedHashMap<Integer, String>();
        for (TypesCashEntity item: list) {
            mapList.put(item.id, item.name);
        }

        return mapList;
    }
}
