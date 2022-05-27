<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <style>
        #l1{
            font-size: 30px;
        }
       #p1{
            font-size: 25px;
        }
       #in1{
           width: 200px;
           height: 30px;
       }
        #in2{
            width: 200px;
            height: 30px;
        }
        #p2{
            font-size: 25px;
        }
    </style>

</head>


<body>
<div align="center">
    <form action="${pageContext.request.contextPath}/d" method="post">
        <h1 id="l1">用户登录</h1>
        <table>
            <tr>
                <td><p id="p1">账号</p></td>
                <td><input type="text" name="username" placeholder="请输入账号" id="in1"> </td>
            </tr>
            <tr>
                <td><p id="p2">密码</p></td>
                <td><input type="password" name="password" placeholder="请输入密码" id="in2"> </td>
            </tr>
            <tr>
                <td><input type="submit" value="登录" ></td>
                <td><input type="submit" value="注册"  onclick="function fun() {

                }"></td>
                <td><input type="submit" value="管理员登录" ></td>
                <td> </td>
            </tr>
        </table>
    </form>
</div>
</body>
