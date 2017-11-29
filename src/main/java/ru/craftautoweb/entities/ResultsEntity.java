package ru.craftautoweb.entities;

import org.hibernate.Session;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;

/**
 * Created by Администратор on 22.11.2016.
 */
@Entity
@Table(name = "Results")
public class ResultsEntity {
    private Integer id;
    private String result;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Result", nullable = false, length = 50)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultsEntity that = (ResultsEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id != null ? id.hashCode() : 0;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}
