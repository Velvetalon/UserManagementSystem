<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<%-- 阻止浏览器直接访问列表页。 --%>
<%
    //出现如下情况之一时，跳转到userlistServlet：
    //  ·用户直接访问list.jsp
    //  ·用户未登录
    if (!"allow".equals(session.getAttribute("access"))) {
        response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        return;
    } else if (request.getAttribute("users") == null) {
        response.sendRedirect(request.getContextPath() + "/userlist");
        return;
    }
%>
<%--存储虚拟路径--%>
<script>contextPath = "<%=request.getContextPath()%>"</script>

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
    <title>用户信息管理系统</title>

    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/jquery-1.8.3.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>

    <script src="./js/list.js"></script>

    <link rel="stylesheet" href="./css/icheck-bootstrap.min.css">


    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left;">

        <form class="form-inline">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" class="form-control" id="exampleInputName2" value="${query_name}">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" class="form-control" id="exampleInputName3" value="${query_birthplace}">
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" class="form-control" id="exampleInputEmail2" value="${query_email}">
            </div>
            <button type="button" class="btn btn-default" id="fuzzy_query_button" onclick="fuzzy_query(5)">查询</button>
        </form>

    </div>

    <div style="float: right;margin: 5px;">
        <button class="btn btn-primary" id="remove_select" type="button">删除选中</button>
    </div>

    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>
                <div class="checkbox icheck-primary">
                    <input type="checkbox" id="select_all"/>
                    <label for="select_all"></label>
                </div>
            </th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${users}" var="user" varStatus="sta">
            <tr>
                <td>
                    <div class="checkbox icheck-default">
                        <input type="checkbox" class="remove_box" id="remove_box${user.uid}" onclick="select_checkbox()"
                               data-user-id="${user.uid}"/>
                        <label for="remove_box${user.uid}"></label>
                    </div>
                </td>
                <td>${user.uid}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.birthplace}</td>
                <td>${user.qq_number}</td>
                <td>${user.email}</td>
                <td>
                    <button class="btn btn-default btn-sm" type="button" onclick="update(${user.uid})">修改</button>&nbsp;
                    <button class="btn btn-default btn-sm" type="button" onclick="remove(${user.uid})">删除</button>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="9" align="center"><a class="btn btn-primary" href="" id="add">添加联系人</a></td>
        </tr>
    </table>

    <div class="btn-group" role="group" aria-label="...">
        <c:choose>
            <c:when test="${current_page != 1}">
                <button class="btn btn-info" type="button"
                        onclick="last_page(${current_page},5)">&lsaquo;
                </button>
            </c:when>
            <c:when test="${current_page == 1}">
                <button class="btn btn-info" type="button">&lsaquo;</button>
            </c:when>
        </c:choose>
        <c:forEach begin="1" end="${total_page}" step="1" var="i">
            <c:choose>
                <c:when test="${i != current_page}">
                    <button class="btn btn-default"
                            data-rows="5" onclick="page(${i},5)">${i}</button>
                </c:when>
                <c:when test="${i == current_page}">
                    <button class="btn btn-primary" type="button">${i}</button>
                </c:when>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${current_page != total_page}">
                <button class="btn btn-info" type="button" onclick="next_page(${current_page},5)">&rsaquo;
                </button>
            </c:when>
            <c:when test="${current_page == total_page}">
                <button type="button" class="btn btn-info">&rsaquo;</button>
            </c:when>
        </c:choose>
        <%--    total_page current_page rows total_users    --%>
        <strong>共${total_users}条数据，共${total_page}页</strong>
    </div>
</div>
</body>

</html>
