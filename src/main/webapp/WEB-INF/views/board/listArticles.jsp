<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="articlesList" value="${articlesList}" />
<%--<c:set var="totArticles" value="${articlesMap.totArticles}" />--%>
<%--<c:set var="section" value="${articlesMap.section}" />--%>
<%--<c:set var="pageNum" value="${articlesMap.pageNum}" />--%>
<%--<c:set var="pageCnt" value="${articlesMap.pageCnt}" />--%>

<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"  href="/css/style.css">
    <meta charset="UTF-8">
    <title>글목록창</title>
</head>
<body>

<div class="container">
    <h3>게시판 목록</h3>
    <table class="board_list">
        <thead>
        <tr height="10" align="center">
            <th>번호</th>
            <th><span style="padding-right: 30px"></span>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
        <c:when test="${empty articlesList}">
        <tr>
            <td>
                <b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
            </td>
        </tr>
        </c:when>
        <c:when test="${!empty articlesList}">
        <c:forEach var="article" items="${articlesList }"
                   varStatus="articleNum">
        <tr>
            <td width="5%">
                    ${pageCnt - (pageNum - 1)*10 - (articleNum.count-1)}

            </td>
            <td width="35%" class="title">
                <a class="board-title" href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
            </td>
            <td width="10%">${article.id }</td>
            <td width="10%"><fmt:formatDate
                    value="${article.writeDate}" /></td>

        </tr>
        </c:forEach>
        </c:when>
        </c:choose>
    </table>
<%--아래는 페이지네이션 관련--%>
    <div>
        <c:if test="${totArticles != null }">
            <c:choose>
                <c:when test="${totArticles >100 }">
                    <!-- 글 개수가 100 초과인경우 -->
                    <c:forEach var="page" begin="1" end="10" step="1">
                        <c:if test="${section >1 && page==1 }">
                            <a class="no-uline"
                               href="${contextPath }/board/listArticles.do?section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp;
                                pre </a>
                        </c:if>
                        <a class="no-uline"
                           href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10 +page }
                        </a>
                        <c:if test="${page ==10 }">
                            <a class="no-uline"
                               href="${contextPath }/board/listArticles.do?section=${section+1}&pageNum=${section*10+1}">&nbsp;
                                next</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:when test="${totArticles ==100 }">
                    <!--등록된 글 개수가 100개인경우  -->
                    <c:forEach var="page" begin="1" end="10" step="1">
                        <a class="no-uline" href="#">${page } </a>
                    </c:forEach>
                </c:when>

                <c:when test="${totArticles< 100 }">
                    <!--등록된 글 개수가 100개 미만인 경우  -->
                    <c:forEach var="page" begin="1" end="${totArticles/10 +1}" step="1">
                        <c:choose>
                            <c:when test="${page==pageNum }">
                                <a class="sel-page"
                                   href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a class="no-uline"
                                   href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:if>
    </div>
    </tbody>

    <br> <a class="btn" href="${contextPath}/board/articleForm.do"><p>글쓰기</p></a>
</div>

</body>
</html>