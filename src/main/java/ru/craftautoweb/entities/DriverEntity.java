package ru.craftautoweb.entities;

import org.hibernate.Session;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.NumberFormat;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ru.craftautoweb.utils.CraftUtils.tdcell;

/**
 * Created by Администратор on 21.11.2016.
 */
@Entity
@Table(name = "Driver")
public class DriverEntity extends BaseEntity{
    private String telephone;
    private String contract;
    private String car;
    private String conditioner;
    private String schedule;
    private String district;
    private String number;
    private String color;
    private String year;
    private Double commission;
    private String email;
    private String surname;
    private String patronymic;

    @Id
    @Column(name = "id", nullable = false)
/*
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_seq")
    @SequenceGenerator(name = "driver_seq",
            sequenceName = "driver_seq",
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
    @Column(name = "Telephone", nullable = true, length = 50)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
    @Column(name = "Contract", nullable = true, length = 256)
    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    @Basic
    @Column(name = "Car", nullable = true, length = 256)
    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    @Basic
    @Column(name = "Conditioner", nullable = true, length = 256)
    public String getConditioner() {
        return conditioner;
    }

    public void setConditioner(String conditioner) {
        this.conditioner = conditioner;
    }

    @Basic
    @Column(name = "Schedule", nullable = true, length = 256)
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Basic
    @Column(name = "District", nullable = true, length = 256)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    //@Column(name = "Numb", nullable = true, length = 256)
    @Column(name = "Number", nullable = true, length = 256)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "Color", nullable = true, length = 256)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "Year", nullable = true, length = 256)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "Commission", nullable = true, precision = 0)
    @NumberFormat(style=NumberFormat.Style.PERCENT)
    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 256)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Surname", nullable = true, length = 256)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "Patronymic", nullable = true, length = 256)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverEntity that = (DriverEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        if (contract != null ? !contract.equals(that.contract) : that.contract != null) return false;
        if (car != null ? !car.equals(that.car) : that.car != null) return false;
        if (conditioner != null ? !conditioner.equals(that.conditioner) : that.conditioner != null) return false;
        if (schedule != null ? !schedule.equals(that.schedule) : that.schedule != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (commission != null ? !commission.equals(that.commission) : that.commission != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (contract != null ? contract.hashCode() : 0);
        result = 31 * result + (car != null ? car.hashCode() : 0);
        result = 31 * result + (conditioner != null ? conditioner.hashCode() : 0);
        result = 31 * result + (schedule != null ? schedule.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (commission != null ? commission.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        return result;
    }
    @Override
    public String GetRowHtml(String dictype, int newid) {
        String rows = "";
        String href = "";
        if (isDeleted != null && isDeleted == 0)
        {
            String newidclass = newid == id ? " class='newid' " : "";
            href = "Directories/EditDriver/"+ id;
            rows = rows + "<tr " + newidclass + " data-id=" + id + ">";
            rows = rows + tdcell("<a data-toggle='modal' data-target='#delete' class='glyphicon glyphicon-remove-circle delete-link' data-href='Directories/Delete" + dictype + "?id=" + id + "' title='Удалить' item-id=" + id + "></a>");
        }
        else
        {
            rows = rows + "<tr class=isDeleted title=Удален>";
            rows = rows + tdcell("");
        }
        rows = rows + tdcell(FIO(), href);
        rows = rows + tdcell(contract, href);
        rows = rows + tdcell(car, href);
        rows = rows + tdcell(conditioner, href);
        rows = rows + tdcell(schedule, href);
        rows = rows + tdcell(district, href);
        rows = rows + tdcell(telephone, href);
        rows = rows + tdcell(number, href);
        rows = rows + tdcell(color, href);
        rows = rows + tdcell(year, href);
        rows = rows + tdcell(comment, href);
        rows = rows + tdcell(String.format( "%.0f", commission*100), href);
        rows = rows + tdcell(email, href);
        rows = rows + "</tr>";

        return rows;
    }

    public String FIO() {
        surname = surname == null ? "" : surname;
        name = name == null ? "" : name;
        patronymic = patronymic == null ? "" : patronymic;
        String fio = "";
        if (!surname.trim().isEmpty()) {
            fio += surname.trim() + " ";
        }
        if (!name.trim().isEmpty()) {
            fio += name.trim() + " ";
        }
        if (!patronymic.trim().isEmpty()) {
            fio += patronymic.trim();
        }

        return (surname.trim() + " " + name.trim() + " " + patronymic.trim()).trim();
    }

    public static Comparator<BaseEntity> ComparatorAsc
            = new Comparator<BaseEntity>() {
        public int compare(BaseEntity o1, BaseEntity o2) {
            return ((DriverEntity)o1).FIO().compareToIgnoreCase(((DriverEntity)o2).FIO());
        }
    };
    public static Comparator<BaseEntity> ComparatorDesc
            = new Comparator<BaseEntity>() {
        public int compare(BaseEntity o1, BaseEntity o2) {
            return ((DriverEntity)o2).FIO().compareToIgnoreCase(((DriverEntity)o1).FIO());
        }
    };
    public static DriverEntity getById(int id) {
        // Получаем объект сесси для работы с базой
        Session session = HibernateUtil.getSession();

        // формируем запрос
        String sql = "from DriverEntity where id=:id";

        // и выполняем его
        List<DriverEntity> list = session.createQuery(sql).
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
            DriverEntity updatedItem = getById(id);
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

    public static void Delete(int id) {
        // Получаем объект сесси для работы с базой
        // и открываем транзакцию
        Session session = HibernateUtil.getSession();

        // Обновляем запись
        DriverEntity item = (DriverEntity)session.get(DriverEntity.class, new Integer(id));
        if(item != null) {
            item.UpdateForDelete(session);
        }
        session.close();
    }
    public static Map<Integer, String> GetMapDrivers(Integer id) {
        // Получаем объект сесси для работы с базой
        Session session = HibernateUtil.getSession();

        // формируем запрос
        String sql = "from DriverEntity where isDeleted=0 or id=:id order by concat(surname, ' ', name, ' ', patronymic)";

        // и выполняем его
        List<DriverEntity> list = session.createQuery(sql).
                setParameter("id", id).
                list();
        session.close();
        //list.sort(DriverEntity.ComparatorAsc);

        Map<Integer, String> mapList = new LinkedHashMap<Integer, String>();
        for (DriverEntity item: list) {
            mapList.put(item.id, item.FIO());
        }

        return mapList;
    }
}
