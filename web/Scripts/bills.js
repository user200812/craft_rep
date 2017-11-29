// Куки для журнала
function SaveParamsBillsInCookie(params) {
    var type = $("#bills").data("type");
    Cookies.set(type + "Params", params)
}

function LoadParamsBillsFromCookie() {
    var type = $("#bills").data("type");
    var p = Cookies.get(type + "Params");
    var ret;
    if (typeof p == "undefined") {
        var dateStart = $("#bills").data("datestart");
        var dateEnd = $("#bills").data("dateend");
        ret = {
            ordertype: "asc", orderfield: "period", dateStart: dateStart, dateEnd: dateEnd, activePage: 1
        }
    }
    else {
        ret = jQuery.parseJSON(p);
    }
    return ret;
}

// Функция загружает заказы в таблицу через ajax запрос к вэб-серверу
function loadBills() {
    plswait.fadeIn("fast");
    var newid = $("#bills").data("newid");
    var pageSize = 10;

    // Сохраняем куки
    SaveParamsBillsInCookie(params);
    $("#checkAll").prop('checked', false);

    var type = $("#bills").data("type");
    var details = "";
    if (type != "Bills") {
        details = "Driver";
    }
    var url = 'Bills/AjaxGetData' + type;

    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'json',
        async: true,
        data: { ordertype: params.ordertype, orderfield: params.orderfield, details: details, newid: newid, activePage: params.activePage, pageSize: pageSize },
        success: function (data) {
            setPagination(data.pagination);
            $("#bills tbody tr").remove();
            $('#bills tbody').append(data.rows);
            params.activePage = data.activePage;
            $("#paginationTop").data("num", data.numPages);
            if (newid != "" && $('#' + newid).length > 0) {
                $('#' + newid).goTo();
                $("#bills").data("newid", null);
            }
            $('.toggle-paid').bootstrapToggle();
            // Обработчик переключателя оплачено
            $('.toggle-paid').on("change", setPaid);
            plswait.fadeOut("fast");

        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
            alert("Ошибка при загрузке данных журнала");
            plswait.fadeOut("fast");
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
                && $(checkboxes[i]).hasClass("toggle-paid") == false) {
                checkboxes[i].checked = true;
                checkboxes[i].parentElement.parentElement.classList.add("checked");
                isChecked = true;
            }
        }
    } else {
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].type == 'checkbox'
                && "checkAll" != checkboxes[i].id
                && $(checkboxes[i]).hasClass("toggle-paid") == false) {
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
                && "checkAll" != checkboxes[i].id && $(checkboxes[i]).hasClass("toggle-paid") == false) {
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

function setPaid() {
    var checked = this.checked;
    var id = this.id.replace("pay", "");
    var url = 'Bills/AjaxSetPaid';
    var type = $("#bills").data("type");
    $.ajax({
        type: 'POST',
        url: url,
        dataType: 'json',
        async: true,
        data: { id: id, type: type },
        success: function (data) {
            // Обновляем журнал 
            if (params.orderfield == "isPaid") {
                 $("#bills").data("newid", id);
                 setTimeout(function () { loadBills(); }, 1000)
            }
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
            alert("Ошибка при установке оплаты");
        }
    });
}

+$(function () { // will trigger when the document is ready
    if ($("#bills").length > 0) {
        params = LoadParamsBillsFromCookie();
        // Устанавливаем параметры формы
        // .. стрелка сортировки
        setOrdering(params.orderfield, params.ordertype);
        loadBills();
    }

    // При закрытии подтверждения удаления формируем запрос и перезагружаем таблицу
    $('#btnDelete').prop('onclick',null).off('click');
    $('#btnDelete').click(DeleteRecords);
});

function DeleteRecords() {
    // Получаем список id для выделенных записей
    var billIds = [];
    var checkboxes = document.getElementsByTagName('input');
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].type == 'checkbox'
            && checkboxes[i].checked
            && "checkAll" != checkboxes[i].id
            && checkboxes[i].id.indexOf("pay") == -1) {
            var id = checkboxes[i].id;
            billIds.push(id);
        }
    }
    var type = $("#bills").data("type");
    var parentbtnConfirm = $("#btnDelete").parent();
    var prevHtml = parentbtnConfirm.html();
    parentbtnConfirm.html("<span class='glyphicon glyphicon-refresh spinning'></span>Удаляем");

    // Устанавливаем результат
    $.ajax({
        type: 'POST',
        url: 'Bills/AjaxDelete',
        dataType: 'json',
        async: true,
        data: { 'billIds': billIds, type: type },
        success: function (data) {
            // прячем модальное окно
            $('#delete').modal('hide')
            parentbtnConfirm.html(prevHtml);
            $("#btnDelete").on("click", DeleteRecords);
            // Обновляем журнал заказов
            loadBills();
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
            // прячем модальное окно
            $('#delete').modal('hide')
            parentbtnConfirm.html(prevHtml);
            $("#btnDelete").on("click", DeleteRecords);

            // И выводим ошибку
            setTimeout(function () { alert("Ошибка при удалении заказа"); }, 1000)
        }
    });

}