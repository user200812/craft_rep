<jsp:include page="../base/top.jsp"></jsp:include>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../base/titleAndIsDeleted.jsp"></jsp:include>
<jsp:include page="../base/searchPagination.jsp"></jsp:include>

<div class="table-wrapper">
    <table style="margin: 0px" class="table table-hover header-fixed" id="mainTable" data-orderfield="Name" data-newid="${newid}" data-dicType=${dicType} data-ordertype="asc" data-active="1">
        <thead>
        <tr>
            <th>
                <span style="display:none">Удалить</span>
            </th>
            <th onclick="changeOrdering('Name')">
                Наименование
                <span class="glyphicon glyphicon-sort-by-attributes" id="arrowName"></span>
            </th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<jsp:include page="../base/bottomPagination.jsp"></jsp:include>
<jsp:include page="../base/confirmDelete.jsp"></jsp:include>
<jsp:include page="../base/footer.jsp"></jsp:include>
