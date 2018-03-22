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
    notifyClient.subscribe('/message/topic.notification/' + urId, onMessageNotifyReceived);
}

function onMessageNotifyReceived(payload) {
    var message = JSON.parse(payload.body);
    //notification
    if (message.type != null && message.content != null) {
        var noNotification = document.getElementById('noNotification');
        noNotification.style.display = 'none';

        var notificationDot = document.getElementById('notificationNotify');
        var notifyCenter = document.getElementById('notificationCenter');

        //show notification
        notificationDot.classList.remove("hiddenDiv");

        //link
        var link = document.createElement('a');
        link.setAttribute('href', '#');
        link.setAttribute('onclick', 'openInNewTab(\'' + message.link + '\')');

        //icon
        var iconDiv = document.createElement('div');
        var icon = document.createElement('i');
        if (message.type == 1) {
            iconDiv.className = "btn btn-warning btn-circle";
            icon.className = "ti-user";
        } else if (message.type == 2) {
            iconDiv.className = "btn btn-success btn-circle";
            icon.className = "fa fa-check";
        } else if (message.type == 3) {
            iconDiv.className = "btn btn-danger btn-circle";
            icon.className = "ti-close";
        } else if (message.type == 4) {
            iconDiv.className = "btn btn-danger btn-circle";
            icon.className = "ti-close";
        }
        iconDiv.appendChild(icon);

        //content
        var contnetDiv = document.createElement('div');
        contnetDiv.className = "mail-contnet notification-content";
        var contentName = document.createElement('h5');
        if (message.type == 1) {
            contentName.innerHTML = "Xin gia nhập khóa học";
        } else if (message.type == 2) {
            contentName.innerHTML = "Xin gia nhập khóa học";
        } else if (message.type == 3) {
            contentName.innerHTML = "Xin gia nhập khóa học";
        } else if (message.type == 4) {
            contentName.innerHTML = "Loại khỏi khóa học";
        }
        var utext = document.createElement('small');
        utext.style.color = '#99abb4';
        utext.innerHTML = message.content;
        contnetDiv.appendChild(contentName);
        contnetDiv.appendChild(utext);

        //append stuff
        notifyCenter.appendChild(link);
        link.appendChild(iconDiv);
        link.appendChild(contnetDiv);
    }

    //message
    if (topic != null && message.topic != null && topic != message.topic) {
        var noMessageNotify = document.getElementById('noMessageNotification');
        noMessageNotify.style.display = 'none';

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