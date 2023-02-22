$(document).ready(function () {

    // 立即注册单击事件
    $("#btn-register").click(function (e) {
        register(e);
    });
});


function register(e) {
    var phone = $("#phone").val();
    var password = $("#password").val();
    var password_confirm = $("#password-confirm").val();
    var nickname = $("#nickname").val();
    var age = $("#age").val();

    alert("进来了");

    if (!phone) {
        alertBox("手机号不能为空！");
        return false;
    }
    if (!password) {
        alertBox("密码不能为空！");
        return false;
    }
    if (!password_confirm || password_confirm != password) {
        alertBox("两次输入的密码不一致！");
        return false;
    }
    if (!nickname) {
        alertBox("昵称不能为空！");
        return false;
    }
    if (!age) {
        alertBox("年龄不能为空！");
        return false;
    }

    // $.ajax({
    //     type: "POST",
    //     url: SERVER_PATH + "/user/register",
    //     data: {
    //         "phone": phone,
    //         "password": password,
    //         "username": nickname,
    //         "age": age
    //     },
    //     success: function (result) {
    //         if (result.status) {
    //             alert(result.data.message);
    //             return false;
    //         }
    //         alertBox("注册成功！", function(){
    //             window.location.href = "login.html";
    //         });
    //     }
    // });
}