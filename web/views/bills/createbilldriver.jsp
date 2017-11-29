<jsp:include page="../base/top.jsp"></jsp:include>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>${Title}</h2>

<spring:message code="NotEmpty.createBillForm.startDate" var="NotEmptyStartDate"/>
<spring:message code="NotEmpty.createBillForm.endDate" var="NotEmptyEndDate"/>
<spring:message code="NotEmpty.createBillForm.typeCash" var="NotEmptyTypeCash"/>

<form:form class="form-horizontal" method="post"
           modelAttribute="bill" action="Bills/CreateBillDriver">
    <div class="form-horizontal" style="padding-top: 2em">
        <div class="form-group">
            <label class="control-label col-md-2">Период</label>
            <spring:bind path="startDate">
                <div class="col-md-10">
                    <div class="input-group date" data-provide="">
                        <form:input path="startDate" type="text"
                                    class="form-control ${status.error ? 'input-validation-error' : ''}"
                                    id="startDate"
                                    data-val="true"
                                    data-val-required="${NotEmptyStartDate}"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                    <span class="control-label field-validation-error" data-valmsg-for="startDate"
                          data-valmsg-replace="true">
                               <form:errors path="startDate"/>
                            </span>
                </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">По</label>
            <spring:bind path="endDate">
                <div class="col-md-10">
                    <div class="input-group date" data-provide="">
                        <form:input path="endDate" type="text"
                                    class="form-control ${status.error ? 'input-validation-error' : ''}"
                                    id="endDate"
                                    data-val="true"
                                    data-val-required="${NotEmptyEndDate}"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                    <span class="control-label field-validation-error" data-valmsg-for="endDate"
                          data-valmsg-replace="true">
                               <form:errors path="endDate"/>
                            </span>
                </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Принципал</label>
            <spring:bind path="driver">
                <div class="col-md-10">
                    <form:select path="driver"
                                 class="form-control ${status.error ? 'input-validation-error' : ''}"
                                 id="driver">
                        <form:option value="" label="---" />
                        <form:options items="${drivers}"/>
                    </form:select>
                    <span class="control-label field-validation-error" data-valmsg-for="driver"
                          data-valmsg-replace="true">
                               <form:errors path="driver"/>
                            </span>
                </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Оплата заказа</label>
            <spring:bind path="typeCash">
                <div class="col-md-10">
                    <form:select path="typeCash"
                                 id="typeCash"
                                 data-val="true"
                                 data-val-required="${NotEmptyTypeCash}"
                                 class="form-control ${status.error ? 'input-validation-error' : ''}">
                        <form:option value="" label="---" />
                        <form:options items="${typesCash}"/>
                    </form:select>
                    <span class="control-label field-validation-error" data-valmsg-for="typeCash"
                          data-valmsg-replace="true">
                               <form:errors path="typeCash"/>
                            </span>
                </div>
            </spring:bind>

        </div>

        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <button type="submit" class="btn btn-primary" id="createBtn">
                    Сформировать
                </button>
            </div>
        </div>
    </div>
</form:form>

<jsp:include page="../base/footer.jsp"></jsp:include>