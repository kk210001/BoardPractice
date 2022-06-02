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
     #tr_file_upload{
       display:none;
     }
     #tr_btn_modify{
       display:none;
     }
   
   </style>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
   <script type="text/javascript" >
     function backToList(obj){
	    obj.action="/board/listArticles.do";
	    obj.submit();
     }
 
	 function fn_enable(obj){
		 document.getElementById("i_title").disabled=false;
		 document.getElementById("i_content").disabled=false;
		 document.getElementById("i_imageFileName").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tr_file_upload").style.display="block";
		 // document.getElementById("tr_btn").style.display="none";
	 }
	 
	 function fn_modify_article(obj){
		 obj.action="${contextPath}/board/modArticle.do";
		 obj.submit();
	 }
	 
	 function fn_remove_article(url,articleNO){
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
    <tr  id="tr_btn"    >
           <td colspan="2" align="center">
               <input type=button value="수정하기" onClick="fn_enable(this.form)">
               <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
               <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
           </td>
          </tr>
</div>
<%--  <form name="frmArticle" method="post"  action="${contextPath}"  enctype="multipart/form-data">--%>
<%--  <table  border=0  align="center">--%>
<%--  <tr>--%>
<%--   <td width=150 align="center" bgcolor=#FF9933>--%>
<%--      글번호--%>
<%--   </td>--%>
<%--   <td >--%>
<%--    <input type="text"  value="${article.articleNO }"  disabled />--%>
<%--    <input type="hidden" name="articleNO" value="${article.articleNO}"  />--%>
<%--   </td>--%>
<%--  </tr>--%>
<%--  <tr>--%>
<%--    <td width="150" align="center">--%>
<%--      작성자 아이디--%>
<%--   </td>--%>
<%--   <td >--%>
<%--    <input type=text value="${article.id }" name="writer"  disabled />--%>
<%--   </td>--%>
<%--  </tr>--%>
<%--  <tr>--%>
<%--    <td width="150" align="center" bgcolor="#FF9933">--%>
<%--      제목 --%>
<%--   </td>--%>
<%--   <td>--%>
<%--    <input type=text value="${article.title }"  name="title"  id="i_title" disabled />--%>
<%--   </td>   --%>
<%--  </tr>--%>
<%--  <tr>--%>
<%--    <td width="150" align="center" bgcolor="#FF9933">--%>
<%--      내용--%>
<%--   </td>--%>
<%--   <td>--%>
<%--    <textarea rows="20" cols="60"  name="content"  id="i_content"  disabled />${article.content }</textarea>--%>
<%--   </td>  --%>
<%--  </tr>--%>
<%-- &lt;%&ndash; --%>
<%-- <c:if test="${not empty imageFileList && imageFileList!='null' }">--%>
<%--	  <c:forEach var="item" items="${imageFileList}" varStatus="status" >--%>
<%--		    <tr>--%>
<%--			    <td width="150" align="center" bgcolor="#FF9933"  rowspan="2">--%>
<%--			      이미지${status.count }--%>
<%--			   </td>--%>
<%--			   <td>--%>
<%--			     <input  type= "hidden"   name="originalFileName" value="${item.imageFileName }" />--%>
<%--			    <img src="${contextPath}/download.do?articleNO=${article.articleNO}&imageFileName=${item.imageFileName}" id="preview"  /><br>--%>
<%--			   </td>   --%>
<%--			  </tr>  --%>
<%--			  <tr>--%>
<%--			    <td>--%>
<%--			       <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   />--%>
<%--			    </td>--%>
<%--			 </tr>--%>
<%--		</c:forEach>--%>
<%-- </c:if>--%>
<%-- 	 &ndash;%&gt;    --%>
<%-- 	 --%>
<%--  <tr>--%>
<%--	   <td width="150" align="center" bgcolor="#FF9933">--%>
<%--	      등록일자--%>
<%--	   </td>--%>
<%--	   <td>--%>
<%--	    <input type=text value="<fmt:formatDate value="${article.writeDate}" />" disabled />--%>
<%--	   </td>   --%>
<%--  </tr>--%>
<%--  <tr   id="tr_btn_modify"  align="center"  >--%>
<%--	   <td colspan="2"   >--%>
<%--	       <input type=button value="수정반영하기"   onClick="fn_modify_article(frmArticle)"  >--%>
<%--           <input type=button value="취소"  onClick="backToList(frmArticle)">--%>
<%--	   </td>   --%>
<%--  </tr>--%>
<%--    --%>
<%--  <tr  id="tr_btn"    >--%>
<%--   <td colspan="2" align="center">--%>
<%--       <c:if test="${member.id == article.id }">--%>
<%--	      <input type=button value="수정하기" onClick="fn_enable(this.form)">--%>
<%--	      <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">--%>
<%--	    </c:if>--%>
<%--	    <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">--%>
<%--	     <input type=button value="답글쓰기"  onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.articleNO})">--%>
<%--   </td>--%>
<%--  </tr>--%>
<%-- </table>--%>
<%-- </form>--%>
</body>
</html>