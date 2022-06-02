<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<c:set var="article"  value="${articleMap.article}"  />
<%--<c:set var="imageFileList"  value="${articleMap.imageFileList}"  />--%>


<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
   <meta charset="UTF-8">
<%--    <link rel="stylesheet"  href="/css/style.css">--%>
    <link rel="stylesheet" th:href="@{/css/style.css}" href="/css/style.css"></link>
   <title>글보기</title>
   <style>
     #tr_btn_modify{
       display:none;
     }
   
   </style>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script type="text/javascript" >
     function backToList(obj){
	    obj.action="${contextPath}/board/listArticles.do";
	    obj.submit();
     }

	 function modify_enable(obj){
		 document.getElementById("i_title").disabled=false;
		 document.getElementById("i_content").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tr_btn").style.display="none";
	 }

	 function modify_article(obj){
		 obj.action="${contextPath}/board/modArticle.do";
		 obj.submit();
	 }
     function remove_article(url,articleNO){
         var form = document.createElement("form");
         form.setAttribute("method", "post");
         form.setAttribute("action", url);
         var articleNOInput = document.createElement("input");
         articleNOInput.setAttribute("type","hidden");
         articleNOInput.setAttribute("name","articleNO");
         articleNOInput.setAttribute("value", articleNO);

         form.appendChild(articleNOInput);
         document.body.appendChild(form);
         form.submit();

     }

 </script>
</head>
<body>
<div class="container">
    <h2>게시글 상세 화면</h2>
    <form name="frmArticle" method="post"  action="${contextPath}">
    <table class="board_detail">
        <colgroup>
            <col width="15%"/>
            <col width="35%"/>
            <col width="15%"/>
            <col width="35%"/>
        </colgroup>
        <caption>게시글 상세내용</caption>
        <tbody>
        <tr>
            <th scope="row">글 번호</th>
            <td >
                <input type="text"  value="${article.articleNO }"  disabled />
                <input type="hidden" name="articleNO" value="${article.articleNO}"  />
            </td>
            <th scope="row">조회수</th>
            <td><input type="text"  value="${article.viewCount }"  disabled /></td>
        </tr>
        <tr>
            <th scope="row">작성자</th>
            <td >
                <input type=text value="${article.id }" name="writer"  disabled />
            </td>
            <th scope="row">작성일</th>
            <td>
                <input type=text value="<fmt:formatDate value="${article.writeDate}" />" disabled />
            </td>
        </tr>
        <tr>
            <th scope="row">제목</th>
            <td colspan="3">
                <input type=text value="${article.title }"  name="title"  id="i_title" disabled />
            </td>
        </tr>
        <tr>
            <td colspan="4" class="view_text">
                <textarea rows="20" cols="60"  name="content"  id="i_content"  disabled />${article.content }</textarea>
            </td>
        </tr>
        </tbody>
    </table>
        <tr   id="tr_btn_modify"  align="center"  >
            <td colspan="2"   >
                <input type=button value="수정반영하기"   onClick="modify_article(frmArticle)"  >
                <input type=button value="취소"  onClick="backToList(frmArticle)">
            </td>
        </tr>
        <tr  id="tr_btn"    >
            <td colspan="2" align="center">
                <input type=button value="수정하기" onClick="modify_enable(this.form)">
                <input type=button value="삭제하기" onClick="remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
                <input type=button value="리스트로 돌아가기" onClick="backToList(this.form)">
            </td>
        </tr>

    </form>
</div>
</body>
</html>