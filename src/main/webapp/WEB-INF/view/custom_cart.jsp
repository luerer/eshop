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
    <title>买家页面</title>
    <style type="text/css">
        #PageBody{width:100%;}
        #Sidebar{float:left;width:10%;}
        #MainBody{float:right;width:90%;}
    </style>
</head>
<body>
<script>
    function deleteCart(item_id,item_num) {
        if(!window.confirm("确认删除商品？")){
            return;
        }
        var cart={
            "item_id":item_id,
            "item_num":item_num
        };
        $.ajax({
            async: false,
            url: '/custom/deleteCart',
            type: 'POST',
            data: cart,
            scriptCharset: 'utf-8',
            success: function (message) {
                alert(message);
                location.reload(true);
            }
        });
    }

    function confirmCart() {
        var password;
        if(!(password = prompt("确认密码",""))){
            return;
        }
        var old = "${user.password}";
        if(old!==password){
            alert("密码不正确");
            return;
        }
        window.location.href="/custom/confirmCart";
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
            <li role="presentation"><a href="/custom">个人信息</a></li>
            <li role="presentation"><a href="/custom/order">订单查看</a></li>
            <li role="presentation" class="active"><a href="/custom/cart">购物车</a></li>
        </ul>
    </div>
    <div id="MainBody">
        <table id="orders" align="center" border="1">
            <tr>
                <th>商品名称</th>
                <th>商品单价</th>
                <th>购买数量</th>
                <th>收货人</th>
                <th>总价格</th>
                <th>操作</th>
            </tr>
            <c:choose>
                <c:when test="${cartList==null}">
                    <td colspan="6">购物车是空的。</td>
                </c:when>
                <c:otherwise>
                <c:forEach var="cart" items="${cartList}">
                    <tr>
                        <td>${cart.item_name}</td>
                        <td>${cart.item_price}</td>
                        <td>${cart.item_num}</td>
                        <td>${cart.custom_name}</td>
                        <td>${cart.item_price}*${cart.item_num}</td>
                        <td><input type="submit" onclick="deleteCart(${cart.item_id},${cart.item_num})" value="删除商品"/></td>
                    </tr>
                </c:forEach>

                <tr>
                    <td colspan="4">总计：</td>
                    <td id = "total" colspan="2">${total}</td>
                </tr>

                <tr>
                <td colspan="6">
                    <input type="submit" value="确认购买" onclick="confirmCart()"/>
                </td>
                </tr>
                </c:otherwise>
            </c:choose>
        </table>
    </div>
</div>

</body>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>