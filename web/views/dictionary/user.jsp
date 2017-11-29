<jsp:include page="../base/top.jsp"></jsp:include>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>${TypeOp}</h2>

<form:form class="form-horizontal" method="post"
           modelAttribute="userForm" action="Directories/${actionUrl}">

    <spring:message code="Length.userForm.name" var="LengthuserFormname" />
    <spring:message code="NotEmpty.userForm.name" var="NotEmptyname" />
    <spring:message code="NotEmpty.userForm.password" var="NotEmptyuserFormpassword" />
    <spring:message code="Exists.userForm.name" var="ExistsuserFormname" />

    <div class="form-horizontal">
        <h4>${dicType}</h4>
        <hr/>
        <form:hidden path="id" novalidate="novalidate" />

        <div class="form-group">
            <label class="control-label col-md-2">Пользователь</label>
            <spring:bind path="name">
            <div class="col-md-10">
                <form:input path="name" type="text" class="form-control ${status.error ? 'input-validation-error' : ''}"
                            id="name"
                            data-val="true"
                            data-val-remote="${ExistsuserFormname}"
                            data-val-remote-additionalfields="*.name,*.id"
                            data-val-remote-url="Validation/IsUserExists"
                            data-val-required="${NotEmptyname}"
                            data-val-minlength="${LengthuserFormname}"
                            data-val-minlength-min="3"/>
                <span class="control-label field-validation-error" data-valmsg-for="name" data-valmsg-replace="true">
                   <form:errors path="name"/>
                </span>
            </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Пароль</label>
            <spring:bind path="password">
            <div class="col-md-10">
                <div class="input-group" style="max-width: 280px; margin-bottom: 12px" data-provide="">
                    <form:input path="password" type="password" class="form-control ${status.error ? 'input-validation-error' : ''}"
                                data-val="true"
                                data-val-required="${NotEmptyuserFormpassword}"
                                id="password"/>
                    <div class="input-group-addon" id="showPassword">
                        <span class="glyphicon glyphicon glyphicon-eye-close"></span>
                    </div>
                </div>
                <span class="control-label field-validation-error" data-valmsg-for="password" data-valmsg-replace="true">
                   <form:errors path="password"/>
                </span>
            </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">ФИО</label>
            <div class="col-md-10">
                <form:input path="fio" type="text" class="form-control"
                            id="fio"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Активен</label>
            <div class="col-md-10">
                <form:checkbox path="isBlocked" class="form-control"
                               id="isBlocked" data-toggle="toggle" data-on="Да" data-off="Нет"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Роль</label>
            <div class="col-md-10">
                <form:select path="role" class="form-control">
                    <form:options items="${roleList}"/>
                </form:select>
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
    <a href="Directories/Users" class="btn">Назад к списку</a>
</div>


<jsp:include page="../base/footer.jsp"></jsp:include>
