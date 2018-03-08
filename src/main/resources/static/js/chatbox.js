/*jslint browser: true*/
/*global $, jQuery, alert*/

var stompClient = null;
var connectingElement = document.getElementById('connecting');
var messageInput = document.getElementById('messageInput');
var messageForm = document.getElementById('messageForm');
var messageArea = document.getElementById('chatList');

/*
    username and topic variable are declared in html page
    therefore do not search here
    since thymeleaf can only pass value direct to view, not js file (AFAIK :D)
 */
function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/message/topic.' + topic, onMessageReceived);
    connectingElement.classList.add('hiddenDiv');
    messageArea.scrollTop = messageArea.scrollHeight;
}

function onError(error) {
    connectingElement.textContent = 'Không thể kết nối đến server. Vui lòng thử lại!';
    connectingElement.className = "p-10 bg-light-danger";
    connectingElement.classList.remove('hiddenDiv');
}

messageForm.addEventListener('submit', sendMessage, true);

function sendMessage(event) {
    event.preventDefault();
    if (topic == 'NOT_CHOSEN') {
        return;
    }
    if (urId == null) {
        alert('Bạn cần đăng nhập để chat!');
        return;
    }
    var messageContent = messageInput.value.trim();
    if (messageContent == null || messageContent.length == 0) {
        return;
    }
    if (messageContent.length > 200) {
        alert('Vui lòng nhập không quá 200 ký tự!');
        event.preventDefault();
        return;
    }
    if (messageContent && stompClient) {
        var chatMessage = {
            username: username,
            urId: urId,
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
        if (null != urId && message.urId == urId) {
            alert('Gửi tin nhắn thất bại\r\nNguyên nhân: ' + message.comment);
        }
        return;
    }
    //chat time
    var chatTimeDiv = document.createElement('div');
    chatTimeDiv.classList.add('chat-time');
    chatTimeDiv.innerHTML = message.sendTime;

    //chat img
    var chatImgDiv = document.createElement('div');
    var chatImg = document.createElement('img');
    chatImgDiv.classList.add('chat-img');
    chatImgDiv.appendChild(chatImg);
    chatImg.src = '/assets/images/users/1.jpg';
    chatImg.alt = message.username;

    //chat content
    var contentDiv = document.createElement('div');
    var nameElement = document.createElement('h5');
    var messageContentDiv = document.createElement('div');
    contentDiv.classList.add('chat-content');
    contentDiv.appendChild(nameElement);
    contentDiv.appendChild(messageContentDiv);
    messageContentDiv.innerHTML = message.content;
    nameElement.innerHTML = message.username;
    messageContentDiv.style.color = "black";
    if (message.urId == urId) {
        messageContentDiv.classList.add('box');
        messageContentDiv.classList.add('bg-light-info');
        messageElement.classList.add('reverse');
        messageElement.appendChild(chatTimeDiv);
        messageElement.appendChild(contentDiv);
        messageElement.appendChild(chatImgDiv);
    } else {
        messageContentDiv.classList.add('box');
        messageContentDiv.classList.add('bg-light-inverse');
        messageElement.appendChild(chatImgDiv);
        messageElement.appendChild(contentDiv);
        messageElement.appendChild(chatTimeDiv);
    }
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function doSearch() {
    var inputElement = document.getElementById('inputText');
}
$(function () {
    var inputElement = document.getElementById('inputText');
    if (inputElement != null) {
        $("#inputText").on('input', function () {
            doSearch();
        });
    }
    if (topic != 'NOT_CHOSEN') {
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    } else {
        connectingElement.className = "p-10 bg-light-green";
        connectingElement.textContent = 'Vui lòng chọn người để chat cùng...';
    }
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
        var topOffset = 130;
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

