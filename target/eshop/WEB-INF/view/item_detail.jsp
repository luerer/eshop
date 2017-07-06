<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tagform" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%--
  Created by IntelliJ IDEA.
  User: luerer
  Date: 06/07/2017
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style type="text/css">
    #PageBody{width:100%;}
    #Sidebar{float:left;width:10%;}
    #MainBody{float:right;width:90%;}
</style>
<head>
    <title>商品详情</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/default.css">
    <script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>

</head>
<body>
<script>

    $(document).ready(function(){
        //获得文本框对象
        var t = $("#text_box");
        //初始化数量为1,并失效减
        $('#min').attr('disabled',true);
        //数量增加操作
        $("#add").click(function(){
            t.val(parseInt(t.val())+1)
            if (parseInt(t.val())!=1){
                $('#min').attr('disabled',false);
            }
            if(parseInt(t.val())==${item.item_stock}){
                $('#add').attr('disabled',true);
            }
        })
        //数量减少操作
        $("#min").click(function(){
            t.val(parseInt(t.val())-1);
            if (parseInt(t.val())==1){
                $('#min').attr('disabled',true);
            }
            if(parseInt(t.val())!=${item.item_stock}){
                $('#add').attr('disabled',false);
            }

        })
    });


    function buyItem() {
        var number = document.getElementById("text_box").value;
        var stock = ${item.item_stock};
        var item_id = ${item.item_id};
        if(${user.username==null}){
            alert("请先登录。");
            return;
        }
        if(${user.id!=2}){
            alert("对不起，您不是买家。");
            return;
        }
        if(number>stock){
            alert("对不起，库存不够");
            return;
        }
        var item_num = {
            "item_num":number,
            "item_id":item_id
        }
        $.ajax({
            async: false,
            url: '/item/buy',
            type: 'POST',
            data: item_num,
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
    <table id="detail-panel">
        <tr>
            <td>
                商品名称：
            </td>
            <td>
                ${item.item_name}
            </td>
        </tr>
        <tr>
            <td>
                商品展示：
            </td>
            <td>
                <img src="/img/${item.item_pic}.jpg" onerror="this.src='/img/blank.jpg'">
            </td>
        </tr>
        <tr>
            <td>
                商品类别：
            </td>
            <td>
                ${item.item_type}
            </td>
        </tr>
        <tr>
            <td>
                商品描述：
            </td>
            <td>
                ${item.item_info}
            </td>
        </tr>
        <tr>
            <td>
                商品价格：
            </td>
            <td>
                ￥<fmt:formatNumber pattern=".00" value="${item.item_price}"></fmt:formatNumber>
            </td>
        </tr>
        <tr>
            <td>
                商品库存：
            </td>
            <td>
                还剩：${item.item_stock}
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" onclick="buyItem()" value="购买：" <c:if test="${item.item_stock==0}">disabled="disabled"</c:if>/>
            </td>
            <td>
                <span>
                <input id="min" name="" type="button" value="-" />
                <input id="text_box" name="" type="text" value="1" style="width:30px;"/>
                <input id="add" name="" type="button" value="+" />
                </span>
            </td>
        </tr>


    </table>

</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>
