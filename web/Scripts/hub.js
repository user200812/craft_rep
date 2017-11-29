var stompClient = null;

function connect() {
    var socket = new SockJS(document.getElementsByTagName('base')[0].href + 'main');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('topic/main', function(greeting){
            var message = JSON.parse(greeting.body).message;
            console.log(message);
            Notify(message, "danger")
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}


+$(function () {
    connect();
});