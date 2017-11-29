package ru.craftautoweb.entities;

import org.hibernate.Session;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 27.11.2016.
 */
@Entity
@Table(name = "TypesAgency")
public class TypesAgencyEntity {
    private Integer id;
    private String type;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Type", nullable = false, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypesAgencyEntity that = (TypesAgencyEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public static Map<Integer, String> GetMapTypesAgency() {
        // Получаем объект сесси для работы с базой
        Session session = HibernateUtil.getSession();

        // формируем запрос
        String sql = "from TypesAgencyEntity order by type";

        // и выполняем его
        List<TypesAgencyEntity> list = session.createQuery(sql).
                list();
        session.close();

        Map<Integer, String> mapList = new LinkedHashMap<Integer, String>();
        for (TypesAgencyEntity item: list) {
            mapList.put(item.id, item.type);
        }

        return mapList;
    }
}
