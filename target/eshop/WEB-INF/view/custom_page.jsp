<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: luerer
  Date: 05/07/2017
  Time: 1:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style type="text/css">
    #PageBody{width:100%;}
    #Sidebar{float:left;width:10%;}
    #MainBody{float:right;width:90%;}
</style>
<script type="text/javascript" src="/js/jquery-1.8.3.min.js" ></script>

<script type="text/javascript">
    function updateUserInfo(){
        var gender = document.getElementById("_gender").value;
        var address = document.getElementById("_address").value;
        var phone = document.getElementById("_phone").value;
        var username = document.getElementById("_username").value;
        if(address.length==0){
            alert("更新地址不能为空");
            return;
        }
        if(phone.length!=11){
            alert("电话必须为11位");
            return;
        }
        var user = {
            "username":username,
            "gender":gender,
            "phone":phone,
            "address":address,
            "id":2
        };
        $.ajax({
            async: false,
            url: '/custom/updateUserInfo',
            type: 'POST',
            data: user,
            scriptCharset: 'utf-8',
            success: function (message) {
                alert(message);
                location.reload(true);
            }
        });

    }
    function changePassword() {
        var old_password = document.getElementById("old_password").value;
        var new_password = document.getElementById("new_password").value;
        var confirm_password = document.getElementById("confirm_password").value;
        if(old_password.length==0||new_password.length==0||confirm_password.length==0){
            alert("密码不能为空");
            return;
        }
        if(confirm_password!=new_password){
            alert("两次密码不匹配，请重新输入。");
            return;
        }
        if(new_password==old_password){
            alert("新密码不能与旧密码相同。");
            return;
        }
        var changeP = {
            "old_password":old_password,
            "new_password":new_password
        }
        $.ajax({
            async: false,
            url: '/custom/changePassword',
            type: 'POST',
            data: changeP,
            scriptCharset: 'utf-8',
            success: function (message) {
                alert(message);
                window.location.href="/home";
            }
        });


    }
</script>
<head>
    <title>个人主页</title>
</head>
<body>
您好：<a href="/custom">${user.username}</a>,
<a href="/login/logout">退出</a>|
<a href="/home">返回首页</a>
<hr/>

<div id="PageBody">
    <div id ="Sidebar">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="/custom">个人信息</a></li>
            <li role="presentation"><a href="/custom/order">订单查看</a></li>
            <li role="presentation"><a href="/custom/caret">购物车</a></li>
        </ul>
    </div>
    <div id="MainBody">
        <table id="user_profile" style="margin:auto;">
            <tr>
                <td>
                    用户名：
                </td>
                <td>
                    <input style="text-align: center;" id ="_username"  name="username" value="${user.username}" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    性别：
                </td>
                <td>
                    <select id="_gender" name="gender">
                        <option value="男" <c:if test="${user.gender=='男'}"> selected="selected"</c:if>>男</option>
                        <option value="女" <c:if test="${user.gender=='女'}"> selected="selected"</c:if>>女</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    电话：
                </td>
                <td>
                    <input style="text-align: center;" id ="_phone"  name="phone" value="${user.phone}"/>
                </td>
            </tr>
            <tr>
                <td>
                    地址：
                </td>
                <td>
                    <input style="text-align: center;" id ="_address"  name="address" value="${user.address}"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" onclick="updateUserInfo()" value="更新信息"/>
                </td>
            </tr>
        </table>
        <hr/>
        <table id="change_password" style="margin:auto;">
            <tr>
                <td colspan="2">修改密码：</td>
            </tr>
            <tr>
                <td>
                    输入旧密码：
                </td>
                <td>
                    <input type="password" placeholder="old password" id="old_password"/>
                </td>
            </tr>
            <tr>
                <td>
                    输入新密码：
                </td>
                <td>
                    <input type="password" placeholder="new password" id="new_password"/>
                </td>
            </tr>
            <tr>
                <td>
                    确认新密码：
                </td>
                <td>
                    <input type="password" placeholder="confirm new password" id="confirm_password"/>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" onclick="changePassword()" value="修改密码"></td>
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
