window.onload = function (ev) {
    var failed_msg = document.getElementById("failed_msg")
    var login_msg = document.getElementById("login_msg")
    var submit_btn = document.getElementById("submit_btn")
    var check_image = document.getElementById("check_image");
    submit_btn.onclick = function () {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var checkcode = document.getElementById("checkcode").value;
        var remember = document.getElementById("remember").value;

        login_msg.innerText = "正在登陆..."
        $.post("/wop/loginServlet", {
            username: username,
            password: password,
            checkcode: checkcode,
            remember: remember
        }, result, "json");
    }

    function result(data) {
        login_msg.innerText = "";
        if (data["success"] === "success") {
            alert("登陆成功！即将为您跳转首页！");
            location.href = "/wop/jsp/list.jsp"
        } else {
            //修改提示信息并显示提示信息
            failed_msg.innerText = "登陆失败，" + data["msg"];
            setMsgBox(true);
            flushCheckImage()
        }
    }

    document.getElementById("close_btn").onclick = function (ev1) {
        setMsgBox(false);
    }

    function flushCheckImage() {
        document.getElementById("checkcode").value=""
        check_image.src = "../checkcodeServlet?time=" + new Date().getTime();
    }

    //设置提示框的显示状态
    function setMsgBox(flag) {
        if (flag) {
            document.getElementById("msg_box").className = "NONE_STYLE"
        } else {
            document.getElementById("msg_box").className = "hide"
        }
    }

    check_image.onclick = flushCheckImage
}