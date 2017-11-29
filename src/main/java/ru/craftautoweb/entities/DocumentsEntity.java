package ru.craftautoweb.entities;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.format.annotation.DateTimeFormat;
import ru.craftautoweb.utils.CraftUtils;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;
import javax.print.Doc;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static ru.craftautoweb.entities.UserEntity.GetIdCurrentUser;

/**
 * Created by User on 24.11.2016.
 */
@Entity
@Table(name = "Documents")
public class DocumentsEntity {
    private Integer id;
    private Integer number;
    private Timestamp date;
    private Timestamp time;
    private String customer;
    private String nums;
    private Double taxa;
    private Integer typeCash;
    private String telephones;
    private String route;
    private String table;
    private String auto;
    private Timestamp dateRelease;
    private String notification;
    private String numberNotification;
    private Timestamp dateNotification;
    private String compass;
    private Integer driver;
    private Integer agency;
    private Integer result;
    private Integer idowner;
    private Integer resultcre;

    public DocumentsEntity() {
        dateRelease = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
/*
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Documents_seq")
    @SequenceGenerator(name = "Documents_seq",
            sequenceName = "Documents_seq",
            allocationSize = 1)
*/
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
    @Column(name = "\"Date\"", nullable = true)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "\"Time\"", nullable = true)
    @DateTimeFormat(pattern = "HH:mm")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

/*
    @Basic
    @Column(name = "DateTime", nullable = true)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
        this.time = date;
    }

    @Transient
    // @Column(name = "\"Time\"", nullable = true)
    @DateTimeFormat(pattern = "HH:mm")
    public Timestamp getTime() {
        return date;
    }

    public void setTime(Timestamp time) {
        Date t1 = new Date(time.getTime());
        Calendar fulldate = Calendar.getInstance();
        fulldate.setTime(date);
        fulldate.set(Calendar.HOUR, t1.getHours());
        fulldate.set(Calendar.MINUTE, t1.getMinutes());
        date = new Timestamp(fulldate.getTime().getTime());

        this.time = time;
    }
*/

    @Basic
    @Column(name = "Customer", nullable = true, length = 50)
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Basic
    @Column(name = "Nums", nullable = true, length = 50)
    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    @Basic
    @Column(name = "Taxa", nullable = true)
    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
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
    @Column(name = "Telephones", nullable = true, length = 50)
    public String getTelephones() {
        return telephones;
    }

    public void setTelephones(String telephones) {
        this.telephones = telephones;
    }

    @Basic
    @Column(name = "Route", nullable = true, length = 1024)
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Basic
    //@Column(name = "Table", nullable = true, length = 50)
    @Column(name = "\"Table\"", nullable = true, length = 50)
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Basic
    @Column(name = "Auto", nullable = true, length = 50)
    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    @Basic
    @Column(name = "DateRelease", nullable = true)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public Timestamp getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(Timestamp dateRelease) {
        this.dateRelease = dateRelease;
    }

    @Basic
    @Column(name = "Notification", nullable = true, length = -1)
    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    @Basic
    @Column(name = "NumberNotification", nullable = true, length = 50)
    public String getNumberNotification() {
        return numberNotification;
    }

    public void setNumberNotification(String numberNotification) {
        this.numberNotification = numberNotification;
    }

    @Basic
    @Column(name = "DateNotification", nullable = true)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public Timestamp getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Timestamp dateNotification) {
        this.dateNotification = dateNotification;
    }

    @Basic
    @Column(name = "Compass", nullable = true, length = 50)
    public String getCompass() {
        return compass;
    }

    public void setCompass(String compass) {
        this.compass = compass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentsEntity that = (DocumentsEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (nums != null ? !nums.equals(that.nums) : that.nums != null) return false;
        if (taxa != null ? !taxa.equals(that.taxa) : that.taxa != null) return false;
        if (typeCash != null ? !typeCash.equals(that.typeCash) : that.typeCash != null) return false;
        if (telephones != null ? !telephones.equals(that.telephones) : that.telephones != null) return false;
        if (route != null ? !route.equals(that.route) : that.route != null) return false;
        if (table != null ? !table.equals(that.table) : that.table != null) return false;
        if (auto != null ? !auto.equals(that.auto) : that.auto != null) return false;
        if (dateRelease != null ? !dateRelease.equals(that.dateRelease) : that.dateRelease != null) return false;
        if (notification != null ? !notification.equals(that.notification) : that.notification != null) return false;
        if (numberNotification != null ? !numberNotification.equals(that.numberNotification) : that.numberNotification != null)
            return false;
        if (dateNotification != null ? !dateNotification.equals(that.dateNotification) : that.dateNotification != null)
            return false;
        if (compass != null ? !compass.equals(that.compass) : that.compass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (nums != null ? nums.hashCode() : 0);
        result = 31 * result + (taxa != null ? taxa.hashCode() : 0);
        result = 31 * result + (typeCash != null ? typeCash.hashCode() : 0);
        result = 31 * result + (telephones != null ? telephones.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + (table != null ? table.hashCode() : 0);
        result = 31 * result + (auto != null ? auto.hashCode() : 0);
        result = 31 * result + (dateRelease != null ? dateRelease.hashCode() : 0);
        result = 31 * result + (notification != null ? notification.hashCode() : 0);
        result = 31 * result + (numberNotification != null ? numberNotification.hashCode() : 0);
        result = 31 * result + (dateNotification != null ? dateNotification.hashCode() : 0);
        result = 31 * result + (compass != null ? compass.hashCode() : 0);
        return result;
    }

    public static DocumentsEntity getById(int id) {
        // Получаем объект сессии для работы с базой
        Session session = HibernateUtil.getSession();
        DocumentsEntity item = null;
        // Возвращаем элемент
        if (id > 0) {
            item =
                    (DocumentsEntity) session.get(
                            DocumentsEntity.class,
                            new Integer(id));
        }
        session.close();
        return item;
    }

    public Integer Save() {
        // Получаем объект сесси для работы с базой
        // и открываем транзакцию
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            idowner = GetIdCurrentUser();
            if (id == null) {
                // Если номер не задан формируем из базы
                if (number == null
                        || number.equals(Integer.valueOf(0))) {
                    Criteria criteria = session
                            .createCriteria(DocumentsEntity.class)
                            .setProjection(Projections.max("number"));
                    number = (Integer) criteria.uniqueResult() + 1;
                }
                // Сохраняем в БД
                id = (Integer) session.save(this);
            } else {
                DocumentsEntity documentsEntity = (DocumentsEntity) session.get(DocumentsEntity.class, id);
                if (documentsEntity != null) {
                    documentsEntity.SaveResultFromModel(this);
                    // Сохраняем в БД
                    session.update(documentsEntity);
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
            id = null;
        }
        finally {
            session.close();
        }

        return id;
    }

    private void SaveResultFromModel(DocumentsEntity model) {
        number = model.number;
        date = model.date;
        time = model.time;
        customer = model.customer;
        nums = model.nums;
        taxa = model.taxa;
        typeCash = model.typeCash;
        telephones = model.telephones;
        route = model.route;
        table = model.table;
        auto = model.auto;
        dateRelease = model.dateRelease;
        notification = model.notification;
        numberNotification = model.numberNotification;
        dateNotification = model.dateNotification;
        compass = model.compass;
        driver = model.driver;
        agency = model.agency;
        idowner = UserEntity.GetIdCurrentUser();
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
    @Column(name = "Agency", nullable = true)
    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    @Basic
    @Column(name = "Owner", nullable = true)
    public Integer getIdowner() {
        return idowner;
    }

    public void setIdowner(Integer idowner) {
        this.idowner = idowner;
    }

    @Basic
    @Column(name = "Result", nullable = true)
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Basic
    @Column(name = "ResultCre", nullable = true)
    public Integer getResultcre() {
        return resultcre;
    }

    public void setResultcre(Integer resultcre) {
        this.resultcre = resultcre;
    }

    public String Result() {
        // Получаем объект сессии для работы с базой
        Session session = HibernateUtil.getSession();

        String str = "";

        // Получаем текущий документ
        DocumentsEntity item = (DocumentsEntity)session.get(DocumentsEntity.class, id);
        if (item != null) {

            // Получаем результат
            ResultsEntity resultEntity = null;
            if (item.result != null)
                resultEntity = (ResultsEntity) session.get(ResultsEntity.class, item.result);

            if (resultEntity != null) {
                str = resultEntity.getResult();
            }
        }
        session.close();
        return str;
    }

    public String UserChanged() {
        // Получаем объект сессии для работы с базой
        Session session = HibernateUtil.getSession();

        String str = "";

        // Получаем текущий документ
        DocumentsEntity item = (DocumentsEntity)session.get(DocumentsEntity.class, id);
        if (item != null) {
            // Получаем владельца
            UserEntity user = null;
            if (item.idowner != null)
                user = (UserEntity) session.get(UserEntity.class, item.idowner);

            if (user != null) {
                str = "Создан/изменен: " + user.getName();

                // Получаем кто установил результат
                UserEntity userResult = null;
                if (item.resultcre != null)
                    userResult = (UserEntity) session.get(UserEntity.class, item.resultcre);

                if (userResult != null) {
                    str += " (" + userResult.getName() + ")";
                }
            }
        }
        session.close();
        return str;
    }
}
