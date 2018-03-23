var leaderClient = null;
$(function () {
    if (leaderboardTopic != null) {
        if (leaderClient == null) {
            var socket = new SockJS('/chat');
            leaderClient = Stomp.over(socket);
            leaderClient.connect({}, onConnectedLeaderboard, onErrorLeaderboard);
        }
        else{
            leaderClient.subscribe('/message/topic.' + leaderboardTopic, onStatusUpdate);
        }
    }
});

function onErrorLeaderboard(error) {

}

function onConnectedLeaderboard() {
    leaderClient.subscribe('/message/topic.' + leaderboardTopic, onStatusUpdate);
}

function onStatusUpdate(payload) {
    var submission = JSON.parse(payload.body);
    var verdict = document.getElementById("sub" + submission.id);
    var timeRun = document.getElementById("timeRun" + submission.id);
    var memoryUsed = document.getElementById("memoryUsed" + submission.id);
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
    if (submission.timeRun != null && submission.timeRun != 0) {
        timeRun.innerHTML = submission.timeRun;
    }
    if (submission.memoryUsed != null && submission.memoryUsed != 0) {
        memoryUsed.innerHTML = submission.memoryUsed;
    }
    if (submission.judgeStatus == 0) {
        verdict.innerHTML = "<i class='fa fa-spin fa-spinner'></i>" + submission.verdict;
    }
    else {
        verdict.innerHTML = submission.verdict;
    }
}