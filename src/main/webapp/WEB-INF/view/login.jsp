<%--
  Created by IntelliJ IDEA.
  User: luerer
  Date: 28/06/2017
  Time: 3:11 PM
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
<%--
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.0/jquery.min.js"></script>
--%>
    <base href="<%=basePath%>">

    <title>用户登录</title>

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
<h2 align="center">用户登录</h2>
<hr/>
<form action="/login.do" method="post" name="userinfo">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密&nbsp;&nbsp;&nbsp;码：</td>
            <td><input type="password" name="password"> </td>
        </tr>
        <%--<tr>
            <td colspan="2"> <input type="checkbox"><span>十天内记住登录状态</span> </td>
        </tr>--%>
        <tr>
            <td colspan="2" align="center"><input type="submit" name = "login" value="登录"></td>
        </tr>
    </table>
</form>

<%--<script type="text/javascript">
        $(document).ready(function() {
            $("#login").click(function() {
                var username = $("#username").val();
                var password = $("#password").val();
                var user = {
                    number : username,
                    password : password
                };//拼装成json格式

                $.ajax({
                    type : "POST",
                    url : "http://localhost:8080/home/login",
                    data : JSON.stringify(user),
                    contentType : 'application/json;charset=utf-8',
                    dataType : 'json',
                    success : function(data) {

                        if (data.code == '0') {
                            window.location.href = "/iswust2hand/index.jsp";
                            alert("欢迎使用西科二手平台！");
                        }else{
                            alert("密码错误，请确认后重新登录！");
                        }

                    },

                    error : function(data) {
                        alert("出错：" + data.code);
                    }

                });

            });
        });

</script>--%>
</body>

</html>