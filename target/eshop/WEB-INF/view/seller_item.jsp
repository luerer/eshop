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
    function deleteItem(item_id) {
        if(!window.confirm("确定删除商品吗？")){
            return;
        }
        var itemId={
            "item_id":item_id
        }
        $.ajax({
            async: false,
            url: '/seller/deleteItem',
            type: 'POST',
            data: itemId,
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
            <li role="presentation" class="active"><a href="/seller">商品管理</a></li>
            <li role="presentation"><a href="/seller/order">订单管理</a></li>
        </ul>
    </div>
    <div id="MainBody">
        <table id="detail-panel" border="1">
            <tr>
                <th>商品种类</th>
                <th>商品名称</th>
                <th>商品价格</th>
                <th>剩余库存</th>
                <th>详细描述</th>
                <th>操作</th>
            </tr>
            <c:if test="${itemList==null}">
                <td colspan="6">没有商品信息</td>
            </c:if>
            <c:forEach var="item" items="${itemList}">
                <tr>
                    <td>${item.item_type}</td>
                    <td>${item.item_name}</td>
                    <td>${item.item_price}</td>
                    <td>${item.item_stock}</td>
                    <td>${item.item_info}</td>
                    <td>
                        <span>
                            <button onclick="location.href='/seller/updateItem/${item.item_id}'"  class="btn">修改商品</button>
                            <input type="submit" onclick="deleteItem(${item.item_id})" value="删除"/>
                        </span>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="7">
                    <button onclick="location.href='/seller/addItem'"  class="btn">添加新商品</button>
                </td>
            </tr>
        </table>
    </div>
</div>

</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>