<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    머리말 --%>
<jsp:include page="../../common/header.jsp" />
<%--    본문--%>
<div class="container">
    <%--        TODO: 검색어 입력 상자 --%>
        <form class="input-group mb-3" role="search" action="/basic/emp" method="get">
            <input type="text" class="form-control" placeholder="Insert ename" aria-label="Recipient's username" aria-describedby="button-addon2" name="ename">
            <input type="hidden" id="page" name="page" value="0">
            <input type="hidden" id="size" name="size" value="3">
            <button class="btn btn-outline-primary" type="submit" id="button-addon2">Search</button>
        </form>
    <%--        TODO: 테이블 --%>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">eno</th>
            <th scope="col">ename</th>
            <th scope="col">job</th>
            <th scope="col">manager</th>
            <th scope="col">hiredate</th>
            <th scope="col">salary</th>
            <th scope="col">commission</th>
            <th scope="col">dno</th>
            <th scope="col">insertTime</th>
            <th scope="col">updateTime</th>
            <th scope="col">delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="data" items="${emp}" >
        <tr>
            <td><a href="/basic/emp/edition/${data.eno}">${data.eno}<a/></td>
            <td>${data.ename}</td>
            <td>${data.job}</td>
            <td>${data.manager}</td>
            <td>${data.hiredate}</td>
            <td>${data.salary}</td>
            <td>${data.commission}</td>
            <td>${data.dno}</td>
            <td>${data.insertTime}</td>
            <td>${data.updateTime}</td>
            <td><form action="/basic/emp/delete/${data.eno}" method="post" onsubmit="return confirmDelete();">
                <input type="hidden" name="_method" value="delete">
                <button class="btn btn-outline-primary" type="submit">삭제</button>
            </form></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--        TODO: 페이지번호 --%>
        <div class="d-flex justify-content-center">
            <ul class="pagination">
                <li class="page-item ${(startPage + 1 == 1)? 'disabled' : ''}">
                    <a class="page-link" href="/basic/emp?page=${startPage-1}&size=${3}">Previous</a>
                </li>
                <c:forEach var="data" begin="${startPage}" end="${endPage}">
                <li class="page-item"><a class="page-link ${(currentPage == data)? 'active' : ''}" href="/basic/emp?page=${data}&size=${3}">${data+1}</a></li><%-- 단순 반복일 경우 (요소를 반복해서 보여주는 것이 아닐경우) begin과 end가 필요하다.--%>
                </c:forEach>
                    <a class="page-link ${(endPage + 1 == totalPages)? 'disabled' : ''}" href="/basic/emp?page=${endPage+1}&size=${3}">Next</a>
                </li>
            </ul>
        </div>
    <%--        TODO: Add 버튼 --%>
    <div class="text-center">
        <a href="/basic/emp/addition" class="btn btn-primary">Add</a>
    </div>
</div>

<%--    꼬리말--%>
<jsp:include page="../../common/footer.jsp" />
<script>
    function confirmDelete() {
        return confirm("정말로 삭제하시겠습니까?");
    }
</script>
</body>
</html>