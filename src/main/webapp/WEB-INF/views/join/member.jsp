<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <script src="/resources/libs/jquery-3.6.1.min.js"></script>
    <script>
        /*전역변수*/
        let flagIdCheck = null;
        let flagPwCheck = null;

        /*로직 처리 스크립트*/
        let process = {
            userIdCheckProcess : function(){
                let $userId = $("#userId").val();
                if(!$userId){
                    alert("아이디를 입력해주세요.");
                    return false;
                }
                let params = {"userId" : $userId};
                $.ajax({
                    type: "POST", //POST로 하면 security에서 막히기 때문에 csrf disable true로 바꿔줌
                    url: "/join/getMember.do",
                    data: params,
                    success : function(userVO){
                        if(!userVO){
                            alert("사용가능한 아이디입니다.");
                            flagIdCheck = true;
                        } else {
                            alert("이미 아이디가 존재합니다. 다른 아이디를 입력해주세요.");
                            flagIdCheck = false;
                        }
                    },
                    error : function(error){
                        console.log(error);
                    }
                });
            },
            userPwCheckProcess : function($thisVal){
                let $userPw = $("#userPw").val();
                let $userPwCheckTag = $("#userPwCheckTag");
                if(!$thisVal){
                    $userPwCheckTag.text("");
                    return;
                }else if($userPw != $thisVal){
                    $userPwCheckTag.css("color","red");
                    $userPwCheckTag.text("*비밀번호가 일치하지 않습니다");
                } else {
                    $userPwCheckTag.css("color","blue");
                    $userPwCheckTag.text("*비밀번호가 일치합니다");
                    flagPwCheck = true;
                }
            },
            formSubmit : function(){
                if(flagIdCheck && flagPwCheck){
                    $("#joinMemberForm").submit();
                } else {
                    alert("아이디 중복 혹은 비밀번호를 확인해주세요");
                }
            },
        },
        /*이벤트 bind 스크립트 (click event 등등)*/
        eventbind = function(){
            $("#userIdCheck").click(function(){
                process.userIdCheckProcess();
            });
            $("#userPw").on("keyup", function(){
                flagPwCheck = false;
                $("#userPwCheck").trigger("keyup");
            });
            $("#userPwCheck").on("keyup", function(){
                process.userPwCheckProcess($(this).val());
            });
            $("#formButton").click(function(e){
                e.preventDefault();
                process.formSubmit();
            });
        };
        $(document).ready(function(){
            eventbind();

            /*초기변수 세팅*/
            flagIdCheck = false;
            flagPwCheck = false;
        });
    </script>
</head>
<body>
    <h1>회원가입페이지</h1><br/>
    <form id="joinMemberForm" action="/join/member.do" method="post">
        <div>
            <input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요." required="required"/>
            <label for="userId">아이디</label>
            <button type="button" id="userIdCheck">아이디 중복 확인</button>
        </div>
        <div>
            <input type="password" name="userPw" id="userPw" placeholder="비밀번호를 입력하세요." required="required" minlength="8"/>
            <label for="userPw">비밀번호</label>
        </div>
        <div >
            <input type="password" name="userPwCheck" id="userPwCheck" placeholder="비밀번호를 다시입력하세요." required="required"/>
            <label for="userPwCheck">비밀번호확인</label>
            <p id="userPwCheckTag">비밀번호확인</p>
        </div>
        <div>
            <input type="text" name="userNm" id="userNm" placeholder="이름을 입력하세요." required="required"/>
            <label for="userId">이름</label>
        </div>
        <div>
            <input type="text" name="userPhoneNum" id="userPhoneNum" placeholder="휴대번호를 입력하세요." required="required"/>
            <label for="userPhoneNum">휴대번호(ex. 01011112222)</label>
        </div>
        <div>
            <input type="text" name="userEmail" id="userEmail" placeholder="이메일을 입력하세요." required="required"/>
            <label for="userEmail">이메일</label>
        </div>
        <br/>
        <button type="submit" id="formButton">계정생성하기</button>
    </form>
</body>
</html>