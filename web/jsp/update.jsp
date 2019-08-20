<!DOCTYPE html>
<!-- 网页使用的语言 -->
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html lang="zh-CN">

<%
    //阻止对修改页的直接访问
    if(request.getAttribute("user") == null){
        response.sendRedirect(request.getContextPath()+"/updateUser");
        return;
    }
%>

<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/jquery-2.1.0.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>

    <script src="./js/update.js"></script>
</head>

<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="" method="post">
        <input type="hidden" name="uid" id="uid" value="${user.uid}"/>

        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" readonly="readonly" placeholder="请输入用户名" value="${user.name}"/>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="genders" class="gender" value="男" ${user.gender == "男" ? "checked" : ""}/>男
            <input type="radio" name="genders" class="gender" value="女" ${user.gender == "女" ? "checked" : ""}/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄" value="${user.age}"/>
        </div>

        <div class="form-group">
            <label for="birthplace">籍贯：</label>
            <select name="birthplace" id="birthplace" class="form-control">
                <option value="${user.birthplace}">${user.birthplace}</option>
                <option value="陕西" >陕西</option>
                <option value="北京" >北京</option>
                <option value="上海" >上海</option>
            </select>
        </div>

        <div class="form-group">
            <label for="qq_number">QQ：</label>
            <input type="text" id="qq_number" class="form-control" name="qq_number" placeholder="请输入QQ号码" value="${user.qq_number}"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" id="email" class="form-control" name="email" placeholder="请输入邮箱地址" value="${user.qq_number}"/>
        </div>

        <div class="form-group" style="text-align: center">
            <a class="btn btn-primary" id="submit">提交</a>
            <button type="reset" class="btn btn-default">重置</button>
            <a class="btn btn-default" id="back" >返回</a>
        </div>
    </form>
</div>
</body>
</html>