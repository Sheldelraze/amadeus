var newPasswordInput = $('#newPassword');
var oldPasswordInput = $('#oldPassword');
var newPasswordDiv = $('#newPasswordDiv');
var newPasswordErr = $('#newPasswordErr');
var retypePasswordInput = $('#retypePassword');
var retypePasswordDiv = $('#retypePasswordDiv');
var retypePasswordErr = $('#retypePasswordErr');
var errReturnDiv = $('#errReturnDiv');
$(document).ready(function() {
    $("#buttonSubmit").click(function(){
        $(errReturnDiv).addClass('hiddenDiv');
        if (!checkPassword()){
            return false;
        }
        var payload = {
            urId : urId,
            oldPassword: $(oldPasswordInput).val(),
            newPassword: $(newPasswordInput).val()
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/user/updatePassword",
            data: JSON.stringify(payload),
            cache: false,
            timeout: 10000,
            success: function (data) {
                //if update success then data should be null or empty string
                if (data){
                    $(errReturnDiv).removeClass('hiddenDiv');
                    $(errReturnDiv).html('✘ ' + data);
                }
                else{
                    $(newPasswordDiv).removeClass('has-success');
                    $(newPasswordInput).removeClass('form-control-success');
                    $(retypePasswordDiv).removeClass('has-success');
                    $(retypePasswordInput).removeClass('form-control-success');
                    $(newPasswordInput).val('');
                    $(oldPasswordInput).val('');
                    $(retypePasswordInput).val('');
                    $('#changePassword').modal('toggle');
                    $.toast({
                        heading: 'Cập nhật mật khẩu thành công',
                        position: 'bottom-right',
                        loaderBg:'#ff6849',
                        icon: 'success',
                        hideAfter: 3000,
                        stack: 6
                    });
                }
            },
            error: function (e) {
                $(errReturnDiv).removeClass('hiddenDiv');
                $(errReturnDiv).html('✘ ' + e.responseText);
            }
        });
    });
    $(newPasswordInput).on('input',checkPassword);
    $(retypePasswordInput).on('input',checkPassword);
});
function checkPassword(){
    $(newPasswordDiv).removeClass('has-danger');
    $(newPasswordInput).removeClass('form-control-danger');
    $(retypePasswordDiv).removeClass('has-danger');
    $(retypePasswordInput).removeClass('form-control-danger');
    var newPass = $(newPasswordInput).val();
    if (!newPass){
        $(newPasswordDiv).addClass('has-danger');
        $(newPasswordInput).addClass('form-control-danger');
        $(newPasswordErr).html('Mật khẩu không được để trống!');
        return false;
    }
    if (newPass.length > 30){
        $(newPasswordDiv).addClass('has-danger');
        $(newPasswordInput).addClass('form-control-danger');
        $(newPasswordErr).html('Mật khẩu quá dài!');
        return false;
    }
    $(newPasswordDiv).addClass('has-success');
    $(newPasswordInput).addClass('form-control-success');
    $(newPasswordErr).html('');
    var retypePass = $('#retypePassword').val();
    if (retypePass !== newPass){
        $(retypePasswordDiv).addClass('has-danger');
        $(retypePasswordInput).addClass('form-control-danger');
        $(retypePasswordErr).html('Mật khẩu không khớp!');
        return false;
    }
    $(retypePasswordDiv).addClass('has-success');
    $(retypePasswordInput).addClass('form-control-success');
    $(retypePasswordErr).html('');
    return true;
}