<jsp:include page="../base/top.jsp"></jsp:include>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>${TypeOp}</h2>
<spring:message code="NotEmpty.orderForm.number" var="NotEmptynumber"/>
<spring:message code="Number.orderForm.number" var="NumberIsNumber"/>
<spring:message code="NotEmpty.orderForm.daterelease" var="NotEmptydaterelease"/>
<spring:message code="NotEmpty.orderForm.date" var="NotEmptydate"/>
<spring:message code="NotEmpty.orderForm.time" var="NotEmptytime"/>
<spring:message code="NotEmpty.orderForm.compass" var="NotEmptyCompass"/>
<spring:message code="NotEmpty.orderForm.customer" var="NotEmptyCustomer"/>
<spring:message code="NotEmpty.orderForm.nums" var="NotEmptyNums"/>
<spring:message code="NotEmpty.orderForm.telephones" var="NotEmptyTelephones"/>
<spring:message code="NotEmpty.orderForm.route" var="NotEmptyRoute"/>
<spring:message code="NotEmpty.orderForm.table" var="NotEmptyTable"/>
<spring:message code="NotEmpty.orderForm.auto" var="NotEmptyAuto"/>
<spring:message code="NotEmpty.orderForm.taxa" var="NotEmptyTaxa"/>
<spring:message code="NotEmpty.orderForm.dateNotification" var="NotEmptyDateNotification"/>
<spring:message code="NotEmpty.orderForm.typeCash" var="NotEmptyTypeCash"/>
<spring:message code="Wrong.orderForm.typeCash" var="WrongTypeCash"/>
<spring:message code="NotEmpty.orderForm.agency" var="NotEmptyAgency"/>
<c:if test="${actionUrl ne 'Create'}" var="validateNumber"/>

<form:form class="form-horizontal" method="post"
           modelAttribute="orderForm" action="Orders/${actionUrl}">

    <form:hidden path="id"/>

    <div class="form-horizontal">
        <div style="height: 25px">
            <h4 class="pull-left">Заказ</h4>
            <div class="pull-right">
                <ul class="nav nav-pills ">
                    <li class="active"><a data-toggle="pill" href="#mainTab">Основная</a></li>
                    <li><a data-toggle="pill" href="#addTab">Уведомление</a></li>
                </ul>
            </div>
        </div>
        <hr/>

        <div class="tab-content">
            <div id="mainTab" class="tab-pane fade in active">
                <div class="form-group">
                    <label class="control-label col-md-2">Номер</label>
                    <spring:bind path="number">
                        <div class="col-md-10">
                            <form:input path="number" type="number"
                                        class="form-control ${status.error ? 'input-validation-error' : ''}"
                                        id="number"
                                        data-val="${validateNumber}"
                                        data-val-required="${NotEmptynumber}"/>
                            <span class="control-label field-validation-error" data-valmsg-for="number"
                                  data-valmsg-replace="true">
                               <form:errors path="number"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Заказ от</label>
                    <spring:bind path="dateRelease">
                        <div class="col-md-10">
                            <div class="input-group date" data-provide="">
                                <form:input path="dateRelease" type="text"
                                            class="form-control ${status.error ? 'input-validation-error' : ''}"
                                            id="dateRelease"
                                            data-val="true"
                                            data-val-required="${NotEmptydate}"/>
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </div>
                            </div>
                            <span class="control-label field-validation-error" data-valmsg-for="dateRelease"
                                  data-valmsg-replace="true">
                               <form:errors path="dateRelease"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Дата заказа</label>
                    <spring:bind path="date">
                        <div class="col-md-10">
                            <div class="input-group date" data-provide="">
                                <form:input path="date" type="text"
                                            class="form-control ${status.error ? 'input-validation-error' : ''}"
                                            id="date"
                                            data-val="true"
                                            data-val-required="${NotEmptydate}"/>
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </div>
                            </div>
                            <span class="control-label field-validation-error" data-valmsg-for="date"
                                  data-valmsg-replace="true">
                               <form:errors path="date"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Время подачи</label>
                    <spring:bind path="time">
                        <div class="col-md-10">
                            <form:input path="time" type="text"
                                        class="form-control clockpicker ${status.error ? 'input-validation-error' : ''}"
                                        id="time"
                                        data-val="true"
                                        data-val-required="${NotEmptytime}"/>
                            <span class="control-label field-validation-error" data-valmsg-for="time"
                                  data-valmsg-replace="true">
                               <form:errors path="time"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Компасс</label>
                    <spring:bind path="compass">
                        <div class="col-md-10">
                            <form:input path="compass" type="text"
                                        class="form-control typeahead ${status.error ? 'input-validation-error' : ''}"
                                        id="compass"
                                        data-val="true"
                                        data-val-required="${NotEmptyCompass}"/>
                            <span class="control-label field-validation-error" data-valmsg-for="compass"
                                  data-valmsg-replace="true">
                               <form:errors path="compass"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Заказ для</label>
                    <spring:bind path="customer">
                        <div class="col-md-10">
                            <form:input path="customer" type="text"
                                        class="form-control ${status.error ? 'input-validation-error' : ''}"
                                        id="customer"
                                        data-val="true"
                                        data-val-required="${NotEmptyCustomer}"/>
                            <span class="control-label field-validation-error" data-valmsg-for="customer"
                                  data-valmsg-replace="true">
                               <form:errors path="customer"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Кол-во пассажиров</label>
                    <spring:bind path="nums">
                        <div class="col-md-10">
                            <form:input path="nums" type="text"
                                        class="form-control ${status.error ? 'input-validation-error' : ''}"
                                        id="nums"
                                        data-val="true"
                                        data-val-required="${NotEmptyNums}"/>
                            <span class="control-label field-validation-error" data-valmsg-for="nums"
                                  data-valmsg-replace="true">
                               <form:errors path="nums"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Телефоны</label>
                    <spring:bind path="telephones">
                        <div class="col-md-10">
                            <form:input path="telephones" type="text"
                                        class="form-control ${status.error ? 'input-validation-error' : ''}"
                                        id="telephones"
                                        data-val="true"
                                        data-val-required="${NotEmptyTelephones}"/>
                            <span class="control-label field-validation-error" data-valmsg-for="telephones"
                                  data-valmsg-replace="true">
                               <form:errors path="telephones"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Маршрут</label>
                    <spring:bind path="route">
                        <div class="col-md-10">
                            <form:input path="route" type="text"
                                        class="form-control typeahead ${status.error ? 'input-validation-error' : ''}"
                                        id="route"
                                        data-val="true"
                                        data-val-required="${NotEmptyRoute}"/>
                            <span class="control-label field-validation-error" data-valmsg-for="route"
                                  data-valmsg-replace="true">
                               <form:errors path="route"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Табличка для встречи</label>
                    <spring:bind path="table">
                        <div class="col-md-10">
                            <form:input path="table" type="text"
                                        class="form-control typeahead ${status.error ? 'input-validation-error' : ''}"
                                        id="table"
                                        data-val="true"
                                        data-val-required="${NotEmptyTable}"/>
                            <span class="control-label field-validation-error" data-valmsg-for="table"
                                  data-valmsg-replace="true">
                               <form:errors path="table"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Тариф</label>
                    <spring:bind path="taxa">
                        <div class="col-md-10">
                            <form:input path="taxa" type="money"
                                        class="form-control ${status.error ? 'input-validation-error' : ''}"
                                        id="taxa"
                                        data-val="true"
                                        data-val-required="${NotEmptyTaxa}"/>
                            <span class="control-label field-validation-error" data-valmsg-for="taxa"
                                  data-valmsg-replace="true">
                               <form:errors path="taxa"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Класс авто</label>
                    <spring:bind path="auto">
                        <div class="col-md-10">
                            <form:input path="auto" type="text"
                                        class="form-control typeahead ${status.error ? 'input-validation-error' : ''}"
                                        id="auto"
                                        data-val="true"
                                        data-val-required="${NotEmptyAuto}"/>
                            <span class="control-label field-validation-error" data-valmsg-for="auto"
                                  data-valmsg-replace="true">
                               <form:errors path="auto"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Принципал</label>
                    <spring:bind path="driver">
                        <div class="col-md-10">
                            <form:select path="driver" class="form-control">
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
                    <label class="control-label col-md-2">Заказчик</label>
                    <spring:bind path="agency">
                        <div class="col-md-10">
                            <form:select path="agency"
                                         class="form-control ${status.error ? 'input-validation-error' : ''}"
                                         data-val="true"
                                         data-val-required="${NotEmptyAgency}">
                                <form:option value="" label="---" />
                                <form:options items="${agencies}"/>
                            </form:select>
                            <span class="control-label field-validation-error" data-valmsg-for="agency"
                                  data-valmsg-replace="true">
                               <form:errors path="agency"/>
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
                                         class="form-control ${status.error ? 'input-validation-error' : ''}"
                                         data-val-remote="${WrongTypeCash}"
                                         data-val-remote-additionalfields="*.agency"
                                         data-val-remote-url="Validation/isValidTypeCash"
                                         data-val="true"
                                         data-val-required="${NotEmptyTypeCash}">
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
                    <label class="control-label col-md-2">Результат</label>
                    <div class="col-md-10">
                        <input class="form-control" disabled value="${result}"/>
                    </div>
                </div>
                <div class="form-group" ${user ? 'style=display:none' : 'style=margin-top:0px;margin-bottom:15px'}>
                    <div class="col-md-2"></div>
                    <div class="col-md-10">
                        <label class="text-nowrap">${user}</label>
                    </div>
                </div>
            </div>


            <div id="addTab" class="tab-pane fade">
                <div class="form-group">
                    <label class="control-label col-md-2">Дата уведомления</label>
                    <spring:bind path="dateNotification">
                        <div class="col-md-10">
                            <div class="input-group date" style="max-width: 280px; margin-bottom: 12px" data-provide="">
                                <form:input path="dateNotification" type="text"
                                            class="form-control ${status.error ? 'input-validation-error' : ''}"
                                            id="dateNotification"
                                            data-val="true"
                                            data-val-required="${NotEmptyDateNotification}"/>
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </div>
                            </div>
                            <span class="control-label field-validation-error" data-valmsg-for="dateNotification"
                                  data-valmsg-replace="true">
                               <form:errors path="dateNotification"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Номер</label>
                    <spring:bind path="numberNotification">
                        <div class="col-md-10">
                            <form:input path="numberNotification" type="text"
                                        class="form-control ${status.error ? 'input-validation-error' : ''}"
                                        id="numberNotification"/>
                            <span class="control-label field-validation-error" data-valmsg-for="numberNotification"
                                  data-valmsg-replace="true">
                               <form:errors path="numberNotification"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Уведомление</label>
                    <spring:bind path="notification">
                        <div class="col-md-10">
                            <form:textarea path="notification" type="text"
                                        class="form-control ${status.error ? 'input-validation-error' : ''}"
                                        id="notification"/>
                            <span class="control-label field-validation-error" data-valmsg-for="notification"
                                  data-valmsg-replace="true">
                               <form:errors path="notification"/>
                            </span>
                        </div>
                    </spring:bind>
                </div>

            </div>
        </div>


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
    <a href="Orders" class="btn">Назад к списку</a>
</div>


<script type="text/javascript" src="Scripts/typeahead.bundle.min.js"></script>
<script type="text/javascript" src="Scripts/editOrder.js"></script>
<jsp:include page="../base/footer.jsp"></jsp:include>
