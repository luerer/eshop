<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: luerer
  Date: 03/07/2017
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script type="text/javascript" src="/js/jquery-1.8.3.min.js" ></script>
<script type="text/javascript">
    function deleteUser(username) {
        if(window.confirm("确认删除用户 " + username +" 吗?" )) {

            var name = {"username":username};
            $.ajax({
                async: false,
                url: '/admin/deleteUser',
                type: 'POST',
                data: name,
                scriptCharset: 'utf-8',
                success: function (message) {
                    alert(message);
                    location.reload(true);
                }
            });
        }
    }
    function updateUser (username) {
        if(window.confirm("确认更新用户 "+username+" 吗？")){

            var id = document.getElementById("id_" + username).value;
            var gender = document.getElementById("gender_" + username).value;
            var address = document.getElementById("address_" + username).value;
            var phone = document.getElementById("phone_" + username).value;
            if(phone.length == 0){
                alert("更新的电话号码不能为空");
                return;
            }
            var user = {
                "username":username,
                "id":id,
                "gender":gender,
                "phone":phone,
                "address":address,
                "password":"1234"
            };
            $.ajax({
                async: false,
                url: '/admin/updateUser',
                type: 'POST',
                data: user,
                scriptCharset: 'utf-8',
                success: function (message) {
                    alert(message);
                    location.reload(true);
                }
            });
        }

    }
    function addUser() {
        if(window.confirm("确认添加用户?(初始密码：1234)")){
            var username = document.getElementById("username").value;
            var id = 2;
            var gender = document.getElementById("gender").value;
            var phone = document.getElementById("phone").value;
            var address = document.getElementById("address").value;
            if(username.length==0){
                alert("用户名不能为空");
                return;
            }
            if(phone.length!=11){
                alert("电话必须为11位");
                return;
            }


            var user = {
                "username":username,
                "id":id,
                "gender":gender,
                "password":"1234",
                "address":address,
                "phone":phone
            };
            $.ajax({
                async: false,
                url: '/admin/addUser',
                type: 'POST',
                data: user,
                scriptCharset: 'utf-8',
                success: function (message) {
                    alert(message);
                    location.reload(true);
                }
            });
        }

    }

</script>
<head>
    <title>管理员页面</title>
</head>
<body>
<span>管理员：${user.username}，<a href="<c:url value="/login/logout"/>">退出</a>,
    <a href="<c:url value="/home"/>">商城首页</a>
</span>
<hr/>
<%--<a href="<c:url value="/admin/adduser"/>">添加新用户</a><br/>--%>
<div id="users">
    <table width="1200" align="center" border="1">
        <tr>
            <td width="200">账号类型</td>
            <td width="200">用户名</td>
            <td width="200">电话</td>
            <td width="200">性别</td>
            <td width="200">地址</td>
            <td width="200">操作</td>
        </tr>
        <c:choose>
        <c:when test="${userList le null }">
            <tr>
                <td colspan="6">目前还没有用户数据</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach items="${userList}" var="cur">
                    <c:if test="${cur.id==1 or cur.id==2}">
                    <form method="post" id="${cur.username}">
                        <tr>
                            <td width="200"><select id="id_${cur.username}" name="id">
                                <option value="1" <c:if test="${cur.id==1}"> selected="selected"</c:if>>商家</option>
                                <option value="2" <c:if test="${cur.id==2}"> selected="selected"</c:if>>买家</option>
                            </select></td>
                            <td width="200"><input style="text-align: center;" id ="username_${cur.username}"  name="username" value="${cur.username}" readonly="true"/></td>
                            <td width="200"><input name="phone" id = "phone_${cur.username}" value="${cur.phone}"/></td>
                            <td width="200"><select id="gender_${cur.username}" name="gender">
                                <option value="男" <c:if test="${cur.gender=='男'}"> selected="selected"</c:if>>男</option>
                                <option value="女" <c:if test="${cur.gender=='女'}"> selected="selected"</c:if>>女</option>
                            </select></td>
                            <td width="200"><input style="text-align: center;" id ="address_${cur.username}"  name="address" value="${cur.address}"/></td>
                            <td width="200"><span>
                                <input type="submit" onclick="updateUser('${cur.username}')" value="更新"/>
                                <input type="submit" onclick="deleteUser('${cur.username}')" value="删除"/>
                            </span></td>
                        </tr>
                    </form>
                    </c:if>
            </c:forEach>
        </c:otherwise>
        </c:choose>
        <tr>
            <td><input style="text-align: center;" id ="id"  name="username" value="买家" readonly="true"/></td>
            <td><input id="username" /></td>
            <td><input id="phone" /></td>
            <td><select id="gender">
                <option value="男" selected="selected">男</option>
                <option value="女" selected="selected">女</option>
            </select></td>
            <td><input id="address" /></td>
            <td><input type="submit" onclick="addUser()" value="添加用户"/></td>
        </tr>

    </table>
</div>

</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
