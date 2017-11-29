<jsp:include page="../base/top.jsp"></jsp:include>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="form-horizontal">
    <h4>Пользователь</h4>
    <hr>

    <input data-val="true" data-val-number="Значением поля id должно быть число." data-val-required="Требуется поле id." id="id" name="id" type="hidden" value="">

    <div class="form-group">
        <label class="control-label col-md-2" for="UserName">Пользователь</label>
        <div class="col-md-10">
            <input autocomplete="off" class="form-control text-box single-line" data-val="true" data-val-length="Имя пользователя не может быть длиннее 50 символов" data-val-length-max="50" data-val-minlength="Имя пользователя не может быть короче 3 символов" data-val-minlength-min="3" data-val-remote="Такой пользователь уже существует" data-val-required="Введите имя пользователя" id="UserName" name="UserName" type="text" value=""/>
            <span class="field-validation-valid text-danger" data-valmsg-for="UserName" data-valmsg-replace="true"></span>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-2" for="Password">Пароль</label>
        <div class="col-md-10">
            <div class="input-group" style="max-width: 280px; margin-bottom: 12px" data-provide="">
                <input class="form-control text-box single-line" data-val="true" data-val-length="Пароль не может быть длиннее 50 символов" data-val-length-max="50" data-val-required="Введите пароль" id="Password" name="Password" type="password" value="">
                <div class="input-group-addon" id="showPassword">
                    <span class="glyphicon glyphicon glyphicon-eye-close"></span>
                </div>
            </div>
            <span class="field-validation-valid text-danger" data-valmsg-for="Password" data-valmsg-replace="true"></span>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-2" for="FIO">ФИО</label>
        <div class="col-md-10">
            <span class="field-validation-valid text-danger" data-valmsg-for="FIO" data-valmsg-replace="true"></span>
            <input class="form-control text-box single-line" data-val="true" data-val-length="ФИО не может быть длиннее 50 символов" data-val-length-max="50" id="FIO" name="FIO" type="text" value="">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-md-2">Активен</label>
        <div class="col-md-10">
            <input checked="True" class="form-control" data-off="Нет" data-on="Да" data-toggle="toggle" data-val="true" data-val-required="Требуется поле Активен." id="toggle-active" name="isBlocked" type="checkbox" value="true">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-2" for="Role">Роль</label>
        <div class="col-md-10">
            <select class="form-control" data-val="true" data-val-number="Значением поля Роль должно быть число." id="Role" name="Role"><option value="0">Администратор</option>
                <option value="1">Пользователь</option>
            </select>
            <!--
            <input type="checkbox" data-toggle="toggle" data-on="Да" data-off="Нет" id="toggle-active">
                -->
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

<jsp:include page="../base/footer.jsp"></jsp:include>
