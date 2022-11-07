<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="input" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인페이지</title>
    <script src="resources/libs/jquery-3.6.1.min.js"></script>
</head>
<body>
    <div>
        <form id="loginForm" action="/login" method="post">
            <div>
                <input type="text" id="username" name="username" placeholder="아이디를 입력하세요." />
                <label for="username">아이디를 입력하세요.</label>
            </div>
            <div>
                <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요."/>
                <label for="password">비밀번호를 입력하세요.</label>
            </div>
            <div>
                <input type="checkbox" id="remember-me" name="remember-me"/>
                <label for="remember-me">아이디 기억하기</label>
            </div>
            <div>
                <a href="#">비밀번호를 잊어버리셨나요?</a>
                <button type="submit">Login</button>
            </div>
            <input:csrfInput />
        </form>
    </div>
</body>
</html>