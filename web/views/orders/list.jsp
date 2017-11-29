<jsp:include page="../base/top.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>${Title}</h2>
<a href="Orders/Create" class="btn btn-primary">Создать</a>
<label>
    <a data-toggle='modal' data-target='#filter' class='btn btn-primary' style="margin-left: 1em">Фильтр</a>
</label>
<label>
    <a data-toggle='modal' data-target='#result' class='btn btn-primary disabled' style="margin-left: 1em"
       id="resultBtn">Результат</a>
</label>
<div class="pull-right">
    <label>
        <a data-toggle='modal' data-target='#delete' class='btn btn-danger disabled' style="margin-left: 1em"
           id="deleteBtn">Удалить</a>
    </label>
</div>
<jsp:include page="../base/searchPagination.jsp"></jsp:include>

<div class="table-wrapper">
    <table class="table table-hover header-fixed" data-active="1" id="orders" data-newid="${newid}"
           data-orderfield="Datetime" data-ordertype="asc" data-dateend="${endDate}" data-datestart="${startDate}">
        <thead>
        <tr>
            <th nowrap>
                <input type="checkbox" title="Выделить всё" onclick="checkAll(this)" id="checkAll"/>
            </th>
            <th nowrap onclick="changeOrdering('Number')">
                №
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowNumber"></span>
            </th>
            <th nowrap onclick="changeOrdering('Datetime')">
                Дата/время
                <span class="glyphicon glyphicon-sort-by-attributes enabledArrow" id="arrowDatetime"></span>
            </th>
            <th nowrap onclick="changeOrdering('Compass')">
                Компасс
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowCompass"></span>
            </th>
            <th nowrap onclick="changeOrdering('Auto')">
                Класс авто
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowAuto"></span>
            </th>
            <th nowrap onclick="changeOrdering('Customer')">
                Заказ для
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowCustomer"></span>
            </th>
            <th nowrap onclick="changeOrdering('Nums')">
                Кол-во пассажиров
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowNums"></span>
            </th>
            <th nowrap onclick="changeOrdering('Telephones')">
                Телефоны
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowTelephones"></span>
            </th>
            <th nowrap onclick="changeOrdering('Route')">
                Маршрут
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowRoute"></span>
            </th>
            <th nowrap onclick="changeOrdering('Table')">
                Табличка
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowTable"></span>
            </th>
            <th nowrap onclick="changeOrdering('Taxa')">
                Тариф
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowTaxa"></span>
            </th>
            <th nowrap onclick="changeOrdering('Driver')">
                Принципал
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowDriver"></span>
            </th>
            <th nowrap onclick="changeOrdering('TypeCash')">
                Оплата заказа
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowTypeCash"></span>
            </th>
            <th nowrap onclick="changeOrdering('Agency')">
                Заказчик
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowAgency"></span>
            </th>
            <th nowrap onclick="changeOrdering('Result')">
                Результат
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowResult"></span>
            </th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<jsp:include page="../base/bottomPagination.jsp"></jsp:include>

<!-- Modal filter -->
<div class="modal fade" id="filter" tabindex="-1" role="dialog" aria-labelledby="myModalLabelFilter" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                Фильтр
            </div>
            <div class="modal-body form-horizontal">
                <div class="form-group" style="margin: 0px">
                    <label class="col-md-4 text-center control-label">Период</label>
                    <div class="col-md-8">
                        <div class="input-group date" style="max-width: 280px; margin-bottom: 12px;">
                            <input type="text" class="form-control" id="dateStart">
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group" style="margin: 0px">
                    <label class="col-md-4 text-center control-label">по</label>
                    <div class="col-md-8">
                        <div class="input-group date" style="max-width: 280px; margin-bottom: 12px;">
                            <input type="text" class="form-control" id="dateEnd">
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group" style="margin: 0px">
                    <label class="col-md-4 control-label">Номер</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="number">
                    </div>
                </div>
                <div class="form-group" style="margin: 0px">
                    <label class="col-md-4 control-label">Заказ для</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="customer">
                    </div>
                </div>
                <div class="form-group" style="margin: 0px">
                    <label class="col-md-4 control-label">Маршрут</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control typeahead" id="route">
                    </div>
                </div>
                <div class="form-group" style="margin: 0px">
                    <label class="col-md-4 control-label">Принципал</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control typeahead" id="driver">
                    </div>
                </div>
                <div class="form-group" style="margin: 0px">
                    <label class="col-md-4 control-label">Заказчик</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control typeahead" id="agency">
                    </div>
                </div>
                <div class="form-group" style="margin: 0px">
                    <label class="col-md-9 control-label">
                        Созданные пользователем?
                    </label>
                    <div class="col-md-3">
                        <input type="checkbox" data-toggle="toggle" data-on="Да" data-off="Нет" id="toggle-user">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                <a class="btn btn-primary" id="btnFilter">OK</a>
            </div>
        </div>
    </div>
</div>

<!-- Modal result -->
<div class="modal fade" id="result" tabindex="-1" role="dialog" aria-labelledby="myModalLabelResult" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                Установка результата
            </div>
            <div class="modal-body form-horizontal">
                <div class="form-group" style="margin: 0px">
                    <label class="col-md-4 text-center control-label">Результат</label>
                    <div class="col-md-8">
                        <select class="form-control" id="results">
                            <c:forEach var="item" items="${results}">
                                <option value="${item.getId()}">${item.getResult()}</option>
                            </c:forEach>
                            <option value="">Сбросить</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                <a class="btn btn-primary" id="btnResult">OK</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../base/confirmDelete.jsp"></jsp:include>


<script type="text/javascript" src="Scripts/typeahead.bundle.min.js"></script>
<script type="text/javascript" src="Scripts/orders.js"></script>


<jsp:include page="../base/footer.jsp"></jsp:include>
