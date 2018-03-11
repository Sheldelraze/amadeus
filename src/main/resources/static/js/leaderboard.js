$(function () {
    if (topic != 'NOT_CHOSEN') {
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
});

function onError(error) {

}

function onConnected() {
    stompClient.subscribe('/message/topic.' + topic, onMessageReceived);
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
}