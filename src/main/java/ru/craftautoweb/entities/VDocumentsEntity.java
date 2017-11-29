package ru.craftautoweb.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static ru.craftautoweb.utils.CraftUtils.tdcell;

/**
 * Created by Администратор on 22.11.2016.
 */
@Entity
@Table(name = "V_Documents")
public class VDocumentsEntity {
    private Integer idDoc;
    private Integer number;
    private Timestamp datetime;
    private String nums;
    private String customer;
    private Double taxa;
    private Integer owner;
    private Integer typeCash;
    private String typeCashString;
    private String telephones;
    private String auto;
    private Integer idAgency;
    private String agency;
    private Integer agencyType;
    private String result;
    private Integer idResult;
    private Integer idDriver;
    private String driver;
    private String route;
    private String table;
    private String compass;
    private Timestamp dateRelease;
    private String warning;

    @Basic
    @Id
    @Column(name = "idDoc", nullable = false)
    public Integer getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }

    @Basic
    //@Column(name = "Numb", nullable = false)
    @Column(name = "Number", nullable = false)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "Datetime", nullable = true)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
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
    @Column(name = "Customer", nullable = true, length = 50)
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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
    @Column(name = "Owner", nullable = true)
    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
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
    @Column(name = "TypeCashString", nullable = true, length = 50)
    public String getTypeCashString() {
        return typeCashString;
    }

    public void setTypeCashString(String typeCashString) {
        this.typeCashString = typeCashString;
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
    @Column(name = "Auto", nullable = true, length = 50)
    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    @Basic
    @Column(name = "idAgency", nullable = true)
    public Integer getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(Integer idAgency) {
        this.idAgency = idAgency;
    }

    @Basic
    @Column(name = "Agency", nullable = false, length = 50)
    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    @Basic
    @Column(name = "AgencyType", nullable = true)
    public Integer getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(Integer agencyType) {
        this.agencyType = agencyType;
    }

    @Basic
    @Column(name = "Result", nullable = true, length = 50)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Basic
    @Column(name = "idResult", nullable = true)
    public Integer getIdResult() {
        return idResult;
    }

    public void setIdResult(Integer idResult) {
        this.idResult = idResult;
    }

    @Basic
    @Column(name = "idDriver", nullable = true)
    public Integer getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(Integer idDriver) {
        this.idDriver = idDriver;
    }

    @Basic
    @Column(name = "Driver", nullable = true, length = 564)
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
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
    //@Column(name = "TableMeeting", nullable = true, length = 50)
    @Column(name = "\"Table\"", nullable = true, length = 50)
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Basic
    @Column(name = "Compass", nullable = true, length = 50)
    public String getCompass() {
        return compass;
    }

    public void setCompass(String compass) {
        this.compass = compass;
    }

    @Basic
    @Column(name = "DateRelease", nullable = true)
    public Timestamp getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(Timestamp dateRelease) {
        this.dateRelease = dateRelease;
    }

    @Basic
    @Column(name = "Warning", nullable = false, length = 4)
    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VDocumentsEntity that = (VDocumentsEntity) o;

        if (idDoc != null ? !idDoc.equals(that.idDoc) : that.idDoc != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (datetime != null ? !datetime.equals(that.datetime) : that.datetime != null) return false;
        if (nums != null ? !nums.equals(that.nums) : that.nums != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (taxa != null ? !taxa.equals(that.taxa) : that.taxa != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (typeCash != null ? !typeCash.equals(that.typeCash) : that.typeCash != null) return false;
        if (typeCashString != null ? !typeCashString.equals(that.typeCashString) : that.typeCashString != null)
            return false;
        if (telephones != null ? !telephones.equals(that.telephones) : that.telephones != null) return false;
        if (auto != null ? !auto.equals(that.auto) : that.auto != null) return false;
        if (idAgency != null ? !idAgency.equals(that.idAgency) : that.idAgency != null) return false;
        if (agency != null ? !agency.equals(that.agency) : that.agency != null) return false;
        if (agencyType != null ? !agencyType.equals(that.agencyType) : that.agencyType != null) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;
        if (idResult != null ? !idResult.equals(that.idResult) : that.idResult != null) return false;
        if (idDriver != null ? !idDriver.equals(that.idDriver) : that.idDriver != null) return false;
        if (driver != null ? !driver.equals(that.driver) : that.driver != null) return false;
        if (route != null ? !route.equals(that.route) : that.route != null) return false;
        if (table != null ? !table.equals(that.table) : that.table != null) return false;
        if (compass != null ? !compass.equals(that.compass) : that.compass != null) return false;
        if (dateRelease != null ? !dateRelease.equals(that.dateRelease) : that.dateRelease != null) return false;
        if (warning != null ? !warning.equals(that.warning) : that.warning != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = idDoc != null ? idDoc.hashCode() : 0;
        result1 = 31 * result1 + (number != null ? number.hashCode() : 0);
        result1 = 31 * result1 + (datetime != null ? datetime.hashCode() : 0);
        result1 = 31 * result1 + (nums != null ? nums.hashCode() : 0);
        result1 = 31 * result1 + (customer != null ? customer.hashCode() : 0);
        result1 = 31 * result1 + (taxa != null ? taxa.hashCode() : 0);
        result1 = 31 * result1 + (owner != null ? owner.hashCode() : 0);
        result1 = 31 * result1 + (typeCash != null ? typeCash.hashCode() : 0);
        result1 = 31 * result1 + (typeCashString != null ? typeCashString.hashCode() : 0);
        result1 = 31 * result1 + (telephones != null ? telephones.hashCode() : 0);
        result1 = 31 * result1 + (auto != null ? auto.hashCode() : 0);
        result1 = 31 * result1 + (idAgency != null ? idAgency.hashCode() : 0);
        result1 = 31 * result1 + (agency != null ? agency.hashCode() : 0);
        result1 = 31 * result1 + (agencyType != null ? agencyType.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (idResult != null ? idResult.hashCode() : 0);
        result1 = 31 * result1 + (idDriver != null ? idDriver.hashCode() : 0);
        result1 = 31 * result1 + (driver != null ? driver.hashCode() : 0);
        result1 = 31 * result1 + (route != null ? route.hashCode() : 0);
        result1 = 31 * result1 + (table != null ? table.hashCode() : 0);
        result1 = 31 * result1 + (compass != null ? compass.hashCode() : 0);
        result1 = 31 * result1 + (dateRelease != null ? dateRelease.hashCode() : 0);
        result1 = 31 * result1 + (warning != null ? warning.hashCode() : 0);
        return result1;
    }

    public String GetRowHtml(int newid) {
        String rows = "";
        String newidclass = newid == idDoc ? " newid" : "";
        String newidname = newid == idDoc ? " name=newid" : "";
        String href = "Orders/Edit/" + idDoc;

        rows = rows + "<tr" + newidname + " class='" + warning + newidclass + "' data-id=" + idDoc + ">";
        rows = rows + tdcell("<input onclick=checkbx(this) type=\"checkbox\" title=\"Выделить\" id=" + idDoc + " />");
        rows = rows + tdcell(number.toString(), href);
        rows = rows + tdcell(datetime, href, (driver == null ? "subwarning" : ""));
        rows = rows + tdcell(compass, href);
        rows = rows + tdcell(auto, href);
        rows = rows + tdcell(customer, href);
        rows = rows + tdcell(nums, href);
        rows = rows + tdcell(telephones, href);
        rows = rows + tdcell(route, href);
        rows = rows + tdcell(table, href);
        rows = rows + tdcell(taxa, href, taxa == null ? "subwarning" : null);
        rows = rows + tdcell(driver, href, driver == null ? "subwarning" : null);
        rows = rows + tdcell(typeCashString, href, null);
        rows = rows + tdcell(agency, href);
        rows = rows + tdcell(result, href, result == null ? "subwarning" : null);
        rows = rows + "</tr>";

        return rows;
    }
}
