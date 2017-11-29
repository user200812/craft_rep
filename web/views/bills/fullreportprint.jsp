<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<fmt:formatDate pattern="dd.MM.yyyy" value="${fullreport.startDate}" var="startDate"/>
<fmt:formatDate pattern="dd.MM.yyyy" value="${fullreport.endDate}" var="endDate"/>

<!DOCTYPE html>
<html>
<head>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${Title} – приложение Крафт</title>
    <link rel="stylesheet" href="Contents/bootstrap.css" />
    <link id="favicon" rel="shortcut icon" href="pics/favicon.ico" type="image/x-ico">
    <link rel="stylesheet" href="Contents/site.css" />
</head>
<body>
<div class="container">
    <div class="row">
        <div class="Absolute-Center is-Responsive">
            <div>
                <p>г. Москва</p>
                <p>Агент: ООО «КОЛЕСНИЦА»</p>
            </div>
            <div>
                <h3 class="text-center">ОТЧЕТ АГЕНТА за ${startDate} – ${endDate}
                </h3>
                <h3 class="text-center">
                    от <fmt:formatDate pattern="dd.MM.yyyy" value="${nowDate}"/>
                </h3>
            </div>
            <div>
                <p>Описание работ: пассажирские перевозки.</p>
            </div>
            <div>
                <table class="table-report">
                    <thead>
                    <tr>
                        <th class="text-center">
                            Число/время подачи
                        </th>
                        <th class="text-center">
                            Заказ для Ф.И.О.
                        </th>
                        <th class="text-center">
                            Чел.
                        </th>
                        <th class="text-center">
                            Тариф на услуги, руб.
                        </th>
                        <th class="text-center">
                            Маршрут, парковки
                        </th>
                        <th class="text-center">
                            Скидка, руб.
                        </th>
                        <th class="text-center">
                            Комиссия, руб.
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${fullreportdetails}" var="item">
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
                                <fmt:formatNumber pattern="0.00" value="${item.taxa}"/>
                            </td>
                            <td>
                                ${item.route}
                            </td>
                            <td class="text-right">
                                <fmt:formatNumber pattern="0.00" value="${item.reduction}"/>
                            </td>
                            <td class="text-right">
                                <fmt:formatNumber pattern="0.00" value="${item.commission}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3" class="text-right">
                            Итого:
                        </td>
                        <td class="text-right">
                            <fmt:formatNumber pattern="0.00" value="${total.taxa}"/>
                        </td>
                        <td></td>
                        <td class="text-right">
                            <fmt:formatNumber pattern="0.00" value="${total.reduction}"/>
                        </td>
                        <td class="text-right">
                            <fmt:formatNumber pattern="0.00" value="${total.commission}"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
