<jsp:include page="../base/top.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form name="form" action="j_spring_security_check" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="form-horizontal">
        <h2>${Title}</h2>

        <div class="form-group" style="margin-bottom: 10px">
            <div class="col-md-4">
                <input name="j_username" class="form-control has-error valid" data-val="true"
                       data-val-required="<spring:message code="NotEmpty.userForm.name"/>" id="j_username"
                       placeholder="Имя пользователя" type="text" value="" aria-required="true"
                       aria-describedby="Username-error" aria-invalid="false">
                <span class="text-danger field-validation-valid" data-valmsg-for="j_username"
                      data-valmsg-replace="true"></span>
            </div>
        </div>

        <div class="form-group" style="margin-bottom: 10px">
        <div class="col-md-4">
                <div class="input-group" style="max-width: 280px;" data-provide="">
                    <input class="form-control valid" data-val="true"
                           data-val-required="<spring:message code="NotEmpty.userForm.password"/>" id="password"
                           name="j_password" placeholder="Пароль" type="password" aria-required="true"
                           aria-describedby="Password-error" aria-invalid="false">
                    <div class="input-group-addon" id="showPassword">
                        <span class="glyphicon glyphicon glyphicon-eye-close"></span>
                    </div>
                </div>
                <span class="text-danger field-validation-valid" data-valmsg-for="j_password"
                      data-valmsg-replace="true"></span>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <button type="submit" class="btn btn-primary btn-block" id="loginBtn">
                    Войти
                </button>
                <c:if test="${not empty param['error']}">
                    <span class="text-danger field-validation-error">
                        <span>Неверное имя пользователя или пароль</span>
                    </span>
                    <br/>
                </c:if>
            </div>
        </div>
    </div>
</form>

<jsp:include page="../base/footer.jsp"></jsp:include>
