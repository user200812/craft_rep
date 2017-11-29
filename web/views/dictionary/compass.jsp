<jsp:include page="../base/top.jsp"></jsp:include>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>${TypeOp}</h2>
<spring:message code="NotEmpty.compassForm.name" var="NotEmptyname"/>

<form:form class="form-horizontal" method="post"
           modelAttribute="compassForm" action="Directories/${actionUrl}">

    <div class="form-horizontal">
        <h4>${dicType}</h4>
        <hr/>
        <form:hidden path="id"/>

        <div class="form-group">
            <label class="control-label col-md-2">Наименование</label>
            <spring:bind path="name">
                <div class="col-md-10">
                    <form:input path="name" type="text"
                                class="form-control ${status.error ? 'input-validation-error' : ''}"
                                id="name"
                                data-val="true"
                                data-val-required="${NotEmptyname}"/>
                    <span class="control-label field-validation-error" data-valmsg-for="name"
                          data-valmsg-replace="true">
                   <form:errors path="name"/>
                </span>
                </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Комментарий</label>
            <div class="col-md-10">
                <form:input path="comment" type="text" class="form-control"
                            id="comment"/>
                <form:errors path="comment" class="control-label text-danger"/>
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
    <a href="Directories/Compass" class="btn">Назад к списку</a>
</div>


<jsp:include page="../base/footer.jsp"></jsp:include>
