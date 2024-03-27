<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2024-03-21
  Time: 오후 4:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--todo 머리말--%>
<jsp:include page="../../common/header.jsp"/>
<%--todo 본문--%>
<form class="input-group mb-3 container" role="search" action="/basic/faq" method="get">
    <input type="text" class="form-control" placeholder="Insert title" aria-label="Recipient's username" aria-describedby="button-addon2" name="title">
    <input type="hidden" id="page" name="page" value="0">
    <input type="hidden" id="size" name="size" value="3">
    <button class="btn btn-outline-primary" type="submit" id="button-addon2">Search</button>
</form>
<table class="table container">
    <thead>
    <tr>
        <th scope="col">>fno</th>
        <th scope="col">title</th>
        <th scope="col">content</th>
        <th scope="col">inset time</th>
        <th scope="col">update time</th>
        <th scope="col">delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="data" items="${faq}">
    <tr>
        <th scope="row" ><a href="/basic/faq/edition/${data.fno}">${data.fno}<a/></th>
        <td>${data.title}</td>
        <td>${data.content}</td>
        <td>${data.insertTime}</td>
        <td>${data.updateTime}</td>
        <td><form action="/basic/faq/delete/${data.fno}" method="post" onsubmit="return confirmDelete();">
            <input type="hidden" name="_method" value="delete">
            <button class="btn btn-outline-primary" type="submit">삭제</button>
        </form></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
    <%--todo 페이지 넘버--%>
    <div class="d-flex justify-content-center">
        <ul class="pagination">
            <li class="page-item ${(startPage+1 == 1)? 'disabled' : ''}">
                <a class="page-link" href="basic/faq?page=${startPage-1}&size=${3}">Previous</a>
            </li>
    <%--todo 반복문 실행--%>
            <c:forEach var="data" begin="${startPage}" end="${endPage}">
           <li><a class="page-link ${(currentPage == data)? 'active' : ''}" href="/basic/faq?page=${data}&size=${3}">${data+1}</a></li>
            </c:forEach>
            <li class="page-item ${(startPage+1 == 1)? 'disabled' : ''}">
            <a class="page-link " href="/basic/faq?page=${endPage+1}&size=${3}">Next</a>
            </li>
        </ul>
    </div>
    <%--todo Add버튼--%>
    <div class="container">
        <a href="/basic/faq/addition" class="btn btn-primary">추가</a>
    </div>


<%--todo 꼬리말--%>
<jsp:include page="../../common/footer.jsp"/>
<script>
    function confirmDelete() {
        return confirm("정말로 삭제하시겠습니까?");
    }
</script>
</body>
</html>
