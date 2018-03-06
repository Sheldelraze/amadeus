/*jslint browser: true*/
/*global $, jQuery, alert*/

var stompClient = null;
var connectingElement = document.getElementById('connecting');
var messageInput = document.getElementById('messageInput');
var messageForm = document.getElementById('messageForm');

/*
    username and topic variable are declared in html page
    therefore do not search here
    since thymeleaf can only pass value direct to view, not js file (AFAIK :D)
 */
function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/message/topic.' + topic, onMessageReceived);

    // Tell your username to the server
    // stompClient.send("/app/chat.addUser",
    //     {},
    //     JSON.stringify({sender: username, type: 'JOIN'})
    // )

    connectingElement.classList.add('hiddenDiv');
}

function onError(error) {
    connectingElement.textContent = 'Không thể kết nối đến server. Vui lòng thử lại!';
    connectingElement.className = "p-10 bg-light-danger";
    connectingElement.classList.remove('hiddenDiv');
}

messageForm.addEventListener('submit', sendMessage, true);

function sendMessage(event) {
    if (username == null){
        alert('Bạn cần đăng nhập để chat!');
        event.preventDefault();
        return;
    }
    var messageContent = messageInput.value.trim();
    if (messageContent.length > 200){
        alert('Vui lòng nhập không quá 200 ký tự!');
        event.preventDefault();
        return;
    }
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value
        };
        stompClient.send("/message/send." + topic, {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if (message.type === 'FAIL') {
        if (null != username && message.sender == username) {
            alert('Gửi tin nhắn thất bại\r\nNguyên nhân: ' + message.comment);
        }
        event.preventDefault();
        return;
    }  else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    // messageArea.appendChild(messageElement);
    // messageArea.scrollTop = messageArea.scrollHeight;
}

$(function () {

    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
    $('.chat-left-inner > .chatonline').slimScroll({
        height: '100%',
        position: 'right',
        size: "5px",
        color: '#dcdcdc'

    });
    $('.chat-list').slimScroll({
        position: 'right'
        , size: "5px"
        , height: '100%'
        , color: '#dcdcdc'
    });

    var cht = function () {
        var topOffset = 220;
        var height = ((window.innerHeight > 0) ? window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        $(".chat-list").css("height", (height) + "px");
    };
    $(window).ready(cht);
    $(window).on("resize", cht);


    // this is for the left-aside-fix in content area with scroll
    var chtin = function () {
        var topOffset = 150;
        var height = ((window.innerHeight > 0) ? window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        $(".chat-left-inner").css("height", (height) + "px");
    };
    $(window).ready(chtin);
    $(window).on("resize", chtin);


    $(".open-panel").on("click", function () {
        $(".chat-left-aside").toggleClass("open-pnl");
        $(".open-panel i").toggleClass("ti-angle-left");
    });

});
