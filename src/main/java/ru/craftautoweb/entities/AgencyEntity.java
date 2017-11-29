package ru.craftautoweb.entities;

import org.hibernate.Session;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.util.StringUtils;
import ru.craftautoweb.utils.HibernateUtil;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ru.craftautoweb.utils.CraftUtils.tdcell;

/**
 * Created by Администратор on 21.11.2016.
 */
@Entity
@Table(name = "Agency")
public class AgencyEntity extends BaseEntity{
    private String juristicPerson;
    private String contract;
    private String telephone;
    private String fax;
    private String email;
    private String contactPerson;
    private String contactPersonCellular;
    private String director;
    private String directorCellular;
    private String address;
    private Integer type = 0;
    private Double reduction = Double.valueOf(0);
    private String password;

    @Id
    @Column(name = "id", nullable = false)
    /*
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agency_seq")
    @SequenceGenerator(name = "agency_seq",
            sequenceName = "agency_seq",
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

    @Basic
    @Column(name = "JuristicPerson", nullable = true, length = 256)
    public String getJuristicPerson() {
        return juristicPerson;
    }

    public void setJuristicPerson(String juristicPerson) {
        this.juristicPerson = juristicPerson;
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
    @Column(name = "Telephone", nullable = true, length = 256)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "Fax", nullable = true, length = 256)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
    @Column(name = "ContactPerson", nullable = true, length = 256)
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Basic
    @Column(name = "ContactPersonCellular", nullable = true, length = 256)
    public String getContactPersonCellular() {
        return contactPersonCellular;
    }

    public void setContactPersonCellular(String contactPersonCellular) {
        this.contactPersonCellular = contactPersonCellular;
    }

    @Basic
    @Column(name = "Director", nullable = true, length = 256)
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Basic
    @Column(name = "DirectorCellular", nullable = true, length = 256)
    public String getDirectorCellular() {
        return directorCellular;
    }

    public void setDirectorCellular(String directorCellular) {
        this.directorCellular = directorCellular;
    }

    @Basic
    @Column(name = "Type", nullable = false)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "Address", nullable = true, length = 256)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "Reduction", nullable = true, precision = 0)
    @NumberFormat(style=NumberFormat.Style.PERCENT)
    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }

    @Basic
    @Column(name = "Password", nullable = true, length = 256)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgencyEntity that = (AgencyEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        if (juristicPerson != null ? !juristicPerson.equals(that.juristicPerson) : that.juristicPerson != null)
            return false;
        if (contract != null ? !contract.equals(that.contract) : that.contract != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (contactPerson != null ? !contactPerson.equals(that.contactPerson) : that.contactPerson != null)
            return false;
        if (contactPersonCellular != null ? !contactPersonCellular.equals(that.contactPersonCellular) : that.contactPersonCellular != null)
            return false;
        if (director != null ? !director.equals(that.director) : that.director != null) return false;
        if (directorCellular != null ? !directorCellular.equals(that.directorCellular) : that.directorCellular != null)
            return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (reduction != null ? !reduction.equals(that.reduction) : that.reduction != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (juristicPerson != null ? juristicPerson.hashCode() : 0);
        result = 31 * result + (contract != null ? contract.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (contactPerson != null ? contactPerson.hashCode() : 0);
        result = 31 * result + (contactPersonCellular != null ? contactPersonCellular.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (directorCellular != null ? directorCellular.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (reduction != null ? reduction.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String GetRowHtml(String dictype, int newid) {
        String rows = "";
        String href = "";
        if (isDeleted != null && isDeleted == 0)
        {
            String newidclass = newid == id ? " class='newid' " : "";
            href = "Directories/EditAgency/"+ id;
            rows = rows + "<tr " + newidclass + " data-id=" + id + ">";
            rows = rows + tdcell("<a data-toggle='modal' data-target='#delete' class='glyphicon glyphicon-remove-circle delete-link' data-href='Directories/Delete" + dictype + "?id=" + id + "' title='Удалить' item-id=" + id + "></a>");
        }
        else
        {
            rows = rows + "<tr class=isDeleted title=Удален>";
            rows = rows + tdcell("");
        }
        rows = rows + tdcell(name, href);
        rows = rows + tdcell(juristicPerson, href);
        rows = rows + tdcell(contract, href);
        rows = rows + tdcell(telephone, href);
        rows = rows + tdcell(fax, href);
        rows = rows + tdcell(email, href);
        rows = rows + tdcell(contactPerson, href);
        rows = rows + tdcell(contactPersonCellular, href);
        rows = rows + tdcell(director, href);
        rows = rows + tdcell(directorCellular, href);
        rows = rows + tdcell(address, href);
        rows = rows + tdcell(comment, href);

        if(type == null) {
            rows = rows + tdcell("", href);
        }
        else if(type.equals(Integer.valueOf(0)))
            rows = rows + tdcell("Физическое лицо", href);
        else
            rows = rows + tdcell("Юридическое лицо", href);

        rows = rows + tdcell(String.format( "%.0f", reduction*100), href);
        rows = rows + "</tr>";

        return rows;
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
            AgencyEntity updatedItem = getById(id);
            updatedItem.name = name;
            updatedItem.comment = comment;
            updatedItem.isDeleted = 0;
            updatedItem.juristicPerson = juristicPerson;
            updatedItem.contract = contract;
            updatedItem.telephone = telephone;
            updatedItem.fax = fax;
            updatedItem.email = email;
            updatedItem.contactPerson = contactPerson;
            updatedItem.contactPersonCellular = contactPersonCellular;
            updatedItem.director = director;
            updatedItem.directorCellular = directorCellular;
            updatedItem.address = address;
            updatedItem.reduction = reduction;
            // Сохраняем в БД
            session.update(updatedItem);
        }
        session.getTransaction().commit();
        session.close();

        return id;
    }

    public static AgencyEntity getById(int id) {
        // Получаем объект сесси для работы с базой
        Session session = HibernateUtil.getSession();

        // формируем запрос
        String sql = "from AgencyEntity where id=:id";

        // и выполняем его
        List<AgencyEntity> list = session.createQuery(sql).
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
        BaseEntity item = (AgencyEntity)session.get(AgencyEntity.class, new Integer(id));
        if(item != null) {
            item.UpdateForDelete(session);
        }
        session.close();
    }
    public static Map<Integer, String> GetMapAgencies(Integer id) {
        // Получаем объект сесси для работы с базой
        Session session = HibernateUtil.getSession();

        // формируем запрос
        String sql = "from AgencyEntity where isDeleted=0 or id=:id order by name";

        // и выполняем его
        List<AgencyEntity> list = session.createQuery(sql).
                setParameter("id", id).
                list();
        session.close();

        Map<Integer, String> mapList = new LinkedHashMap<Integer, String>();
        for (AgencyEntity item: list) {
            mapList.put(item.id, item.name.trim());
        }

        return mapList;
    }

    public static boolean isValidTypeCash(Integer idAgency, Integer idTypeCash) {
        if(idAgency != null) {
            AgencyEntity a = AgencyEntity.getById(idAgency);
            if (a != null)
            {
                // Тип оплаты на р/счет и договор пустой, то ошибка
                if (idTypeCash != null && idTypeCash.equals(1) && !StringUtils.hasText(a.contract))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
