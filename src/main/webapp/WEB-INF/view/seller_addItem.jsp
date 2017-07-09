<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%--
  Created by IntelliJ IDEA.
  User: luerer
  Date: 06/07/2017
  Time: 9:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
</head>
<body>
<script>
    function addType() {
        var add_type = document.getElementById("add_type").value;
        if(!window.confirm("确认添加商品种类："+add_type)){
            return;
        }
        var newType={
            "newType":add_type
        };
        $.ajax({
            async: false,
            url: '/seller/addType',
            type: 'POST',
            data: newType,
            scriptCharset: 'utf-8',
            success: function (message) {
                alert(message);
                location.reload(true);
            }
        });

    }


</script>
添加商品|<a href="/seller">返回</a>
<hr/>
<form enctype="multipart/form-data" id="addConfirm" method="post" action="/seller/addConfirm">
<table id="detail-panel" border="1">
    <tr>
        <td>商品名称：</td>
        <td>
            <input style="text-align: center;" id ="item_name"  name="item_name"  />
        </td>
    </tr>
    <tr>
        <td>商品展示：</td>
        <td>
            <input id="pic_info" name="pic_info" type="file" />
        </td>
    </tr>
    <tr>
        <td>商品类别：</td>
        <td><span>
            <select id="item_type" name="item_type">
                <c:forEach var="type" items="${typeList}">
                    <option value="${type.type_name}" >${type.type_name}</option>
                </c:forEach>
            </select>
            或者添加：
            <input id="add_type"/>
            <input type="submit" onclick="addType()" value="提交"/>
        </span></td>
    </tr>
    <tr>
        <td>商品描述：</td>
        <td>
            <input style="text-align: center;" id ="item_info"  name="item_info"  />
        </td>
    </tr>
    <tr>
        <td>商品价格：</td>
        <td>
            <input style="text-align: center;" id="item_price" name="item_price" />
        </td>
    </tr>
    <tr>
        <td>商品库存：</td>
        <td>
            <input style="text-align: center;" id ="item_stock"  name="item_stock"  />
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="添加商品" />
        </td>
    </tr>

</table>
</form>
</body>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>
