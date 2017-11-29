// Куки для журнала
function SaveParamsOrdersInCookie(params) {
    Cookies.set("OrdersParams", params)
}

function LoadParamsOrdersFromCookie() {
    var p = Cookies.get("OrdersParams");
    var ret;
    if (typeof p == "undefined") {
        var dateStart = $("#orders").data("datestart");
        var dateEnd = $("#orders").data("dateend");
        ret = {
            ordertype: "asc", orderfield: "Datetime", dateStart: dateStart, dateEnd: dateEnd,
            number: "", customer: "", route: "", driver: "",
            agency: "", toggleuser: false, activePage: 1
        }
    }
    else {
        ret = jQuery.parseJSON(p);
    }
    return ret;
}

// Функция загружает заказы в таблицу через ajax запрос к вэб-серверу
function loadOrders() {
    plswait.fadeIn("fast");
    var newid = $("#orders").data("newid");
    var pageSize = 10;
    var search = $("#search").val();

    // Сохраняем куки
    SaveParamsOrdersInCookie(params);

    $("#checkAll").prop('checked', false);
    $("#resultBtn").addClass("disabled");
    $("#deleteBtn").addClass("disabled");

    var url = 'Orders/AjaxGetTableData';
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'json',
        async: true,
        data: {
            ordertype: params.ordertype,
            orderfield: params.orderfield,
            dateStart: params.dateStart,
            dateEnd: params.dateEnd,
            number: params.number,
            customer: params.customer,
            route: params.route,
            driver: params.driver,
            agency: params.agency,
            toggleuser: params.toggleuser,
            newid: newid,
            activePage: params.activePage,
            pageSize: pageSize,
            search: search
        },
        success: function (data) {
            setPagination(data.pagination);
            $("#orders tbody tr").remove();
            $('#orders tbody').append(data.rows);
            params.activePage = data.activePage;
            $("#paginationTop").data("num", data.numPages);
            if (newid != "" && $('#' + newid).length > 0) {
                $('#' + newid).parent().goTo();
                $("#orders").data("newid", null);
            }
            plswait.fadeOut("fast");
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
            plswait.fadeOut("fast");
            alert("Ошибка при загрузке данных журнала");
        }
    });

}
// Инициализация выпадающих списков (для элементов фильтра)
var substringMatcher = function (s) {
    return function findMatches(q, cb) {
        var matches;
        var url = 'Orders/AjaxGetJsonDataForTypeahead';

        // an array that will be populated with substring matches
        matches = [];

        $.ajax({

            type: 'GET',
            url: url,
            dataType: 'json',
            async: false,
            data: {type: s, search: q},
            success: function (data) {
                $.each(data, function (i, item) {
                    matches.push(item);
                });
            },
            error: function (xhr, status, error) {
                console.log(xhr.responseText);
            }
        });

        cb(matches);
    };
};

$('#route').typeahead({
        hint: true,
        highlight: true,
        minLength: 0
    },
    {
        name: 'route',
        source: substringMatcher("route"),
        limit: 1000,
        minLength: 0
    });

$('#agency').typeahead({
        hint: true,
        highlight: true,
        minLength: 0
    },
    {
        name: 'agency',
        source: substringMatcher("agency"),
        limit: 1000,
        minLength: 0
    });

$('#driver').typeahead({
        hint: true,
        highlight: true,
        minLength: 0
    },
    {
        name: 'driver',
        source: substringMatcher("driver"),
        limit: 1000,
        minLength: 0
    });

// При открытии фильтра загружаем текущие значения
$('#filter').on('shown.bs.modal', function () {
    $("#dateStart").val(params.dateStart);
    $("#dateEnd").val(params.dateEnd);
    $("#number").val(params.number);
    $("#customer").val(params.customer);
    $("#route").val(params.route);
    $("#driver").val(params.driver);
    $("#agency").val(params.agency);
    $("#toggle-user").prop("checked", params.toggleuser).change();

})
// При закрытии фильтра формируем запрос и перезагружаем таблицу
$('#btnFilter').on("click", function () {

    // Собираем нужную инфу для формирования запроса
    params.dateStart = $("#dateStart").val();
    params.dateEnd = $("#dateEnd").val();
    params.number = $("#number").val();
    params.customer = $("#customer").val();
    params.route = $("#route").val();
    params.driver = $("#driver").val();
    params.agency = $("#agency").val();
    params.toggleuser = $("#toggle-user").prop("checked");

    // прячем модальное окно
    $('#filter').modal('hide')
    setTimeout(function () {
        loadOrders();
    }, 1000)
});

// При открытии результата проверяем, что что-то выделено
$('#result').on('shown.bs.modal', function () {
});


// При закрытии результата формируем запрос и перезагружаем таблицу
function SetResult() {

    // Получаем список id для выделенных записей
    var ordersIds = [];
    var checkboxes = document.getElementsByTagName('input');
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].type == 'checkbox' && checkboxes[i].checked
            && "checkAll" != checkboxes[i].id && "toggle-user" != checkboxes[i].id) {
            var id = checkboxes[i].id;
            ordersIds.push(id);
        }
    }
    var result = $('#results').val();
    $("#orders").data("newid", 0);
    var parentbtnConfirm = $("#btnResult").parent();
    var prevHtml = parentbtnConfirm.html();
    parentbtnConfirm.html("<span class='glyphicon glyphicon-refresh spinning'></span>Устанавливаем результат");

    // Устанавливаем результат
    $.ajax({
        type: 'POST',
        url: 'Orders/AjaxSetResult',
        dataType: 'json',
        async: true,
        data: {'ordersIds': ordersIds, result: result},
        success: function (data) {
            loadOrders();
            // прячем модальное окно
            $('#result').modal('hide')
            setTimeout(function () {
                parentbtnConfirm.html(prevHtml);
                $("#btnResult").on("click", SetResult);
            }, 2000);
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
            // прячем модальное окно
            $('#result').modal('hide')

            // И выводим ошибку
            setTimeout(function () {
                parentbtnConfirm.html(prevHtml);
                $("#btnResult").on("click", SetResult);
                alert("Ошибка при сохранении результата");
            }, 1000)
        }
    });
}

// Функция снимает/устанавливает выделение для всех строк
// .. также отслеживает выделение строк и активирует или дисэйблит кнопки результата и удаления
function checkAll(el) {
    var checkboxes = document.getElementsByTagName('input');
    var isChecked = false;
    if (el.checked) {
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].type == 'checkbox'
                && "checkAll" != checkboxes[i].id
                && "toggle-user" != checkboxes[i].id) {
                checkboxes[i].checked = true;
                checkboxes[i].parentElement.parentElement.classList.add("checked");
                isChecked = true;
            }
        }
    } else {
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].type == 'checkbox'
                && "checkAll" != checkboxes[i].id
                && "toggle-user" != checkboxes[i].id) {
                checkboxes[i].parentElement.parentElement.classList.remove("checked");
                checkboxes[i].checked = false;
            }
        }
    }
    if (isChecked) {
        $("#resultBtn").removeClass("disabled");
        $("#deleteBtn").removeClass("disabled");
    } else {
        $("#resultBtn").addClass("disabled");
        $("#deleteBtn").addClass("disabled");
    }
}
// Функция отслеживает выделение строк и активирует или дисэйблит кнопки результата и удаления
function checkbx(el) {
    var isChecked = false;
    if (el.checked) {
        el.parentElement.parentElement.classList.add("checked");
        isChecked = true;
    } else {
        var checkboxes = document.getElementsByTagName('input');
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].type == 'checkbox' && checkboxes[i].checked
                && "checkAll" != checkboxes[i].id && "toggle-user" != checkboxes[i].id) {
                isChecked = true;
                break;
            }
        }
        el.parentElement.parentElement.classList.remove("checked");
    }
    $("#checkAll").prop('checked', isChecked);
    if (isChecked) {
        $("#resultBtn").removeClass("disabled");
        $("#deleteBtn").removeClass("disabled");
    } else {
        $("#resultBtn").addClass("disabled");
        $("#deleteBtn").addClass("disabled");
    }
}

function DeleteRecords() {
    // Получаем список id для выделенных записей
    var ordersIds = [];
    var checkboxes = document.getElementsByTagName('input');
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].type == 'checkbox' && checkboxes[i].checked
            && "checkAll" != checkboxes[i].id && "toggle-user" != checkboxes[i].id) {
            var id = checkboxes[i].id;
            ordersIds.push(id);
        }
    }
    $("#orders").data("newid", 0);
    var parentbtnConfirm = $("#btnDelete").parent();
    var prevHtml = parentbtnConfirm.html();
    parentbtnConfirm.html("<span class='glyphicon glyphicon-refresh spinning'></span>Удаляем");

    // Устанавливаем результат
    $.ajax({
        type: 'POST',
        url: 'Orders/AjaxDelete',
        dataType: 'json',
        async: true,
        data: {'ordersIds': ordersIds},
        success: function (data) {
            // Обновляем журнал заказов
            loadOrders();
            // прячем модальное окно
            $('#delete').modal('hide');
            setTimeout(function () {
                parentbtnConfirm.html(prevHtml);
                $("#btnDelete").on("click", DeleteRecords);
            }, 2000);
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
            // прячем модальное окно
            $('#delete').modal('hide')

            // И выводим ошибку
            setTimeout(function () {
                parentbtnConfirm.html(prevHtml);
                $("#btnDelete").on("click", DeleteRecords);
                alert("Ошибка при удалении заказов");
            }, 2000)
        }
    });
}

+$(function () { // will trigger when the document is ready
    if ($("#orders").length > 0) {
        params = LoadParamsOrdersFromCookie();
        // Устанавливаем параметры формы
        // .. стрелка сортировки
        setOrdering(params.orderfield, params.ordertype);
        loadOrders();
    }

    // При закрытии подтверждения удаления формируем запрос и перезагружаем таблицу
    $('#btnDelete').prop('onclick',null).off('click');
    $('#btnDelete').click(DeleteRecords);
    $('#btnResult').click(SetResult);
});
