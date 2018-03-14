/*jslint browser: true*/
/*global $, jQuery, alert*/
var stompClient = null;
var connectingElement = document.getElementById('connecting');
var messageInput = document.getElementById('messageInput');
var messageForm = document.getElementById('messageForm');
var messageArea = document.getElementById('chatList');
var currentFetchTime = 0;
var fetchingDataFlag = false;
/*
    username and topic variable are declared in html page
    therefore do not search here
    since thymeleaf can only pass value direct to view, not js file (AFAIK :D)
 */
function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/message/topic.' + topic, onMessageReceived);
    connectingElement.classList.add('hiddenDiv');
    $('.chat-list').slimScroll({
        scrollTo: messageArea.scrollHeight
        , position: 'right'
        , size: "5px"
        , height: '100%'
        , start: 'bottom'
        , color: '#dcdcdc'
        , scroll: 1
    }).bind('slimscroll', function () {
        fetchMessage();
    });

}

function createAnchor() {
    var anchor = document.createElement('a');
    anchor.setAttribute("name", "anchor" + currentFetchTime);
    anchor.style.position = "relative";
    messageArea.insertBefore(anchor, messageArea.firstChild);
}

function fetchMessage() {
    if (fetchingDataFlag) {
        return;
    }
    fetchingDataFlag = true;
    connectingElement.textContent = 'Đang lấy dữ liệu...';
    connectingElement.className = "p-10 bg-light-primary";
    connectingElement.classList.remove('hiddenDiv');
    var payload = {
        topic: topic,
        limitFrom: currentMessageIndex
    };
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/message/getMessage",
        data: JSON.stringify(payload),
        dataType: 'json',
        cache: false,
        timeout: 10000,
        success: function (data) {
            if (data != null && data.length > 0) {
                createAnchor();
            }
            for (var i = data.length - 1; i >= 0; i--) {
                addMessage(data[i], 'top');
            }
            if (data != null && data.length > 0) {
                var anchorName = "anchor" + currentFetchTime;
                var aTag = $("a[name=" + anchorName + "]");
                var newPos = aTag.position().top;
                $('.chat-list').slimScroll({
                    scrollTo: newPos
                    , position: 'right'
                    , size: "5px"
                    , height: '100%'
                    , start: 'bottom'
                    , color: '#dcdcdc'
                    , scroll: 1
                });
                currentFetchTime = currentFetchTime + 1;
                currentMessageIndex += data.length;
            }
            fetchingDataFlag = false;
            connectingElement.classList.add('hiddenDiv');

        },
        error: function (e) {
            fetchingDataFlag = false;
            connectingElement.textContent = 'Lỗi hệ thống. Vui lòng load lại trang!';
            connectingElement.className = "p-10 bg-light-danger";
        }
    });
}

function addMessage(message, position) {
    var messageElement = document.createElement('li');

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
    messageContentDiv.classList.add('preserve-line');
    if (message.username == username) {
        messageContentDiv.classList.add('box');
        messageContentDiv.classList.add('bg-light-info');
        messageElement.classList.add('reverse');
        messageElement.appendChild(chatTimeDiv);
        messageElement.appendChild(contentDiv);
        messageElement.appendChild(chatImgDiv);
        messageContentDiv.style.textAlign = "left";
    } else {
        messageContentDiv.classList.add('box');
        if (message.isRead != null && message.isRead == 0) {
            messageContentDiv.classList.add('bg-light-green');
        }
        else {
            messageContentDiv.classList.add('bg-light-inverse');
        }
        messageElement.appendChild(chatImgDiv);
        messageElement.appendChild(contentDiv);
        messageElement.appendChild(chatTimeDiv);
    }
    if (position == 'bottom') {
        messageArea.appendChild(messageElement);
    } else if (position == 'top') {
        messageArea.insertBefore(messageElement, messageArea.firstChild);
    }
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
    if (message.topic != topic) {
        return;
    }
    if (message.type == 'FAIL') {
        if (null != urId && message.urId == urId) {
            alert('Gửi tin nhắn thất bại\r\nNguyên nhân: ' + message.comment);
        }
        return;
    }
    addMessage(message, 'bottom');
    $('.chat-list').slimScroll({
        scrollTo: messageArea.scrollHeight
        , position: 'right'
        , size: "5px"
        , height: '100%'
        , start: 'bottom'
        , color: '#dcdcdc'
        , scroll: 1
    });
}

$(function () {
    $('textarea').on('keydown', function (event) {
        if (event.keyCode == 13)
            if (!event.shiftKey && !event.altKey)
                sendMessage(event);
    });
    if (topic != 'NOT_CHOSEN') {
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    } else {
        connectingElement.className = "p-10 bg-light-green";
        connectingElement.textContent = 'Vui lòng chọn người để chat cùng...';
    }
    $('.chat-list').slimScroll({
        scrollTo: messageArea.scrollHeight
        , position: 'right'
        , size: "5px"
        , height: '100%'
        , start: 'bottom'
        , color: '#dcdcdc'
        , scroll: 1
    })

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

