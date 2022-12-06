<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="articlesList" value="${articlesList}" />

<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"  href="/css/style.css">
    <meta charset="UTF-8">
    <title>글목록창</title>
    <script>

        function getSearchBoard(){
            var type = searchForm.type.value;
            var keyword = searchForm.keyword.value;

            if(keyword === ''){
                alert('검색어를 입력해주세요.')
            }else{
                var url = "${pageContext.request.contextPath}/board/listArticles.do";
                url = url + "?type="+type + "&keyword="+keyword;
                location.href = url;
            }
        }
        function prev(page, rangeSize) {

            var page = parseInt(page) - parseInt(rangeSize);
            while(page % 10 != 0){
                page++;
            }
            <%--var url = "${pageContext.request.contextPath}/board/listArticles.do";--%>
            // var url = document.location.href;
            // var arr = url.split("page")
            // url = url + "/board/listArticles.do";
            // url = url + "?page=" + page;
            // url = arr[0] + "page=" + page;
            // location.href = url;
            pagination(page);
        }
        function next(page , rangeSize) {

            var page = parseInt(page) + parseInt(rangeSize);
            while(page % 10 != 1){
                page--;
            }
            pagination(page);
            <%--var url = "${pageContext.request.contextPath}/board/listArticles.do";--%>
            <%--url = url + "?page=" + page;--%>
            // var url = document.location.href;
            // var arr = url.split("page")
            // url = arr[0] + "page=" + page;
            // location.href = url;
        }
        <%--function pagination(page) {--%>
        <%--    var url = "${contextPath}/board/listArticles.do";--%>
        <%--    url = url + "?page=" + page;--%>
        <%--    location.href = url;--%>
        <%--}--%>
        function pagination(page) {
            // var arr = url.split("page=");
            // url = url + "/board/listArticles.do";
            // url = url + "?page=" + page;
            // if(arr[0].indexOf("type") != -1 && arr[0].charAt(arr[0].length-1) != "&"){
            //     url = arr[0] + "&page=" + page;
            // } else if(arr[0].indexOf("type") == -1 && arr[0].charAt(arr[0].length-1) != "&"){
            //     url = arr[0] + "?page=" + page;
            // }else {
            //     url = arr[0] +"page=" + page;
            // }
            var url = document.location.href;
            if (url.indexOf("?page=") == -1 && url.indexOf("type") == -1) {
                var arr = url.split("?page=");
                url = arr[0] + "?page=" + page;
            } else if (url.indexOf("&page=") == -1 &&  url.indexOf("type") != -1) {
                var arr = url.split("&page=");
                url = arr[0] + "&page=" + page;
            } else {
                var arr = url.split("page=");
                url = arr[0] + "page=" + page;
            }
            location.href = url;
        }
    </script>
    <style>
        .page_wrap {
            text-align:center;
            font-size:0;
        }
        .page_nation {
            display:inline-block;
        }
        .page_nation a {
            display:block;
            margin:0 3px;
            float:left;
            border:1px solid #e6e6e6;
            width:28px;
            height:28px;
            line-height:28px;
            text-align:center;
            background-color:#fff;
            font-size:13px;
            color:#999999;
            text-decoration:none;
        }
        .page_nation a:hover {
            background-color:#42454c;
            color:#fff;
            border:1px solid #42454c;
        }
    </style>
</head>
<body>

<div class="container">
    <h3 style="display: inline">게시판 목록</h3>
    <input type="button" style="float: right" onclick="getSearchList()" class="btn btn-outline-primary mr-2"value="로그인">
    <table class="board_list">
        <thead>
        <tr height="10" align="center">
            <th>번호</th>
            <th><span></span>제목</th>
            <th>작성자</th>
            <th>조회</th>
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
                    <%--                    ${pagination.listCount - (pagination.listSize * (pagination.page-1)) - (articleNum.count-1)}--%>
                    ${article.articleNO}
                    <%--        ${article.articleNO }--%>
            </td>
            <td width="35%" class="title">
                <a class="board-title" href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
            </td>
            <td width="10%">${article.id }</td>
            <td width="5%">${article.viewCount }</td>
            <td width="10%"><fmt:formatDate
                    value="${article.writeDate}" /></td>

        </tr>
        </c:forEach>
        </c:when>
        </c:choose>
    </table>
    <tr>
        <br> <a class="btn" href="${contextPath}/board/articleForm.do" style="text-decoration-line:none;float: left"><p>글쓰기</p></a>
        <div class="page_wrap">
            <div class="page_nation">
                <c:if test="${pagination.page > pagination.rangeSize}">
                    <a href="#" onClick="prev('${pagination.page}', '${pagination.rangeSize}')">이전</a>
                </c:if>
                <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
                    <c:choose>
                        <c:when test="${pagination.page==idx}">
                            <a style="text-decoration-line: none;background-color: #252525" href="#" onClick="pagination('${idx}')"> ${idx} </a>
                        </c:when>
                        <c:otherwise>
                            <a style="text-decoration-line: none" href="#" onClick="pagination('${idx}')"> ${idx} </a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pagination.page + pagination.rangeSize < pagination.pageCount}">
                    <a  href="#" onClick="next('${pagination.page}', '${pagination.rangeSize}')">다음</a>
                </c:if>
            </div>
        </div>
    </tr>
    <div style="margin-left: 400px; margin-top: 10px" >
        <form name="searchForm" autocomplete="off">
            <select name="type">
                <option selected value="title">제목</option>
                <option value="content">내용</option>
                <option value="id">작성자</option>
            </select>
            <input type="text" name="keyword" value="">
            <input type="button" onclick="getSearchBoard()" class="btn btn-outline-primary mr-2"value="검색">
        </form>
    </div>
    </tbody>
</div>
</body>
</html>