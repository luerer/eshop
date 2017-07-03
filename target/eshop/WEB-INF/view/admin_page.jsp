<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: luerer
  Date: 03/07/2017
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript" src="tatics/js/jquery-1.8.3.min.js" ></script>

<head>
    <title>管理员页面</title>
</head>
<body>
<span>管理员：${user.username}，<a href="<c:url value="/login/logout"/>">退出</a></span>
<hr/>
<a href="<c:url value="/admin/adduser"/>">添加新用户</a><br/>
<div id="users">
    <table width="1200" align="center" border="1">
        <tr>
            <td width="200">账号类型</td>
            <td width="200">用户名</td>
            <td width="200">用户密码</td>
            <td width="200">性别</td>
            <td width="200">地址</td>
            <td width="200">操作</td>
        </tr>
        <c:choose>
        <c:when test="${userList le null }">
            <tr>
                <td colspan="6">目前还没有用户数据</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${userList}" var="cur">
                <c:choose>
                    <c:when test="${cur.id==1}">
                        <tr>
                            <td width="200">商家</td>
                            <td width="200">${cur.username}</td>
                            <td width="200">${cur.password}</td>
                            <td width="200">${cur.gender}</td>
                            <td width="200">${cur.address}</td>
                            <td width="200">更新 删除</td>
                        </tr>
                    </c:when>
                    <c:when test="${cur.id==2}">
                        <tr>
                            <td width="200">买家</td>
                            <td width="200">${cur.username}</td>
                            <td width="200">${cur.password}</td>
                            <td width="200">${cur.gender}</td>
                            <td width="200">${cur.address}</td>
                            <td width="200">更新 删除</td>
                        </tr>
                    </c:when>
                </c:choose>
            </c:forEach>
        </c:otherwise>
        </c:choose>



    </table>
</div>
<div id="users">
    <c:forEach items="${userList}" var="user">
    <li id="user_<c:out value="user.username"/>">
        <div class="container">
            <c:out value="${user.username}"/>
        </div>
        <span><c:out value="${user.gender}"/></span>
        <span><c:out value="${user.address}"/></span>
</div>
</li>
</c:forEach>
</div>

</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
