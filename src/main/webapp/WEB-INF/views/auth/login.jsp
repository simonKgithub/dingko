<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="input" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인페이지</title>
    <script src="/resources/libs/jquery-3.6.1.min.js"></script>
    <script>
        function joinMemberProcess(){
            let $joinUserId = $("#joinUserId").val();
            if($joinUserId != ""){
                alert("회원가입이 완료되었습니다. 로그인을 진행해주세요.");
                $("#userId").val($joinUserId);
                return;
            }
        }

        $(document).ready(function(){
            joinMemberProcess();
        });
    </script>
</head>
<body>
    <div>
        <!--
            joinUserId : 회원가입 성공 시 주어짐
            logout : 로그아웃 성공 시 주어짐(logout=true)
            error : 로그인 실패 시 주어짐 아이디나 비밀번호 오류 등 (error=access-fail)
         -->
        <input type="hidden" id="joinUserId" value="${joinUserId}">
        <h2><c:out value="${error}" /></h2>
        <h2><c:out value="${logout}" /></h2>
        <form id="loginForm" action="/login" method="post">
            <div>
                <input type="text" id="userId" name="username" placeholder="아이디를 입력하세요." />
                <label for="userId">아이디를 입력하세요.</label>
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
                <a href="/join/member.do">회원가입 하기</a>
                <button type="submit">Login</button>
            </div>
            <input:csrfInput />
        </form>
    </div>
</body>
</html>