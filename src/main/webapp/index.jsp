<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <script src="resources/libs/jquery-3.6.1.min.js"></script>
    <script>
        function gotoLogin(){
            console.log("index.jsp -> location.href = /auth/login.do");
            location.href = "/auth/login.do";
        }
    </script>
</head>
<body onload="gotoLogin()"></body>
</html>