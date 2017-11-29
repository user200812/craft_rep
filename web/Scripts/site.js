var plswait = $('#plswait').hide();
/*
$(document)
  .ajaxStart(function () {
      if ($("#orders").length > 0) {
          plswait.fadeIn("fast");
      }
  })
  .ajaxStop(function () {
      if ($("#orders").length > 0) {
          plswait.fadeOut("fast");
      }
  });

*/

// Добавляет к кнопке сохранить loading
$('form').submit(function () {
    var form = $(this);
    if (form.valid() && form.data('submitted') != true) {
        form.data('submitted', true);
        var saveBtn = $("#saveBtn");
        if (saveBtn.length > 0) {
            saveBtn.prop('disabled', true);
            saveBtn.html("<span class='glyphicon glyphicon-refresh spinning'></span>Сохраняем");
        }
        var loginBtn = $("#loginBtn");
        if (loginBtn.length > 0) {
            loginBtn.prop('disabled', true);
            loginBtn.html("<span class='glyphicon glyphicon-refresh spinning'></span>Входим");
        }
        var createBtn = $("#createBtn");
        if (createBtn.length > 0) {
            createBtn.prop('disabled', true);
            createBtn.html("<span class='glyphicon glyphicon-refresh spinning'></span>Формируем");
        }
        form.submit();
    }
});


+$(function () { // will trigger when the document is ready
});
