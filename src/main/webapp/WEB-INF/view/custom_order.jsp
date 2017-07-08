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
    function receiveItem(order_id) {
        if(!window.confirm("确认收货？")){
            return;
        }
        var password = prompt("确认密码","");
        var old = "${user.password}";
        if(old!==password){
            alert("密码不正确");
            return;
        }
        var order={
            "order_id":order_id
        }
        $.ajax({
            async: false,
            url: '/custom/receiveItem',
            type: 'POST',
            data: order,
            scriptCharset: 'utf-8',
            success: function (message) {
                alert(message);
                location.reload(true);
            }
        });
    }

    function returnItem(order_id) {
        if(!window.confirm("确认申请退款？")){
            return;
        }
        var msg = prompt("填写退款理由");
        if(msg==null){
            alert("理由不能为空。");
            return;
        }
        var order={
            "order_id":order_id,
            "msg":msg
        };
        $.ajax({
            async: false,
            url: '/custom/returnItem',
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
            <li role="presentation"><a href="/custom">个人信息</a></li>
            <li role="presentation" class="active"><a href="/custom/order">订单查看</a></li>
            <li role="presentation"><a href="/custom/cart">购物车</a></li>
        </ul>
    </div>
    <div id="MainBody">
        <table id="orders" align="center" border="1">
            <tr>
                <th>订单号</th>
                <th>商品种类</th>
                <th>商品名称</th>
                <th>商品单价</th>
                <th>购买数量</th>
                <th>收货人</th>
                <th>联系电话</th>
                <th>收货地址</th>
                <th>订单时间</th>
                <th>状态</th>
                <th>备注</th>
            </tr>
            <c:if test="${orderList==null}">
                <td colspan="11">没有订单信息</td>
            </c:if>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.item_type}</td>
                    <td>${order.item_name}</td>
                    <td>${order.item_price}</td>
                    <td>${order.item_num}</td>
                    <td>${order.custom_name}</td>
                    <td>${order.custom_phone}</td>
                    <td>${order.custom_address}</td>
                    <td>${order.time}</td>
                    <td>
                        <c:choose>
                            <c:when test="${order.status==0}">
                                <c:choose>
                                    <c:when test="${order.send==false}">
                                        <input type="button" disabled="disabled" value="等待发货" />
                                    </c:when>
                                    <c:when test="${order.send==true}">
                                        <c:choose>
                                            <c:when test="${order.receive==false}">
                                                <input type="submit" onclick="receiveItem(${order.id})" value="确认收货"/>
                                                <input type="submit" onclick="returnItem(${order.id})" value="申请退款" />
                                            </c:when>
                                            <c:otherwise>
                                                <input type="button" disabled="disabled" value="已收货"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                </c:choose>
                            </c:when>
                            <c:when test="${order.status==1}">
                                <input type="button" disabled="disabled" value="订单被取消" />
                            </c:when>
                            <c:when test="${order.status==2}">
                                <input type="button" disabled="disabled" value="卖家正在处理"/>
                            </c:when>
                            <c:otherwise>
                                <input type="button" value="订单已关闭" disabled="disabled"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${order.status==0}">
                                订单正常。
                                <c:if test="${order.send==true}">快递：${order.msg}</c:if>
                            </c:when>
                            <c:when test="${order.status==1}">
                                卖家取消订单，原因：${order.msg}
                            </c:when>
                            <c:when test="${order.status==2}">
                                已经申请退款，理由：${order.msg}
                            </c:when>
                            <c:otherwise>
                                订单已关闭。${order.msg}
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