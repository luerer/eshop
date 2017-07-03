<%--
  Created by IntelliJ IDEA.
  User: luerer
  Date: 03/07/2017
  Time: 5:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js" ></script>
<script type="text/javascript">

</script>
<head>
    <title>添加用户</title>
</head>
<body>
<sf:form method="post" modelAttribute="user">
    <table width="700" align="center" border="1">
        <tr>
            <td>用户名:</td><td><sf:input path="username"/><sf:errors path="username"/></td>
        </tr>
        <tr>
            <td>用户密码:</td><td><sf:password path="password"/><sf:errors path="password"/></td>
        </tr>
        <tr>
            <td>用户性别:</td><td><sf:select path="gender">
            <sf:option value="男">男</sf:option>
            <sf:option value="女">女</sf:option>
            </sf:select></td>
        </tr>
        <tr>
            <td>用户地址:</td><td><sf:input path="address"/></td>
        </tr>
        <tr>
            <td>账号类型:</td><td><sf:select id="id" path="id">
            <sf:option value="0">管理员</sf:option>
            <sf:option value="1">商家</sf:option>
            <sf:option value="2">买家</sf:option>
            </sf:select></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="用户添加"/>
            </td>
        </tr>
    </table>
</sf:form>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
