$(document).ready(function () {
    // 立即登录单击事件
    $("#btn-login").click(function (e) {
        login(e);
    });
});

function login(e) {
    var username = $("#nickname").val();
    var password = $("#password").val();

    if (!username) {
        alertBox("用户名不能为空！");
        return false;
    }
    if (!password) {
        alertBox("密码不能为空！");
        return false;
    }

    $.ajax({
        type: "POST",
        url: SERVER_PATH + "/user/login",
        data: {
            "username": username,
            "password": password
        },
        /*  xhrFields: {withCredentials: true},*/
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
            alertBox("登录成功！", function () {
                window.location.href = "index.html";
            });
        }
    });
}