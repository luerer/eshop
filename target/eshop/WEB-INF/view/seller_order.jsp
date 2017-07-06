<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: luerer
  Date: 03/07/2017
  Time: 9:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>商家页面</title>
    <style type="text/css">
        #PageBody{width:100%;}
        #Sidebar{float:left;width:10%;}
        #MainBody{float:right;width:90%;}
    </style>
</head>
<body>
<script>
    function sendItem(order_id) {
        if(!window.confirm("确认发货？")){
            return;
        }
        var order={
            "order_id":order_id
        }
        $.ajax({
            async: false,
            url: '/seller/sendItem',
            type: 'POST',
            data: order,
            scriptCharset: 'utf-8',
            success: function (message) {
                alert(message);
                location.reload(true);
            }
        });

    }
</script>
<div class="container" id="tobelog">
    <c:choose>
        <c:when test="${user==null}">
            <a href="<c:url value="/home/login"/>">登录</a>|
            <a href="<c:url value="/home/register"/>">注册</a>
        </c:when>
        <c:when test="${user!=null}">
            您好：<a href="/custom">${user.username}</a>,
            <a href="/login/logout">退出</a>|
            <a href="/home">返回首页</a>
        </c:when>
    </c:choose>
</div>
<hr/>

<div id="PageBody">
    <div id ="Sidebar">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="/seller">商品管理</a></li>
            <li role="presentation" class="active"><a href="seller/order">订单管理</a></li>
        </ul>
    </div>
    <div id="MainBody">
        <table id="orders" align="center" border="1">
            <tr>
                <th>订单号</th>
                <th>商品名称</th>
                <th>买家名字</th>
                <th>购买数量</th>
                <th>收货地址</th>
                <th>状态</th>
            </tr>
            <c:if test="${seller_orders==null}">
                <td colspan="6">没有订单信息</td>
            </c:if>
            <c:forEach var="seller_order" items="${seller_orders}">
                <tr>
                    <td>${seller_order.order_id}</td>
                    <td>${seller_order.order_item}</td>
                    <td>${seller_order.order_custom}</td>
                    <td>${seller_order.order_num}</td>
                    <td>${seller_order.order_address}</td>
                    <td>
                        <c:choose>
                            <c:when test="${seller_order.order_status==false}">
                                <input type="submit" onclick="sendItem(${seller_order.order_id})" value="发货"/>
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="已发货" disabled="disabled"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>