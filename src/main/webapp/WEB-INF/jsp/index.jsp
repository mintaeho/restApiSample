<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
<title>Jsp page sample</title>
<script src="/js/jquery.min.js"></script>
<script language="javascript">
    var HEADER_TOKEN_KEY = "token";

    function signUp() {
        var param = {
            email: "test@test.com",
            password: "testpassword"
        };

        $.ajax({
            type: 'POST',
            url: '/signUp',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(param)
        }).done(function(data) {
            alert('등록 되었습니다.');
            location.href = "/index";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }

    function signIn() {
        var param = {
            email: "test@test.com",
            password: "testpassword"
        };

        $.ajax({
            type: 'POST',
            url: '/signIn',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(param)
        }).done(function(data) {
            alert('로그인 되었습니다.');
            $("#userId").val(data.userId);
            $("#token").val(data.token);
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }

    function test() {
        var param = {
            email: "test@test.com",
            password: "testpassword"
        };

        var userId = $("#userId").val();
        var token = $("#token").val();

        console.log('userId', userId, 'token', token);


        $.ajax({
            type: 'POST',
            url: '/test',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(param),
            beforeSend: function(xhr) {
                xhr.setRequestHeader("userId", userId);
                xhr.setRequestHeader(HEADER_TOKEN_KEY, token);
            }
        }).done(function(data) {
            alert('테스트가 성공 되었습니다.');
            console.log(data);
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }

</script>
</head>
<body>
    <input type="hidden" id="userId" name="userId">
    <input type="hidden" id="token" name="token">


	<div>
	    <ul>

	        <li><a href="javascript:signUp()">
	                <span>sign up</span>
	            </a></li>

	        <li><a href="javascript:signIn()">
	                <span>sign in</span>
	            </a></li>

	        <li><a href="javascript:test()">
	                <span>test</span>
	            </a></li>


	    </ul>
	</div>

    <br/>
    Results :
	<table>
	    <thead>
	        <th>email</th>
	        <th>password</th>
	        <th>token</th>
	    </thead>
	    <tbody>
	        <c:forEach var="items" items="${users}" varStatus="status">
                <tr>
                    <td>${items.email}</td>
                    <td>${items.password}</td>
                    <td>${items.token}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
