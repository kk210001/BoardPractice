<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
    request.setCharacterEncoding("UTF-8");
%>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 560px;
        }
        .errors-fields {
            border-color: #dc3545;
            color: #dc3545;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="py-5 text-center">
        <h2>회원 가입</h2>
    </div>
    <h4 class="mb-3">회원 정보 입력</h4>
    <form:form modelAttribute="memberDTO" action="${contextPath}/members/add" method="post">
        <div>
            <div>
                <label for="memberId">ID</label>
                <form:input path="memberId" type="text" id="memberId" name="memberId" class="form-control"/>
            </div>
            <form:errors path="memberId" class="errors-fields" />
        </div>
        <div>
            <div>
                <label for="password">비밀번호</label>
                <form:input path="password" type="text" id="password" name="password" class="form-control"/>
            </div>
            <form:errors path="password" class="errors-fields" />
        </div>
        <div>
            <div>
                <label for="nickname">이름</label>
                <form:input path="nickname" type="text" id="nickname" name="nickname" class="form-control"/>
            </div>
            <form:errors path="nickname" class="errors-fields" />
        </div>
        <hr class="my-4">
        <div class="row">
            <div class="col">
                <button class="w-100 btn btn-primary btn-lg" type="submit">회원
                    가입</button>
            </div>
            <div class="col">
                <button class="w-100 btn btn-secondary btn-lg"
                        onclick="location.href='${contextPath}/board/listArticles.do'"
                        type="button">취소</button>
            </div>
        </div>
    </form:form>
</div> <!-- /container -->
</body>