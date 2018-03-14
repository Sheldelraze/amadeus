var notifyClient = null;
$(function () {
    if (urId != null) {
        var socket = new SockJS('/chat');
        notifyClient = Stomp.over(socket);
        notifyClient.connect({}, onConnect, onConnectError);
    }
});

function onConnect() {
    notifyClient.subscribe('/message/topic.inbox/' + urId, onMessageNotifyReceived);
}

function onMessageNotifyReceived(payload) {
    var message = JSON.parse(payload.body);
    if (topic != null && message.topic != null && topic != message.topic) {
        var notifyDot = document.getElementById('messageNotify');
        var messageCenter = document.getElementById('messageNotifyCenter');
        //show notification
        notifyDot.classList.remove("hiddenDiv");

        //link
        var notifyLink = document.createElement('a');
        notifyLink.setAttribute("href", "/message");

        //img div
        var notifyImgDiv = document.createElement('div');
        notifyImgDiv.className = "user-img";

        //img
        var userAvatar = document.createElement('img');
        userAvatar.setAttribute("src", "/assets/images/users/1.jpg");
        userAvatar.setAttribute("alt", message.sender.fullname);
        userAvatar.className = "img-circle";

        //message content
        var contentDiv = document.createElement('div');
        contentDiv.className = "mail-contnet";

        //user name
        var userName = document.createElement('h5');
        userName.innerHTML = message.sender.fullname;

        //content
        var messageContent = document.createElement('span');
        messageContent.className = "mail-desc";
        messageContent.innerHTML = message.content;

        //time sent
        var timeSent = document.createElement('span');
        timeSent.className = "time";
        timeSent.innerHTML = message.sendTime;

        contentDiv.appendChild(userName);
        contentDiv.appendChild(messageContent);
        contentDiv.appendChild(timeSent);

        notifyImgDiv.appendChild(userAvatar);

        notifyLink.appendChild(notifyImgDiv);
        notifyLink.appendChild(contentDiv);

        messageCenter.appendChild(notifyLink);

        var messageNotify = parseInt(document.getElementById('messageNotifyCount').innerHTML) + 1;
        document.getElementById('messageNotifyCount').innerHTML = "" + messageNotify;
    }
}

function onConnectError(error) {

}