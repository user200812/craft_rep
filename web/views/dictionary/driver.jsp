<jsp:include page="../base/top.jsp"></jsp:include>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>${TypeOp}</h2>
<spring:message code="NotEmpty.driverForm.name" var="NotEmptyname" />

<form:form class="form-horizontal" method="post"
           modelAttribute="driverForm" action="Directories/${actionUrl}">

    <div class="form-horizontal">
        <h4>${dicType}</h4>
        <hr/>
        <form:hidden path="id" />

        <div class="form-group">
            <label class = "control-label col-md-2">Фамилия</label>
            <div class="col-md-10">
                <form:input path="surname" type="text" class="form-control"
                            id="surname" />
                <form:errors path="surname" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Имя</label>
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
            <label class = "control-label col-md-2">Отчество</label>
            <div class="col-md-10">
                <form:input path="patronymic" type="text" class="form-control"
                            id="patronymic" />
                <form:errors path="patronymic" class="control-label text-danger" />
            </div>
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
            <label class = "control-label col-md-2">Телефон</label>
            <div class="col-md-10">
                <form:input path="telephone" type="text" class="form-control"
                            id="telephone" />
                <form:errors path="telephone" class="control-label text-danger" />
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
            <label class = "control-label col-md-2">Авто</label>
            <div class="col-md-10">
                <form:input path="car" type="text" class="form-control"
                            id="car" />
                <form:errors path="car" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Кондиционер</label>
            <div class="col-md-10">
                <form:input path="conditioner" type="text" class="form-control"
                            id="conditioner" />
                <form:errors path="conditioner" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">График</label>
            <div class="col-md-10">
                <form:input path="schedule" type="text" class="form-control"
                            id="schedule" />
                <form:errors path="schedule" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Округ</label>
            <div class="col-md-10">
                <form:input path="district" type="text" class="form-control"
                            id="district" />
                <form:errors path="district" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Гос.номер</label>
            <div class="col-md-10">
                <form:input path="number" type="text" class="form-control"
                            id="number" />
                <form:errors path="number" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Цвет</label>
            <div class="col-md-10">
                <form:input path="color" type="text" class="form-control"
                            id="color" />
                <form:errors path="color" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Год</label>
            <div class="col-md-10">
                <form:input path="year" type="text" class="form-control"
                            id="year" />
                <form:errors path="year" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Комиссия</label>
            <div class="col-md-10">
                <form:input path="commission" type="text" class="form-control"
                            id="commission" />
                <form:errors path="commission" class="control-label text-danger" />
            </div>
        </div>

        <div class="form-group">
            <label class = "control-label col-md-2">Эл.почта</label>
            <div class="col-md-10">
                <form:input path="email" type="text" class="form-control"
                            id="email" />
                <form:errors path="email" class="control-label text-danger" />
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
    <a href="Directories/Drivers" class="btn">Назад к списку</a>
</div>


<jsp:include page="../base/footer.jsp"></jsp:include>
