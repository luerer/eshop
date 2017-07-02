<%--
  Created by IntelliJ IDEA.
  User: luerer
  Date: 29/06/2017
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>用户注册</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
        span
        {
            color: #CCC;
        }
        table
        {
            margin-top:10%;
            margin-left: 40%;
            margin-right: 40%;
            margin-bottom: 30%
        }
    </style>
</head>

<body>
<h2 align="center">用户注册</h2>
<hr/>
<a>注册页面</a>
</body>
</html>