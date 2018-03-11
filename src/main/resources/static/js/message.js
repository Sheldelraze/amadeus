var searchStatus = document.getElementById('searchStatus');
var listUser = document.getElementById('listUser');
var currentUserFetchTime = 0;
var userWindow = $('.chat-left-inner > .chatonline');
searchStatus.style.display = "none";
$('#inputText').on('input', function () {
    if (fetchingDataFlag) {
        return;
    }
    fetchingDataFlag = true;
    $(userWindow).empty();
    currentUserFetchTime = 0;
    currentUserIndex = 0;
    doFindUser();
});
function moveToTopic(currentTopic) {
    if (currentTopic == topic) {
        return;
    }
    if (topic != 'NOT_CHOSEN') {
        var currentLink = document.getElementById(topic);
        currentLink.classList.remove('active');
    }
    while (messageArea.firstChild) {
        messageArea.removeChild(messageArea.firstChild);
    }
    var newLink = document.getElementById(currentTopic);
    newLink.classList.add('active');
    if (stompClient == null) {
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
    currentMessageIndex = 0;
    topic = currentTopic;
    fetchMessage();
}

function createUserAnchor() {
    var anchor = document.createElement('a');
    anchor.setAttribute("name", "anchorUser" + currentUserFetchTime);
    anchor.style.position = "relative";
    listUser.appendChild(anchor);
}

$(userWindow).slimScroll({
    height: '100%',
    position: 'right',
    size: "5px",
    color: '#dcdcdc',
    scroll: 1
}).bind('slimscrolling', function () {
    if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
        if (fetchingDataFlag) {
            return;
        }
        fetchingDataFlag = true;
        doFindUser();
    }
});

function doFindUser() {
    var inputText = $('#inputText').val();
    searchStatus.style.display = "inline";
    var payload = {
        content: inputText,
        limitFrom: currentUserIndex
    };
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/message/findUser",
        data: JSON.stringify(payload),
        dataType: 'json',
        cache: false,
        timeout: 10000,
        success: function (data) {
            if (data != null && data.length > 0) {
                createUserAnchor();
            }


            for (var i = 0; i < data.length; i++) {
                addUser(data[i]);
            }
            if (data != null && data.length > 0) {
                currentUserIndex += data.length;
                var anchorName = "anchorUser" + currentUserFetchTime;
                var aTag = $("a[name=" + anchorName + "]");
                var newPos = aTag.position().top;
                $(userWindow).slimScroll({
                    scrollTo: newPos
                    , position: 'right'
                    , size: "5px"
                    , height: '100%'
                    , start: 'bottom'
                    , color: '#dcdcdc'
                    , scroll: 1
                });
                currentUserFetchTime = currentUserFetchTime + 1;
            }
            searchStatus.style.display = "none";
            checkIfNoResult();
            fetchingDataFlag = false;
        },
        error: function (e) {
            searchStatus.style.display = "inline";
            searchStatus.textContent = 'Lỗi hệ thống. Vui lòng load lại trang!';
            searchStatus.className = "p-10 bg-light-danger";
            fetchingDataFlag = false;
        }
    });
}

function checkIfNoResult() {
    if (!$.trim($(userWindow).html()).length) {
        $(userWindow).append("<li><div class='card-body'>Không tìm thấy dữ liệu...</div></li>")
    }
}
function addUser(data) {
    var newUser = document.createElement('li');

    var link = document.createElement('a');
    link.className = "redirectLink";
    link.setAttribute("href", "javascript:void(0)");
    link.setAttribute("id", data.conversation.topic);
    link.setAttribute("onclick", "moveToTopic('" + data.conversation.topic + "')");
    if (topic == data.conversation.topic) {
        link.classList.add('active');
    }

    //user avatar
    var userAvatar = document.createElement('img');
    userAvatar.src = '/assets/images/users/1.jpg';
    userAvatar.alt = data.fullname;
    userAvatar.className = "img-circle";

    //user infor
    var userInfor = document.createElement('span');
    userInfor.textContent = data.fullname;

    var userRole = document.createElement('small');
    userRole.textContent = data.role.text;
    if (data.role.id == 1) {
        userRole.className = "text-danger";
    } else if (data.role.id == 2) {
        userRole.className = "text-success";
    } else if (data.role.id == 3) {
        userRole.className = "text-primary";
    } else if (data.role.id == 4) {
        userRole.className = "text-warning";
    }
    listUser.appendChild(newUser);
    newUser.appendChild(link);
    userInfor.appendChild(userRole);
    link.appendChild(userAvatar);
    link.appendChild(userInfor);
}