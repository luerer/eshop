<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tagform" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
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
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/default.css">
    <script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <style type="text/css">
        #PageBody{width:100%;}
        #Sidebar{float:left;width:10%;}
        #MainBody{float:right;width:90%;}
    </style>
</head>
<body>

<div class="container" id="tobelog">
    <table>
        <a href="<c:url value="/home/login"/>">登录</a>|
        <a href="<c:url value="/home/register"/>">注册</a>
    </table>
</div>
<script>
    if(${user.is_default==true}){
        alert("新用户请尽快到个人主页修改密码！");
    }

    if(${user.username!=null}){
        var str = "您好：";
        var link;
        var id = "";
        if(${user.id==0}){
            id = "管理员。";
            link = '<a href="<c:url value="/admin"/>">${user.username}</a>';
        }
        else if (${user.id==1}){
            id = "商家。";
            link = '<a href="<c:url value="/seller"/>">${user.username}</a>';
        }
        else if (${user.id==2}){
            id = "买家。";
            link = '<a href="<c:url value="/custom"/>">${user.username}</a>';
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

<div id="PageBody">
    <ul class="nav nav-tabs">
        <li role="presentation" <c:if test="${default_type==null}"> class="active" </c:if> ><a href="/home" >全部</a></li>
        <c:forEach var="type" items="${typeList}">
            <li role="presentation" <c:if test="${default_type==type.type_name}"> class="active" </c:if> ><a href="/item/${type.type_name}">${type.type_name}</a></li>
        </c:forEach>
    </ul>
        <div id="items">
            <c:if test="${empty itemList}">没有搜索到商品。</c:if>
            <div class="row">
            <c:forEach items="${itemList}" var="item">
                <div class="col-sm-3">
                    <a href="/items/${item.item_id}" class="thumbnail">
                    <img src="/img/'${item.item_pic}'.jpg" onerror="this.src='/img/blank.jpg'">
                        ${item.item_name} ￥<fmt:formatNumber pattern=".00" value="${item.item_price}"></fmt:formatNumber>
                    </a>
                </div>
            </div>
            </c:forEach>
        </div>
</div>

</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>


