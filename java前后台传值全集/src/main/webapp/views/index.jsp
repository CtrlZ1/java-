<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2018/11/20
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
</head>
<body>
<center>
<form action="/getdata">
    <h2>从后台获取的用户名和密码：<%=request.getAttribute("user").toString()%></h2>
    <h2>${user.username}  ${user.password}</h2>
    <h2>${string}</h2>
    <h2>后台传过来的map${map.get("user1").toString()}</h2>
    <h2>后台传过来的列表${list.get(0)}</h2>
    <table>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username" id="username"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td><input type="button" value="提交" id="TJ"></td>
        </tr>
    </table>
</form>
</center>
<script type="text/javascript">
    var x1=${json_user}
    //alert(x1.password)
    var x2=${json_string}
    //alert(x2)
    var x3=${json_list}
    //alert(x3[0].username)
    var x4=eval("("+'${json_map}'+")")
    //alert(x4["user1"].password)
    //对象
    var user=new Object();
    //对象数组
    var list_of_user=[]
    $('#TJ').click(function(){
        user.username=$.trim($("#username").val());
        user.password=$.trim($("#password").val());
        list_of_user.push(user);
        list_of_user.push(user);
        $.ajax({
            type:'POST',
            url:'/getdata1',
            data:{ObjectUser:JSON.stringify(list_of_user)},
            dataType:'text',
            timeout:1000,
            success:function(result){
                if (result=="success")
                    window.location.href='views/success.jsp';
            }
        })
    })
</script>
</body>
</html>
