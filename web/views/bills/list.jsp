<jsp:include page="../base/top.jsp"></jsp:include>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>${Title}</h2>
<!--
<label>
<a data-toggle='modal' data-target='#filter' class='btn btn-primary' style="margin-left: 1em">Фильтр</a>
</label>
<label>
<a data-toggle='modal' data-target='#pay' class='btn btn-primary disabled' style="margin-left: 1em" id="payBtn">Оплатить</a>
</label>
-->
<div>
    <a href="Bills/${TypeCreate}" class = "btn btn-primary">Создать</a>
    <label>
        <a data-toggle='modal' data-target='#delete' class='btn btn-danger disabled' style="margin-left: 1em" id="deleteBtn">Удалить</a>
    </label>
    <div class="divPagination">
        <ul id="paginationTop" class="pagination" style="vertical-align: middle;"></ul>
    </div>
</div>
<div class="table-wrapper">
    <table class="table table-hover header-fixed" id="bills" data-newid="${newid}" data-type="${Type}" data-orderfield="period" data-ordertype="asc">
        <thead>
        <tr>
            <th nowrap>
                <input type="checkbox" title="Выделить всё" onclick="checkAll(this)" id="checkAll" />
            </th>
            <th nowrap onclick="changeOrdering('Number')">
                №
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowNumber"></span>
            </th>
            <th nowrap onclick="changeOrdering('period')">
                Период
                <span class="glyphicon glyphicon-sort-by-attributes enabledArrow" id="arrowperiod"></span>
            </th>
            <th nowrap onclick="changeOrdering('Name')">
                ${TableHeader}
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowName"></span>
            </th>
            <th nowrap onclick="changeOrdering('Type')">
                Вид оплаты
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowType"></span>
            </th>
            <th nowrap onclick="changeOrdering('toPay')">
                Сумма
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowtoPay"></span>
            </th>
            <th nowrap onclick="changeOrdering('isPaid')">
                Оплачено
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowisPaid"></span>
            </th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<jsp:include page="../base/bottomPagination.jsp"></jsp:include>

<jsp:include page="../base/confirmDelete.jsp"></jsp:include>

<script type="text/javascript" src="Scripts/bills.js"></script>

<jsp:include page="../base/footer.jsp"></jsp:include>