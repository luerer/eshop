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
        var msg = prompt("请输入快递单号","")
        if(isNaN(msg))
        {
            alert("请输入有效快递单号");
            return;
        }
        if(!window.confirm("确认发货？单号："+msg)){
            return;
        }
        var order={
            "order_id":order_id,
            "msg":msg
        };
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
    function rejectItem(order_id) {
        var msg = prompt("请输入原因","");
        if(msg==null){
            alert("原因不能为空");
        }
        if(!window.confirm("确认拒绝订单，原因："+msg)){
            return;
        }
        var reject_info={
            "order_id":order_id,
            "msg":msg
        };
        $.ajax({
            async: false,
            url: '/seller/rejectItem',
            type: 'POST',
            data: reject_info,
            scriptCharset: 'utf-8',
            success: function (message) {
                alert(message);
                location.reload(true);
            }
        });
    }
    function confirmReturn(order_id) {
        if(!window.confirm("同意退款？")){
            return;
        }
        var password;
        if(!(password = prompt("确认密码",""))){
            return;
        }
        var old = "${user.password}";
        if(old!==password){
            alert("密码不正确");
            return;
        }
        var order={
            "order_id":order_id
        };
        $.ajax({
            async: false,
            url: '/seller/confirmReturn',
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
            您好：<a href="/seller">${user.username}</a>,
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
            <li role="presentation" class="active"><a href="/seller/order">订单管理</a></li>
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
                                        <input type="submit" onclick="sendItem(${order.id})" value="发货"/>
                                        <input type="submit" onclick="rejectItem(${order.id})" value="拒绝"/>
                                    </c:when>
                                    <c:when test="${order.send==true}">
                                        <input type="button" disabled="disabled" value="已发货"/>
                                        <c:if test="${order.receive==true}">
                                            <input type="button" disabled="disabled" value="已收货"/>
                                        </c:if>
                                    </c:when>
                                </c:choose>
                            </c:when>
                            <c:when test="${order.status==1}">
                                <input type="button" disabled="disabled" value="已拒绝" />
                            </c:when>
                            <c:when test="${order.status==2}">
                                <input type="submit" onclick="confirmReturn(${order.id})" value="同意退款"/>
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
                                已拒绝，原因：${order.msg}
                            </c:when>
                            <c:when test="${order.status==2}">
                                买家申请退款，理由：${order.msg}
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