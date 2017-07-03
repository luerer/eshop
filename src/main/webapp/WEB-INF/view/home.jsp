<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: luerer
  Date: 29/06/2017
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>这是严肃的主页</title>
    <%--<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.min.js">

    </script>--%>
</head>
<body>

<div class="container" id="tobelog">
    <table>
        <a href="<c:url value="/home/login"/>">登录</a>|
        <a href="<c:url value="/home/register"/>">注册</a>
    </table>
</div>
<script>

    if(${user.username!=null}){
        var str = "您好：";
        var link;
        var id = "";
        if(${user.id==0}){
            id = "管理员。";
            link = '<a href="<c:url value="/login/admin"/>">${user.username}</a>';
        }
        else if (${user.id==1}){
            id = "商家。";
            link = '<a href="<c:url value="/login/seller"/>">${user.username}</a>';
        }
        else if (${user.id==2}){
            id = "买家。";
            link = '<a href="<c:url value="/login/custom"/>">${user.username}</a>';
        }
        else {
            id = "未知用户。";
            link = "${user.username}";
        }
        var logout = '<a href="<c:url value="/login/logout"/>">注销</a>'
        str+=(link+"，您的身份是："+id+logout);
        var div1 = document.getElementById("tobelog");
        div1.style.display='none';
        document.write(str);
    }
</script>

<hr/>

<div id="iterms">
<c:forEach items="${itermList}" var="iterm">
    <li id="iterm_<c:out value="iterm.iterm_id"/>">
        <div class="container">
            <c:out value="${iterm.iterm_name}"/>
        </div>
            <span><c:out value="${iterm.iterm_price}"/></span>
            <span><c:out value="${iterm.iterm_stock}"/></span>
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


