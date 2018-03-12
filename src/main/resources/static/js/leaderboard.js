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
    var submission = JSON.parse(payload.body);
    var verdict = document.getElementById("sub" + submission.id);
    if (verdict == null) {
        return;
    }
    if (submission.judgeStatus == 0) {
        verdict.className = "judging";
    } else if (submission.judgeStatus == 6) {
        verdict.className = "accepted";
    } else {
        verdict.className = "fail";
    }
    verdict.innerHTML = submission.verdict;
}