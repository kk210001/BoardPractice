<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
    request.setCharacterEncoding("UTF-8");
%>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="py-5 text-center">
        <h2>회원 가입</h2>
    </div>
    <h4 class="mb-3">회원 정보 입력</h4>
    <form action="${contextPath}/members/add" method="post">
<%--        <div th:if="${#fields.hasGlobalErrors()}">--%>
<%--            <p class="field-error" th:each="err : ${#fields.globalErrors()}"--%>
<%--               th:text="${err}">전체 오류 메시지</p>--%>
<%--        </div>--%>
        <div>
            <label for="loginId">로그인 ID</label>
            <input type="text" id="loginId"  class="form-control">
            <div class="field-error" />
        </div>
        <div>
            <label for="password">비밀번호</label>
            <input type="password" id="password" class="form-control">
            <div class="field-error"/>
        </div>
        <div>
            <label for="name">이름</label>
            <input type="text" id="name"class="form-control">
            <div class="field-error"/>
        </div>
        <hr class="my-4">
        <div class="row">
            <div class="col">
                <button class="w-100 btn btn-primary btn-lg" type="submit">회원
                    가입</button>
            </div>
            <div class="col">
                <button class="w-100 btn btn-secondary btn-lg"
                        onclick="location.href='listArticles.html'"
                        type="button">취소</button>
            </div>
        </div>
    </form>
</div> <!-- /container -->
</body>