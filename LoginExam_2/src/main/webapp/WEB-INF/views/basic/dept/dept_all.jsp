<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-19
  Time: 오전 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    머리말 --%>
<jsp:include page="../../common/header.jsp"/>
<%--    본문--%>
<div class="container">
    <%--        TODO: 검색어 입력 상자 --%>
        <form class="input-group mb-3 mt-3"  role="search" action="/basic/dept" method="get">
        <%--todo 검색 input 태그--%>
            <input type="text" class="form-control" placeholder="Insert dname" aria-label="Recipient's username" aria-describedby="button-addon2" name="dname">
            <input type="hidden" id="page" name="page" value="0">
            <input type="hidden" id="size" name="size" value="3">
        <%--todo 검색 버튼--%>
            <button class="btn btn-outline-primary" type="submit" id="button-addon2">Search</button>
    </form>
    <%--        TODO: 테이블 --%>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">dno</th>
            <th scope="col">dname</th>
            <th scope="col">loc</th>
            <th scope="col">insertTime</th>
            <th scope="col">updateTime</th>
            <th scope="col">delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="data" items="${dept}">
            <tr>
                <td><a href="/basic/dept/edition/${data.dno}">${data.dno}</a></td>
                <td>${data.dname}</td>
                <td>${data.loc}</td>
                <td>${data.insertTime}</td>
                <td>${data.updateTime}</td>
                <td><form action="/basic/dept/delete/${data.dno}" method="post" onsubmit="return confirmDelete();">
                    <input type="hidden" name="_method" value="delete">
                    <div><button type="submit" class="btn btn-outline-primary">삭제</button></div>
                </form></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--        TODO: 페이지번호 --%>
    <div class="d-flex justify-content-center"> <%--중앙정령 해주는 클래스 속성--%>
        <ul class="pagination">
            <li class="page-item ${(startPage+1==1)? 'disabled' : ''}">
                <a href="/basic/dept?page=${startPage-1}&size=${3}"<%--dname도 RequestParam으로 받았지만 두개만 사용 가능--%> class="page-link">Previous</a>
            </li>
            <%--todo 반복문 실행--%>
            <c:forEach var="data" begin="${startPage}" end="${endPage}">
                <li class="page-item ${(currentPage == data)? 'active':''}"><a class="page-link" href="/basic/dept?page=${data}&size=${3}">${data+1}</a><%-- 단순 반복일 경우 (요소를 반복해서 보여주는 것이 아닐경우) begin과 end가 필요하다.--%>
                </li><%-- 클릭하면 해당 페이지의 데이터가 뜨게 링크 걸어줘야함--%>
            </c:forEach>
            <li class="page-item ${(endPage+1==totalPages)? 'disabled' : ''}">
                <a href="/basic/dept?page=${endPage+1}&size=${3}" class="page-link" >Next</a>
            </li>
        </ul>
    </div>
    <%--        TODO: Add 버튼 --%>
    <div class="text-center">
        <a href="/basic/dept/addition" class="btn btn-primary">Add</a>
    </div>
</div>
<%--    꼬리말--%>

<jsp:include page="../../common/footer.jsp"/>
<script>
    function confirmDelete() {
        return confirm("정말로 삭제하시겠습니까?");
    }
</script>
</body>
</html>
