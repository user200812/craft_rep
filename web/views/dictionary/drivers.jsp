<jsp:include page="../base/top.jsp"></jsp:include>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../base/titleAndIsDeleted.jsp"></jsp:include>
<jsp:include page="../base/searchPagination.jsp"></jsp:include>

<div class="table-wrapper">
    <table style="margin: 0px" class="table table-hover header-fixed" id="mainTable" data-orderfield="Name" data-newid="${newid}" data-dicType=${dicType} data-ordertype="asc" data-active="1">
        <thead>
        <tr>
            <th nowrap>
                <span style="display:none">Удалить</span>
            </th>
            <th nowrap onclick="changeOrdering('FIO')">
                ФИО
                <span class="glyphicon glyphicon-sort-by-attributes enabledArrow" id="arrowFIO"></span>
            </th>
            <th nowrap onclick="changeOrdering('Contract')">
                Договор
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowContract"></span>
            </th>
            <th nowrap onclick="changeOrdering('Car')">
                Авто
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowCar"></span>
            </th>
            <th nowrap onclick="changeOrdering('Conditioner')">
                Кондиц.
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowConditioner"></span>
            </th>
            <th nowrap onclick="changeOrdering('Schedule')">
                График
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowSchedule"></span>
            </th>
            <th nowrap onclick="changeOrdering('District')">
                Округ
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowDistrict"></span>
            </th>
            <th nowrap onclick="changeOrdering('Telephone')">
                Телефон
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowTelephone"></span>
            </th>
            <th nowrap onclick="changeOrdering('Number')">
                Гос.номер
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowNumber"></span>
            </th>
            <th nowrap onclick="changeOrdering('Color')">
                Цвет
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowColor"></span>
            </th>
            <th nowrap onclick="changeOrdering('Year')">
                Год
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowYear"></span>
            </th>
            <th nowrap onclick="changeOrdering('Comment')">
                Комментарий
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowComment"></span>
            </th>
            <th nowrap onclick="changeOrdering('Commission')">
                Комиссия
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowCommission"></span>
            </th>
            <th nowrap onclick="changeOrdering('Email')">
                Email
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowEmail"></span>
            </th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<jsp:include page="../base/bottomPagination.jsp"></jsp:include>
<jsp:include page="../base/confirmDelete.jsp"></jsp:include>
<jsp:include page="../base/footer.jsp"></jsp:include>
