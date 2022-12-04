

    function getSearchBoard() {
    $.ajax({
        type: 'GET',
        url : "/getSearchBoard",
        data : $("form[name=find-boards-form]").serialize(),
        success : function(result){
            //테이블 초기화
            $('#boardtable > tbody').empty();
            if(result.length>=1){
                result.forEach(function(article){
                    str='<tr>'
                    str += "<td>"+article.idx+"</td>";
                    str+="<td>"+article.articleNO+"</td>";
                    str+="<td><a href='/board/viewArticle.do?article.articleNO= "+ article.articleNO +"'>" +article.title + "</a></td>";
                    str+="<td>"+article.id+"</td>";
                    str+="<td>"+article.content+"</td>";
                    str+="<td>"+article.writeDate+"</td>";
                    str+="</tr>"
                    $('#boardtable').append(str);
                })
            }
        }
    })
}
    function prev(page, rangeSize) {

        var page = parseInt(page) - parseInt(rangeSize);
        while(page % 10 != 0){
            page++;
        }
        var url = "${pageContext.request.contextPath}/board/listArticles.do";
        url = url + "?page=" + page;
        // var range = range - 1;
        // url = url + "&range=" + range;
        location.href = url;
    }
    function pagination(page) {
        var url = "${contextPath}/board/listArticles.do";
        url = url + "?page=" + page;
        location.href = url;
    }

    function next(page , rangeSize) {

        var page = parseInt(page) + parseInt(rangeSize);
        while(page % 10 != 1){
            page--;
        }
        var url = "${pageContext.request.contextPath}/board/listArticles.do";
        url = url + "?page=" + page;
        // var range = parseInt(range) + 1;
        // url = url + "&range=" + range;
        location.href = url;
    }
