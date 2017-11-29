<jsp:include page="../base/top.jsp"></jsp:include>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Просмотр</h2>
<a class="btn btn-default pull-right" href="Bills/PrintDriver/${billdetails.id}" target="_blank">Печать</a>
<spring:message code="NotEmpty.billDetailsForm.number" var="NotEmptyNumber"/>
<spring:message code="NotEmpty.billDetailsForm.createDate" var="NotEmptyCreateDate"/>
<fmt:formatDate pattern="dd.MM.yyyy" value="${billdetails.startDate}" var="startDate" />
<fmt:formatDate pattern="dd.MM.yyyy" value="${billdetails.endDate}" var="endDate" />

<form:form class="form-horizontal" method="post"
           modelAttribute="billdetailsmodel" action="${actionUrl}">
    <form:hidden path="id"/>
    <div class="form-horizontal">
        <h4>Счет водителю</h4>
        <hr/>

        <div class="form-group">
            <label class="control-label col-md-2">Номер</label>
            <spring:bind path="number">
                <div class="col-md-10">
                    <form:input path="number" type="number"
                                class="form-control ${status.error ? 'input-validation-error' : ''}"
                                id="number"
                                data-val="true"
                                data-val-required="${NotEmptyNumber}"/>
                    <span class="control-label field-validation-error" data-valmsg-for="number"
                          data-valmsg-replace="true">
                               <form:errors path="number"/>
                            </span>
                </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">От</label>
            <spring:bind path="createDate">
                <div class="col-md-10">
                    <div class="input-group date" data-provide="">
                        <form:input path="createDate" type="text"
                                    class="form-control ${status.error ? 'input-validation-error' : ''}"
                                    id="createDate"
                                    data-val="true"
                                    data-val-required="${NotEmptyCreateDate}"/>
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                    <span class="control-label field-validation-error" data-valmsg-for="createDate"
                          data-valmsg-replace="true">
                               <form:errors path="createDate"/>
                            </span>
                </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Период</label>
            <div class="col-md-10">
                <input value="${startDate}" class="form-control" disabled/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">По</label>
            <div class="col-md-10">
                <input value="${endDate}" class="form-control" disabled/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Принципал</label>
            <div class="col-md-10">
                <input value="${billdetails.driverName}" class="form-control" disabled/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Вид оплаты</label>
            <div class="col-md-10">
                <input value="${billdetails.typeCash}" class="form-control" disabled/>
            </div>
        </div>

        <h4 class="">Заказы по счету:</h4>
        <table class="table table-hover header-fixed">
            <thead>
            <tr>
                <th>
                    №
                </th>
                <th>
                    Дата/время
                </th>
                <th>
                    Заказ для
                </th>
                <th>
                    Кол-во пассажиров
                </th>
                <th>
                    Тариф/заказ
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${billdetails.details}" var="item">
                <tr class="onclick" onclick="document.location='Orders/Edit/${item.idDoc}'">
                    <td>
                            ${item.number}
                    </td>
                    <td>
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${item.dateTime}" />
                    </td>
                    <td>
                            ${item.customer}
                    </td>
                    <td>
                            ${item.nums}
                    </td>
                    <td>
                        <fmt:formatNumber type="currency" value="${item.taxa}" />
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <button type="submit" class="btn btn-primary" id="saveBtn">
                    Сохранить
                </button>
            </div>
        </div>
    </div>
</form:form>
<div>
    <a href="Bills/ListDriver">Назад к списку</a>
</div>

<jsp:include page="../base/footer.jsp"></jsp:include>