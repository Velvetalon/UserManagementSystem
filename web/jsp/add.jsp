<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 网页使用的语言 -->
<html lang="zh-CN">

<%
    //出现如下情况之一时，跳转到userlistServlet：
    //  ·用户直接访问list.jsp
    //  ·用户未登录
    if (!"allow".equals(session.getAttribute("access"))) {
        response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        return;
    }
%>

<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-1.8.3.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <script src="../js/add.js"></script>
</head>
<body>
<div class="container">
    <div style="text-align: center"><h3>添加联系人页面</h3></div>
    <form action="" method="post" onsubmit="return false;">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" class="gender" value="男" checked="checked"/>男
            <input type="radio" name="gender" class="gender" value="女"/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄">
        </div>

        <div class="form-group">
            <label for="birthplace">籍贯：</label>
            <select name="birthplace" class="form-control" id="birthplace">
                <option value="陕西">陕西</option>
                <option value="北京">北京</option>
                <option value="上海">上海</option>
            </select>
        </div>

        <div class="form-group">
            <label for="qq_number">QQ：</label>
            <input type="text" class="form-control" id="qq_number" name="qq_number" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <a class="btn btn-primary" href="javascript:void(0);" id="submit">提交</a>
            <button class="btn btn-default" type="reset">重置</button>
            <a class="btn btn-default" href="javascript:void(0);" id="back">返回</a>
        </div>
    </form>
</div>
</body>
</html>