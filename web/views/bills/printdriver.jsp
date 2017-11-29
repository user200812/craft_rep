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
    <title>Список заказов, оплаченных водителю – приложение Крафт</title>
    <link rel="stylesheet" href="Contents/bootstrap.css"/>
    <link id="favicon" rel="shortcut icon" href="pics/favicon.ico" type="image/x-ico">
    <link rel="stylesheet" href="Contents/site.css"/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="Absolute-Center is-Responsive">
            <div>
                <p>г. Москва</p>
                <p>Агент: ООО «КОЛЕСНИЦА»</p>
                <p>Принципал: ${bill.driverName}</p>
            </div>
            <div>
                <h3 class="text-center">ОТЧЕТ АГЕНТА за ${startDate} - ${endDate}</h3>
                <h3 class="text-center">от ${createDate}</h3>
            </div>
            <div>
                <p>Описание работ: пассажирские перевозки.</p>
                <c:if test="${bill.idTypeCash eq 0}">
                    <p>Список заказов, переданных Принципалу за наличный расчет:</p>
                </c:if>
                <c:if test="${bill.idTypeCash ne 0}">
                    <p>Список заказов, переданных Принципалу за безналичный расчет:</p>
                </c:if>
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
                        <c:if test="${bill.idTypeCash eq 0}">
                            <th class="text-center">
                                Выплата комиссионного вознаграждения руб.
                            </th>
                        </c:if>
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
                            <td class="text-right">
                                <fmt:formatNumber pattern="0.00" value="${item.toPay}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3"></td>
                        <td class="text-right">
                            <fmt:formatNumber pattern="0.00" value="${bill.total.taxa}"/>
                        </td>
                        <td></td>
                        <td class="text-right">
                            <fmt:formatNumber pattern="0.00" value="${bill.total.reduction}"/>
                        </td>
                        <td class="text-right">
                            <fmt:formatNumber pattern="0.00" value="${bill.total.commission}"/>
                        </td>
                        <td class="text-right">
                            <fmt:formatNumber pattern="0.00" value="${bill.total.toPay}"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div>
                <p style="padding-top: 2em;">Стоимость услуг, переданных Принципалу за месяц, составила
                    <fmt:formatNumber pattern="0.00" value="${bill.total.taxa}"/>
                    (${totalTaxaStr}) рублей</p>
                <p style="padding-top: 2em;">Сумма комиссионного Вознаграждения за месяц составила
                    <fmt:formatNumber pattern="0.00" value="${bill.total.commission}"/>
                    (${totalCommissionStr}) рублей</p>
                <p style="padding-top: 2em;">Сумма скидок от тарифа предоставленные Агентом Заказчикам за месяц
                    составила
                    <fmt:formatNumber pattern="0.00" value="${bill.total.reduction}"/>
                    ${totalReductionStr}
                    рублей</p>
                <c:if test="${bill.idTypeCash eq 0}">
                    <p style="padding-top: 2em;">Выплата комиссионного вознаграждения Принципалом:
                        <fmt:formatNumber pattern="0.00" value="${bill.total.toPay}"/>
                         (${totaltoPayStr}) рублей</p>
                </c:if>
                <c:if test="${bill.idTypeCash ne 0}">
                    <td class="text-right">
                        <p style="padding-top: 2em;">К оплате Принципалу с учетом скидок:
                            <fmt:formatNumber pattern="0.00" value="${bill.total.toPay}"/>
                            (${totaltoPayStr}) рублей</p>
                    </td>
                </c:if>

            </div>
            <table class="table table-footer">
                <tr>
                    <td style="width: 50%; padding-left: 1em">Агент:</td>
                    <td style="width: 50%; padding-left: 1em">Принципал:</td>
                </tr>
                <tr>
                    <td colspan="2" style="padding-left: 1em">Генеральный директор</td>
                </tr>
                <tr>
                    <td colspan="2" style="padding-left: 1em">ООО «КОЛЕСНИЦА»</td>
                </tr>
                <tr style="height: 12em">
                    <td>_____________________________</td>
                    <td>_____________________________</td>
                </tr>
                <tr style="height: 2em">
                    <td style="padding-left: 3em">м.п.</td>
                    <td style="padding-left: 3em">м.п.</td>
                </tr>
            </table>
        </div>
    </div>
</div>

</body>
</html>
