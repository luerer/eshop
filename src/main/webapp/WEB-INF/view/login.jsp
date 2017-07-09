<%--
  Created by IntelliJ IDEA.
  User: luerer
  Date: 28/06/2017
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%--
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.0/jquery.min.js"></script>
--%>

    <title>用户登录</title>


</head>

<body>
<h2 align="center">用户登录</h2>
<hr/>
<div style="margin-left: auto;margin-right: auto;max-width: 384px;">
<sf:form action="/login" method="post" name="userinfo" modelAttribute="user">
    <sf:errors path="*" element="div" /><br/>
    <div>
        <span>用户名：</span>
        <sf:input path="username"></sf:input>
    </div>
    <div>
        <span>密码：</span>
        <sf:password path="password"></sf:password>
    </div>
    <input type="submit" class="form-control" value="登录">
</sf:form>
</div>
</body>

</html>