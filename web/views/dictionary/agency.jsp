<jsp:include page="../base/top.jsp"></jsp:include>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>${TypeOp}</h2>
<spring:message code="NotEmpty.agencyForm.name" var="NotEmptyname" />

<form:form class="form-horizontal" method="post"
           modelAttribute="agencyForm" action="Directories/${actionUrl}">

    <div class="form-horizontal">
        <h4>${dicType}</h4>
        <hr/>
        <form:hidden path="id" />

        <div class="form-group">
            <label class = "control-label col-md-2">Наименование</label>
            <spring:bind path="name">
                <div class="col-md-10">
                    <form:input path="name" type="text" class="form-control ${status.error ? 'input-validation-error' : ''}"
                                id="name"
                                data-val="true"
                                data-val-required="${NotEmptyname}"/>
                    <span class="control-label field-validation-error" data-valmsg-for="name" data-valmsg-replace="true">
                   <form:errors path="name"/>
                </span>
                </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Комментарий</label>
            <div class="col-md-10">
                <form:input path="comment" type="text" class="form-control"
                            id="comment" />
                <form:errors path="comment" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Юр.лицо</label>
            <div class="col-md-10">
                <form:input path="" type="text" class="form-control"
                            id="juristicPerson" />
                <form:errors path="juristicPerson" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Договор</label>
            <div class="col-md-10">
                <form:input path="contract" type="text" class="form-control"
                            id="contract" />
                <form:errors path="contract" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Телефон</label>
            <div class="col-md-10">
                <form:input path="telephone" type="text" class="form-control"
                            id="telephone" />
                <form:errors path="telephone" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Факс</label>
            <div class="col-md-10">
                <form:input path="fax" type="text" class="form-control"
                            id="fax" />
                <form:errors path="fax" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Email</label>
            <div class="col-md-10">
                <form:input path="email" type="text" class="form-control"
                            id="email" />
                <form:errors path="email" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Контактное лицо</label>
            <div class="col-md-10">
                <form:input path="contactPerson" type="text" class="form-control"
                            id="contactPerson" />
                <form:errors path="contactPerson" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Мобильный телефон</label>
            <div class="col-md-10">
                <form:input path="contactPersonCellular" type="text" class="form-control"
                            id="contactPersonCellular" />
                <form:errors path="contactPersonCellular" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Директор</label>
            <div class="col-md-10">
                <form:input path="director" type="text" class="form-control"
                            id="director" />
                <form:errors path="director" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Мобильный телефон</label>
            <div class="col-md-10">
                <form:input path="directorCellular" type="text" class="form-control"
                            id="directorCellular" />
                <form:errors path="directorCellular" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Почтовый адрес</label>
            <div class="col-md-10">
                <form:input path="address" type="text" class="form-control"
                            id="address" />
                <form:errors path="address" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2">Тип</label>
            <div class="col-md-10">
                <form:select path="type" class="form-control">
                    <form:options items="${typeList}"/>
                </form:select>
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Скидка</label>
            <div class="col-md-10">
                <form:input path="reduction" type="text" class="form-control"
                            id="reduction" />
                <form:errors path="reduction" class="control-label text-danger" />
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
    <a href="Directories/Agencies" class="btn">Назад к списку</a>
</div>


<jsp:include page="../base/footer.jsp"></jsp:include>
