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
<title>글쓰기창</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

  function backToList(obj){
    obj.action="${contextPath}/board/listArticles.do";
    obj.submit();
  }


  function addArticle(obj){
	  document.getElementById("id").disabled=false;
	  obj.submit();
  }
  

</script>
 <title>글쓰기창</title>
	<link rel="stylesheet"  href="/css/style.css">
</head>
<body>
<div class="container">
<h2>글쓰기</h2>
  <form name="articleForm" method="post"   action="${contextPath}/board/addNewArticle.do"   enctype="multipart/form-data">
    <table class="board_detail">
      		<tr>
					<td align="center" width="10%">작성자</td>
					<td width="30%"><input type="text" size="20" maxlength="20"  id="id" name="id" value=${member.nickname} disabled/> </td>
				<td></td>
				<td width="20%" ></td>
			</tr>
	     <tr>
			   <td align="center">제목 </td>
			   <td colspan="2"><input type="text" size="67"  maxlength="500" name="title"/></td>
			  <td></td>
		 </tr>
	 		<tr>
				<td colspan="4"><textarea name="content" rows="10" cols="65" maxlength="4000"></textarea> </td>
     		</tr>

    </table>
	  <tr>
		  <td><input type="button" value="글쓰기" onClick="addArticle(this.form)"/></td>
		  <td><input type=button value="목록보기"onClick="backToList(this.form)" /></td>
	  </tr>
  </form>
</div>
</body>
</html>
