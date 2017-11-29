// Функция перехода к элементу
(function ($) {
    $.fn.goTo = function () {
        var offset = $(this).offset().top;
        if ($(".navbar-header").length > 0) {
            offset = offset - $(".navbar-header").height();
        }
        offset = Math.ceil(offset);
        $('html, body').animate({
            scrollTop: offset + 'px'
        }, 'slow');
        return this; // for chaining...
    }
})(jQuery);


$body = $("body");

// Куки для общего справочника
function SaveParamsInCookie(params) {
    var dicType = $("#mainTable").data("dictype");
    Cookies.set(dicType + "Params", params)
}

function LoadParamsFromCookie() {
    var dicType = $("#mainTable").data("dictype");
    var p = Cookies.get(dicType + "Params");
    var ret;
    if (typeof p == "undefined") {
        var orderfield = dicType == "Driver" ? "FIO" : "Name";
        ret = {isShowDeleted: false, ordertype: "asc", orderfield: orderfield, activePage: 1}
    }
    else {
        ret = jQuery.parseJSON(p);
    }
    return ret;
}

// Запрашивает данные у сервера для отображения в таблице справочника
// и отображает их
var params;
var isFromLoadDictionary = false;
function loadDictionary() {
    // Получаем значения переменных
    var dicType = $("#mainTable").data("dictype");
    var url = 'Directories/AjaxGetTableData';
    var newid = $("#mainTable").data("newid");
    var search = $("#search").val();
    var pageSize = 10;

    // Сохраняем куки
    SaveParamsInCookie(params);

    // Получаем через ajax-запрос таблицу с данными
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'json',
        async: true,
        data: {
            isShowDeleted: params.isShowDeleted,
            dictype: dicType,
            ordertype: params.ordertype,
            orderfield: params.orderfield,
            newid: newid,
            activePage: params.activePage,
            pageSize: pageSize,
            search: search
        },
        success: function (data) {
            setPagination(data.pagination);
            $("#mainTable tbody tr").remove();
            $('#mainTable tbody').append(data.rows);
            params.activePage = data.activePage;
            $("#paginationTop").data("num", data.numPages);
            $("#mainTable").data("newid", null);
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
            alert("Ошибка при загрузке данных справочника");
        }
    });
}


// Изменяет направление сортировки для общего справочника
function setOrdering(orderfield, ordertype) {
    var arrow = $("#arrow" + orderfield);
    if (arrow.length > 0) {
        arrow.css("display", "none");
        if (ordertype == "desc") {
            arrow.addClass("over");
            arrow.removeClass("out");
            arrow.addClass("enabledArrow");
            arrow.removeClass("disabledArrow");
        }
        else {
            arrow.addClass("out");
            arrow.removeClass("over");
            arrow.addClass("enabledArrow");
            arrow.removeClass("disabledArrow");
        }
        arrow.removeAttr("style");
    }
    if (orderfield != "Name") {
        var arrowName = $("#arrowName");
        if (arrowName.length > 0) {
            arrowName.removeClass("enabledArrow");
            arrowName.addClass("disabledArrow");
        }
    }
    if (orderfield != "FIO") {
        var arrowFIO = $("#arrowFIO");
        if (arrowFIO.length > 0) {
            arrowFIO.removeClass("enabledArrow");
            arrowFIO.addClass("disabledArrow");
        }
    }
    if (orderfield != "Datetime") {
        var arrowDatetime = $("#arrowDatetime");
        if (arrowDatetime.length > 0) {
            arrowDatetime.removeClass("enabledArrow");
            arrowDatetime.addClass("disabledArrow");
        }
    }
    if (orderfield != "period") {
        var arrowperiod = $("#arrowperiod");
        if (arrowperiod.length > 0) {
            arrowperiod.removeClass("enabledArrow");
            arrowperiod.addClass("disabledArrow");
        }
    }
}
// Изменяет направление сортировки для расширенного справочника
function changeOrdering(neworderfield) {
    // Если поле сортировки меняется, то сортировка по возрастанию
    // и меняем отображение стрелок
    if (params.orderfield != neworderfield) {
        var arrowNew = $("#arrow" + neworderfield);
        var arrowOld = $("#arrow" + params.orderfield);
        if (arrowNew.length > 0) {
            arrowNew.addClass("enabledArrow");
            arrowNew.removeClass("disabledArrow");
        }
        if (arrowOld.length > 0) {
            arrowOld.addClass("disabledArrow");
            arrowOld.removeClass("enabledArrow");
            // Также старую стрелку возвращаем в обычное положение
            if (params.ordertype == "desc") {
                params.ordertype = "asc";
                arrowOld.addClass("out");
                arrowOld.removeClass("over");
            }
        }
    }
    // Если поле сортировки не меняется, то изменяем направление сортировки
    else {
        var arrow = $("#arrow" + neworderfield);
        if (arrow) {
            if (params.ordertype == "asc") {
                params.ordertype = "desc";
                arrow.addClass("over");
                arrow.removeClass("out");
            }
            else {
                params.ordertype = "asc";
                arrow.addClass("out");
                arrow.removeClass("over");
            }
        }
    }
    params.orderfield = neworderfield;
    if ($("#mainTable").length > 0)
        setTimeout(function () {
            loadDictionary();
        }, 100)
    if ($("#orders").length > 0)
        setTimeout(function () {
            loadOrders();
        }, 100)
    if ($("#bills").length > 0)
        setTimeout(function () {
            loadBills();
        }, 100)
}

function toDate(str) {
    var from = str.split(".");
    return new Date(from[2], from[1] - 1, from[0]);
}

function Notify(message, type) {
    $.notify({
        // options
        message: '<span class=\"glyphicon glyphicon-warning-sign pull-left\" style=\"font-size: 32px; font-weight: 50%;\"></span><p style=\"padding-left: 3em\">' + message + '</p>'
    }, {
        // settings
        type: type,
        placement: {
            from: "bottom",
            align: "right"
        },
        delay: 15000,
        animate: {
            enter: 'animated fadeInRightBig',
            exit: 'animated fadeOutRightBig'
        },
    });
}

var isPressedSearchOrders = false;

+$(function () { // will trigger when the document is ready
    $.validator.methods.date = function (value, element) {
        return this.optional(element) || !/Invalid|NaN/.test(toDate(value));
    };
    $.validator.methods.range = function (value, element, param) {
        var globalizedValue = value.replace(",", ".");
        return this.optional(element) || (globalizedValue >= param[0] && globalizedValue <= param[1]);
    }

    $.validator.methods.number = function (value, element) {
        return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:[\s\.,]\d{3})+)(?:[\.,]\d+)?$/.test(value);
    }

    // Код для уведомлений
    /*
     var chat = $.connection.expiredOrdersHub;
     chat.client.ShowMessage = function (message, type) {
     Notify(message, type);
     }
     $.connection.hub.start().done(function () {
     });
     */
    // Инициализация дата и тайм пикеров и параметров
    $('.date').datepicker({
        isRTL: false,
        format: 'dd.mm.yyyy',
        autoclose: true,
        language: 'ru',
		todayHighlight: true
    }); //Initialise any date pickers
    $('.clockpicker').clockpicker({
        isRTL: false,
        format: 'hh:mm',
        autoclose: true,
        language: 'ru'
    });
    // Обработчик появления модального окна подтверждения
    $('#delete').on('show.bs.modal', function (e) {
        // устанавливаем аттрибуты для кнопки подтверждения
        var btnDelete = $('#btnDelete');
        var urlToDelete = $(e.relatedTarget).data('href');
        btnDelete.attr('url-to-delete', urlToDelete);
        var itemId = $(e.relatedTarget).attr('item-id')
        btnDelete.attr('item-to-delete', itemId);
        // для соотв строки делаем подсветку
        $("*[data-id|=" + itemId + "]").removeClass('newid');
        $("*[data-id|=" + itemId + "]").addClass('forDelete');

    });

    // Обработчик закрытия модального окна подтверждения
    $('#delete').on('hidden.bs.modal', function (e) {
        // для соотв строки делаем подсветку
        var itemId = $(this).find('.btn-ok').attr('item-to-delete')
        $("*[data-id|=" + itemId + "]").removeClass('forDelete');

    });
    // Обработчик кнопки подтверждения модального окна подтверждения
    $("#btnDelete").on("click", DeleteRecord);
    // Обработчик изменения поиска
    $("#search").on("keyup paste cut", function () {
        SetDisplayClearSearch(this.value);
        if ($("#mainTable").length > 0) {
            setTimeout(function () {
                loadDictionary();
            }, 400)
        }
        if ($("#orders").length > 0 && isPressedSearchOrders == false) {
            isPressedSearchOrders = true;
            setTimeout(function () {
                loadOrders();
                isPressedSearchOrders = false;
            }, 2000)

        }
    });

    // Инициализация параметров и загрузка документов
    if ($("#mainTable").length > 0) {
        params = LoadParamsFromCookie();
        // Устанавливаем параметры формы
        // .. стрелка сортировки
        setOrdering(params.orderfield, params.ordertype);
        // тоггл показывать удаленные записи
        $("#toggle-event").bootstrapToggle(params.isShowDeleted ? "on" : "off");
        loadDictionary();
    }

    // Обработчик показать пароль
    $('#showPassword').click(function () {
        var pswd = $("#password");
        if (pswd.length > 0) {
            var type = pswd.attr("type");
            if (type == "text") {
                pswd.attr("type", "password");
                $(this).children().removeClass("glyphicon-eye-open");
                $(this).children().addClass("glyphicon-eye-close");
            }
            else {
                pswd.attr("type", "text");
                $(this).children().addClass("glyphicon-eye-open");
                $(this).children().removeClass("glyphicon-eye-close");
            }
        }
    });
    // Обработчик переключателя отображать удаленные
    $('#toggle-event').on("change", function () {
        if ($("#mainTable").length > 0) {
            params.isShowDeleted = $("#toggle-event").prop("checked");
            loadDictionary();
        }
    });


    if ($("#newOrder").length > 0) {
        var message = $("#newOrder").data("message");
        if (message != "")
            Notify(message, "success");
    }
});

// Удаляет запись справочника
function DeleteRecord() {

    // Собираем нужную инфу для формирования запроса
    var btnDelete = $('#btnDelete');
    var idToDelete = btnDelete.attr('item-to-delete');
    var urlToDelete = btnDelete.attr('url-to-delete');
    var parentbtnConfirm = btnDelete.parent();
    var prevHtml = parentbtnConfirm.html();
    parentbtnConfirm.html("<span class='glyphicon glyphicon-refresh spinning'></span>Удаляем");

    // Формируем запрос ajax для удаления в БД
    $.ajax({
        url: urlToDelete,
        type: 'POST',
        async: true,
        // пои успехе обновляем в интерфейсе
        success: function () {
            loadDictionary();
            $('#delete').modal('hide');
            setTimeout(function () {
                // прячем модальное окно
                parentbtnConfirm.html(prevHtml);
                $("#btnDelete").on("click", DeleteRecord);
            }, 2000);
        },
        error: function (e) {
            alert("Ошибка при удалении записи");
            loadDictionary();
            $('#delete').modal('hide');
            setTimeout(function () {
                // прячем модальное окно
                parentbtnConfirm.html(prevHtml);
                $("#btnDelete").on("click", DeleteRecord);
            }, 2000);
        }
    });
}

// Показывает или скрывает элемент, очищающий строку поиска
function SetDisplayClearSearch(search) {
    var clearSearch = $("#clearSearch");
    if (clearSearch.length > 0) {
        if (search != "")
            clearSearch.addClass("search-display");
        else
            clearSearch.removeClass("search-display");
        clearSearch.removeAttr("style");
    }
}

function ClearSearch() {
    var search = $("#search");
    if (search.length > 0) {
        var isNeedReload = search.val() != "";
        search.val("");
        SetDisplayClearSearch(search.val());
        if (isNeedReload && $("#mainTable").length > 0) {
            loadDictionary();
        }
        if (isNeedReload && $("#orders").length > 0) {
            loadOrders();
        }
    }
}

function setActivePage(activepage) {
    params.activePage = activepage;
    if ($("#mainTable").length > 0) {
        loadDictionary();
    }
    if ($("#orders").length > 0) {
        loadOrders();
    }
    if ($("#bills").length > 0) {
        loadBills();
    }
}
function prevPage() {
    var activepage = params.activePage > 1 ? params.activePage - 1 : 1;
    setActivePage(activepage);
}

function nextPage() {
    var numpages = $("#paginationTop").data("num");
    var activepage = params.activePage < numpages ? params.activePage + 1 : numpages;
    setActivePage(activepage);
}

function setPagination(items) {
    $("#paginationTop li").remove();
    $("#paginationTop").append(items);
    $("#paginationBottom li").remove();
    $("#paginationBottom").append(items);
    var search = $("#searchdiv");
    if (search.length > 0) {
        var paginationWidth = Math.ceil($("#paginationTop").width() + 20);
        var searchWidth = "calc(100% - " + paginationWidth + "px)";
        var styles = {"width": searchWidth}
        search.css(styles);
    }
}


