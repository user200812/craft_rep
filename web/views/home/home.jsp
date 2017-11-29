<jsp:include page="../base/top.jsp"></jsp:include>

<p>
    <div class="jumbotron">
        <h1>Крафт</h1>
<p class="lead">Крафт &mdash; программа для управления заказами транспортной компании.</p>
<a class = "btn btn-primary btn-lg" href="${MainActionURL}">${MainActionTitle}</a>
</div>

<div class="row">
    <div class="col-md-4">
        <h2>Новый заказ</h2>

        <p>
            <span class="glyphicon glyphicon-new-window" aria-hidden="true"></span>
            Создайте новый заказ, укажите его параметры и сохраните в базу данных.
        </p>

        <a class = "btn btn-default" href="Orders/Create">Создать заказ »</a>
    </div>
    <div class="col-md-4">
        <h2>Журнал заказов</h2>
        <p>
            <span class="glyphicon glyphicon-align-center" aria-hidden="true"></span>
            Просмотрите журнал с заказами, отредактируйте необходимые позиции заказов.
        </p>
        <a class = "btn btn-default" href="Orders">Журнал »</a>
    </div>
    <div class="col-md-4">
        <h2>Полный отчет</h2>
        <p>
            <span class="glyphicon glyphicon glyphicon-stats" aria-hidden="true"></span>
            Создайте полный отчет с помощью сервиса. Указав вводные данные, получите полные сведения о вашем бизнесе.
        </p>
        <a class = "btn btn-default" href="Bills/FullReport">Создать отчет »</a>
    </div>
</div>

<jsp:include page="../base/footer.jsp"></jsp:include>
