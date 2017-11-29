<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${Title} – приложение Крафт</title>
    <link rel="stylesheet" href="Contents/bootstrap.css" />
    <link rel="stylesheet" href="Contents/bootstrap-toggle.css" />
    <link rel="stylesheet" href="Contents/bootstrap-datepicker3.min.css" />
    <link rel="stylesheet" href="Contents/bootstrap-clockpicker.min.css" />
    <link rel="stylesheet" href="Contents/animate.css" />
    <link id="favicon" rel="shortcut icon" href="pics/Craft.png" type="image/png">
    <!--
    <link id="favicon" rel="shortcut icon" href="blob:https://web.whatsapp.com/007d7b64-5112-48bf-8b82-76522e0a08b9" type="image/png">
    -->
    <link rel="stylesheet" href="Contents/site.css" />
    <script type="text/javascript" src="Scripts/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="Scripts/bootstrap.min.js"></script>
    <script type="text/javascript" src="Scripts/bootstrap-toggle.js"></script>
    <!--@Scripts.Render("~/bundles/jquerysignalr")
    <!--<script src="~/signalr/hubs"></script>-->
    <script type="text/javascript" src="Scripts/jquery.validate.min.js"></script>
    <script type="text/javascript" src="Scripts/jquery.validate.unobtrusive.min.js"></script>
    <script type="text/javascript" src="Scripts/bootstrap-datepicker.min.js"></script>
    <script type="text/javascript" src="Scripts/locales/bootstrap-datepicker.ru.min.js"></script>
    <script type="text/javascript" src="Scripts/bootstrap-clockpicker.min.js"></script>
    <script type="text/javascript" src="Scripts/js.cookie.js"></script>
    <script type="text/javascript" src="Scripts/notify.min.js"></script>
    <script type="text/javascript" src="Scripts/site_before.js"></script>
</head>
<body>
<!-- Plswait -->
<div id="plswait">
    <div class="align-helper">
        <div>
            <span class="glyphicon glyphicon-refresh"></span>
            <p>Пожалуйста подождите</p>
        </div>
    </div>
</div>

<noscript>
    <div class="noscript" >
        <p>Включите JavaScript в вашем браузере для доступа к содержимому.</p>
    </div>
</noscript>
<span id="newOrder" data-message="@ViewBag.message" hidden></span>
<div class="modal"></div>
<div class="container">
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="Home" class="navbar-brand">Крафт</a>
            </div>

            <div class="navbar-collapse collapse" id="navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="Orders/Create">Новый заказ</a>
                    </li>
                    <li>
                        <a href="Orders/Index">Журнал заказов</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Справочники<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li ${UserMenu}>
                                <a href="Directories/Users">Пользователи</a>
                            </li>
                            <li>
                                <a href="Directories/Agencies">Заказчики</a>
                            </li>
                            <li>
                                <a href="Directories/Drivers">Принципалы</a>
                            </li>
                            <li>
                                <a href="Directories/Routes">Маршруты</a>
                            </li>
                            <li>
                                <a href="Directories/Tables">Таблички для встречи</a>
                            </li>
                            <li>
                                <a href="Directories/Auto">Класс авто</a>
                            </li>
                            <li>
                                <a href="Directories/Compass">Компас</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Счета<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><p style="white-space: nowrap">Детализация счета <span class="caret"></span></p></li>
                            <li>
                                <a href="Bills/CreateBill">Сформировать</a>
                            </li>
                            <li>
                                <a href="Bills/List">Журнал</a>
                            </li>
                            <li role="separator" class="divider"></li>
                            <li><p style="white-space: nowrap">Список заказов водителю <span class="caret"></span></p></li>
                            <li>
                                <a href="Bills/CreateBillDriver">Сформировать</a>
                            </li>
                            <li>
                                <a href="Bills/ListDriver">Журнал</a>
                            </li>
                            <li role="separator" class="divider"></li>
                            <li>
                                <a href="Bills/FullReport">Полный отчет</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="Home/About">О программе</a>
                    </li>
                </ul>
                ${LoginMenu}
            </div>
        </div>


    </div>
    <div style="padding-bottom: 15px"></div>


    <hr />
    <footer>
        <p>&copy; ${CurrentYear} – приложение Крафт</p>
    </footer>
</div>
<script type="text/javascript" src="Scripts/site.js"></script>
</body>
</html>
