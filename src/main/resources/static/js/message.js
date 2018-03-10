var searchStatus = document.getElementById('searchStatus');
var listUser = document.getElementById('listUser');
searchStatus.style.display = "none";

$('.chat-left-inner > .chatonline').slimScroll({
    height: '100%',
    position: 'right',
    size: "5px",
    color: '#dcdcdc',
    scroll: 1
}).bind('slimscrolling', function () {
    if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
        var inputText = $('#inputText').val();
        searchStatus.style.display = "inline";
        var payload = {
            content: inputText,
            limitFrom: currentUserIndex,
            limitTo: currentUserIndex + increment
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
                currentUserIndex += increment;
                searchStatus.style.display = "none";
                for (var i = 0; i < data.length; i++) {
                    addUser(data[i]);
                }
            },
            error: function (e) {
                searchStatus.style.display = "inline";
                searchStatus.textContent = 'Không thể kết nối đến server. Vui lòng thử lại!';
                searchStatus.className = "p-10 bg-light-danger";
            }
        });
    }
});

function addUser(data) {
    var newUser = document.createElement('li');

    var link = document.createElement('a');
    link.className = "redirectLink";
    link.setAttribute("href", "javascript:void(0)");

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