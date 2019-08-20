<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html lang="en">
    <%
        //如果用户已经登录，则跳转列表页
        if ("allow".equals(session.getAttribute("access"))) {
            response.sendRedirect(request.getContextPath() + "/userlist");
        }
    %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap 固定头部 -->
    <title>用户登录</title>

    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- 引入 jQuery 依赖 -->
    <script src="../js/jquery-3.2.1.min.js"></script>
    <!-- 加载 Bootstrap JavaScript 插件 -->
    <script src="../js/bootstrap.min.js"></script>
    <link href="../css/SharedStyle.css" rel="stylesheet">
</head>
<body>

<%-- 整体布局 --%>
<div class="container" style="width: 400px">
    <%--  标题  --%>
    <div class="row text-blue text-center">
        <h3>管理员登录</h3>
    </div>

    <%-- 登陆表单 --%>
    <div class="row text-center">
        <form action="" method="post" onsubmit="return false;">
            <div class="form-group text-left">
                <label for="username">用户名：</label>
                <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1"
                       id="username" name="username">
            </div>

            <div class="form-group text-left">
                <label for="password">密码：</label>
                <input type="password" class="form-control" placeholder="Password" aria-describedby="basic-addon2"
                       id="password" name="password">
            </div>

            <div class="form-inline text-left">
                <label for="checkcode">校验码：</label>
                <input type="text" class="form-control" placeholder="CheckCode" aria-describedby="basic-addon3"
                       id="checkcode" name="checkcode" style="width: 215px">
                <img src="../checkcodeServlet" class="img-rounded" id="check_image" alt="点击刷新验证码">
            </div>
            <div class="form-group text-right">
                <input type="checkbox" name="remember" id="remember"> 记住我
            </div>
            <div class="form-group">
                <button class="btn btn-primary" id="submit_btn">登录</button>
            </div>
        </form>
    </div>

    <div class="row text-center text-blue">
        <span id="login_msg"></span>
    </div>

    <%-- 错误提示框。正常状态下隐藏。 --%>
    <div class="row">
        <span id="msg_box" class="hide">
            <div class="alert alert-warning" role="alert">
                <span id="failed_msg">登录失败</span>
                <button class="close" id="close_btn">&times;</button>
            </div>
        </span>
    </div>

</div>

</body>
    <script src="../js/login.js"></script>
</html>