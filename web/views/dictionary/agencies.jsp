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
            <th nowrap onclick="changeOrdering('Name')">
                Агенство
                <span class="glyphicon glyphicon-sort-by-attributes enabledArrow" id="arrowName"></span>
            </th>
            <th nowrap onclick="changeOrdering('JuristicPerson')">
                Юр. лицо
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowJuristicPerson"></span>
            </th>
            <th nowrap onclick="changeOrdering('Contract')">
                Договор
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowContract"></span>
            </th>
            <th nowrap onclick="changeOrdering('Telephone')">
                Телефон
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowTelephone"></span>
            </th>
            <th nowrap onclick="changeOrdering('Fax')">
                Факс
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowFax"></span>
            </th>
            <th nowrap onclick="changeOrdering('Email')">
                Email
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowEmail"></span>
            </th>
            <th nowrap onclick="changeOrdering('ContactPerson')">
                Контакт
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowContactPerson"></span>
            </th>
            <th nowrap onclick="changeOrdering('ContactPersonCellular')">
                Телефон
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowContactPersonCellular"></span>
            </th>
            <th nowrap onclick="changeOrdering('Director')">
                Директор
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowDirector"></span>
            </th>
            <th nowrap onclick="changeOrdering('DirectorCellular')">
                Телефон
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowDirectorCellular"></span>
            </th>
            <th nowrap onclick="changeOrdering('Address')">
                Адрес
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowAddress"></span>
            </th>
            <th nowrap onclick="changeOrdering('Comment')">
                Комментарий
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowComment"></span>
            </th>
            <th nowrap onclick="changeOrdering('Type')">
                Тип
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowType"></span>
            </th>
            <th nowrap onclick="changeOrdering('Reduction')">
                Скидка
                <span class="glyphicon glyphicon-sort-by-attributes disabledArrow" id="arrowReduction"></span>
            </th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<jsp:include page="../base/bottomPagination.jsp"></jsp:include>
<jsp:include page="../base/confirmDelete.jsp"></jsp:include>
<jsp:include page="../base/footer.jsp"></jsp:include>
