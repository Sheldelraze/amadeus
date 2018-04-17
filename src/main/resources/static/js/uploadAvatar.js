var uploadInput = $("input[id=uploadAvatar]");
$('[data-toggle="tooltip"]').tooltip({
    trigger: 'hover'
})
$('button[id=btnUploadAvatar]').on("click", function () {
    $(uploadInput).trigger("click");
});
var validFileExtension = ['.jpg', '.jpeg', '.bmp', '.gif', '.png'];

function hasExtension(filename, exts) {
    return (new RegExp('(' + exts.join('|').replace(/\./g, '\\.') + ')$')).test(filename);
}

var _URL = window.URL || window.webkitURL;
$(uploadInput).change(function () {
    var filesize = this.files[0].size / (1024 * 1024);
    if (filesize > 4.0) {
        $.toast({
            heading: 'Kích thước file quá to!',
            text: 'Giới hạn là 4MB!',
            position: 'bottom-right',
            loaderBg: '#ff6849',
            icon: 'error',
            hideAfter: 3000,
            stack: 6
        });
        return false;
    }
    var filename = this.value;
    if (!hasExtension(filename, validFileExtension)) {
        $.toast({
            heading: 'Đuôi file không hợp lệ (.' + filename.split('.').pop() + ')!',
            text: 'Các đuôi file hợp lệ bao gồm: .jpg, .jpeg, .bmp, .gif, .png',
            position: 'bottom-right',
            loaderBg: '#ff6849',
            icon: 'error',
            hideAfter: 4000,
            stack: 6
        });
        return false;
    }
    var file, img;


    if ((file = this.files[0])) {
        img = new Image();
        img.onload = function () {
            if (this.width !== this.height) {
                $.toast({
                    heading: 'Ảnh không có kích thước 1:1',
                    text: 'Việc này có thể làm ảnh hưởng đến chất lượng hiển thị!',
                    position: 'bottom-right',
                    loaderBg: '#ff6849',
                    icon: 'warning',
                    hideAfter: 5000,
                    stack: 6
                });
            }
        };
        img.onerror = function () {
            alert("not a valid file: " + file.type);
        };
        img.src = _URL.createObjectURL(file);
    }
    var uploadData = $('#avatarUploadForm')[0];
    $.ajax({
        url: "/user/" + urId + "/uploadAvatar?" + parameter + "=" + token,
        type: "POST",
        data: new FormData(uploadData),
        enctype: 'multipart/form-data',
        processData : false,  // <----required to upload
        contentType : false,  // <----required to upload
        cache: false,
        timeout: 60000,
        success: function (data) {
            $.toast({
                heading: 'Cập nhật ảnh đại diện thành công!',
                position: 'bottom-right',
                loaderBg: '#ff6849',
                icon: 'success',
                hideAfter: 3000,
                stack: 6
            });
            $("#userAvatar").attr("src",data);
            $("#userAvatar1").attr("src",data);
            $("#userAvatar2").attr("src",data);
        },
        error: function (e) {
            $.toast({
                heading: 'Cập nhật ảnh thất bại!',
                text: 'Nguyên nhân: ' + e.responseText,
                position: 'bottom-right',
                loaderBg: '#ff6849',
                icon: 'warning',
                hideAfter: 5000,
                stack: 6
            });
        }
    });
});

