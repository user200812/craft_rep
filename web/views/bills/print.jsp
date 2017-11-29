<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<fmt:formatDate pattern="dd.MM.yyyy" value="${bill.startDate}" var="startDate"/>
<fmt:formatDate pattern="dd.MM.yyyy" value="${bill.endDate}" var="endDate"/>
<fmt:formatDate pattern="dd.MM.yyyy" value="${bill.createDate}" var="createDate"/>

<!DOCTYPE html>
<html>
<head>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Детализация к счету – приложение Крафт</title>
    <link rel="stylesheet" href="Contents/bootstrap.css"/>
    <link id="favicon" rel="shortcut icon" href="pics/favicon.ico" type="image/x-ico">
    <link rel="stylesheet" href="Contents/site.css"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="Absolute-Center is-Responsive">
            <div>
                <H3>ООО «КОЛЕСНИЦА»</H3>
            </div>
            <div>
                <p>Список заказов транспортных услуг ${bill.agencyJuristicPerson}</p>
                <p>Детализация к счету от ${createDate}</p>
            </div>
            <div>
                <table class="table-report">
                    <thead>
                    <tr>
                        <th class="text-center">
                            Дата/время подачи
                        </th>
                        <th class="text-center">
                            Заказ для
                        </th>
                        <th class="text-center">
                            Кол. пассаж.
                        </th>
                        <th class="text-center">
                            Класс авто
                        </th>
                        <th class="text-center">
                            Тариф, руб.
                        </th>
                        <th class="text-center">
                            Маршрут
                        </th>
                        <th class="text-center">
                            Скидка, руб.
                        </th>
                        <c:if test="${bill.idTypeCash ne 0}">
                            <th class="text-center">
                                К оплате, руб.
                            </th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${bill.details}" var="item">
                        <tr>
                            <td>
                                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${item.dateTime}"/>
                            </td>
                            <td>
                                    ${item.customer}
                            </td>
                            <td>
                                    ${item.nums}
                            </td>
                            <td class="text-right">
                                    ${item.auto}
                            </td>
                            <td class="text-right">
                                <fmt:formatNumber pattern="0.00" value="${item.taxa}"/>
                            </td>
                            <td>
                                    ${item.route}
                            </td>
                            <td class="text-right">
                                <fmt:formatNumber pattern="0.00" value="${item.reduction}"/>
                            </td>
                            <c:if test="${bill.idTypeCash ne 0}">
                                <td class="text-right">
                                    <fmt:formatNumber pattern="0.00" value="${item.toPay}"/>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="6" class="text-right">
                            <c:if test="${bill.idTypeCash ne 0}">
                                <span>Итого к оплате:</span>
                            </c:if>
                            <c:if test="${bill.idTypeCash eq 0}">
                                <span>Итого к возврату:</span>
                            </c:if>
                        </td>
                        <td class="text-right">
                            <fmt:formatNumber pattern="0.00" value="${bill.total.reduction}"/>
                        </td>
                        <c:if test="${bill.idTypeCash ne 0}">
                            <td class="text-right">
                                <fmt:formatNumber pattern="0.00" value="${bill.total.toPay}"/>
                            </td>
                        </c:if>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


</body>
</html>
