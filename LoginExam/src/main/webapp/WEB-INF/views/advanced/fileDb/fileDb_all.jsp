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
    <%--todo : 검색어 --%>
        <form class="input-group mb-3 mt-3"  role="search" action="/advanced/fileDb/" method="get">
            <%--todo 검색 input 태그--%>
            <input type="text" class="form-control"  aria-label="Recipient's username" aria-describedby="button-addon2" name="fileTitle">
            <input type="hidden" id="page" name="page" value="0">
            <input type="hidden" id="size" name="size" value="3">
            <%--todo 검색 버튼--%>
            <button class="btn btn-outline-primary" type="submit" id="button-addon2">Search</button>
        </form>
    <%--todo : 부트스트랩 카드(반복문)--%>
        <div class="row"> <%--row : 행, col-숫자 : 열(숫자 : 총 12칸 중에 임의숫자)--%>
            <%--todo : forEach 반복문 시작--%>
            <c:forEach var="data" items="${fileDb}"> <%--열을 반복할것--%>
                <div class="col-3"><%--열의 12칸중 3칸을 차지--%>
                    <div class="card" style="width: 18rem;">
                        <%--todo : 카드 이미지--%>
                        <img src="${data.fileUrl}" class="card-img-top" alt="..."><%--데이터 베이스의 fielUrl에 이미지 url이 들어가있다. 이 url로 들어가면 자동으로 다운로드가 된다.--%>

                        <div class="card-body">
                            <%--todo : 카드 제목--%>
                            <h5 class="card-title" >
                                <a href="/advanced/fileDb/edition/${data.uuid}">${data.fileTitle}<a/>
                            </h5>
                            <%--todo : 카드 본문--%>
                            <p class="card-text">${data.fileContent}</p>
                            <%--todo : 삭제 버튼--%>
                            <form action="/advanced/fileDb/delete/${data.uuid}" method="post" onsubmit="return confirmDelete();">
                                <input type="hidden" name="_method" value="delete" >
                                <button type="submit" class="btn btn-primary">삭제</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

    <%--        TODO: 페이지번호 --%>
    <div class="d-flex justify-content-center"> <%--중앙정령 해주는 클래스 속성--%>
        <ul class="pagination">
            <li class="page-item ${(startPage+1==1)? 'disabled' : ''}">
                <a href="/advanced/fileDb?page=${startPage-1}&size=${3}&fileTitle=${fileTitle}"<%--dname도 RequestParam으로 받았지만 두개만 사용 가능--%> class="page-link">Previous</a>
            </li>
            <%--todo 반복문 실행--%>
            <c:forEach var="data" begin="${startPage}" end="${endPage}">
                <li class="page-item ${(currentPage == data)? 'active':''}"><a class="page-link" href="/advanced/fileDb?page=${data}&size=${3}&fileTitle=${fileTitle}">${data+1}</a><%-- 단순 반복일 경우 (요소를 반복해서 보여주는 것이 아닐경우) begin과 end가 필요하다.--%>
                </li><%-- 클릭하면 해당 페이지의 데이터가 뜨게 링크 걸어줘야함--%>
            </c:forEach>
            <li class="page-item ${(endPage+1==totalPages)? 'disabled' : ''}">
                <a href="/advanced/fileDb?page=${endPage+1}&size=${3}&fileTitle=${fileTitle}" class="page-link" >Next</a>
            </li>
        </ul>
    </div>
    <%--        TODO: Add 버튼 --%>
    <div class="text-center">
        <a href="/advanced/fileDb/addition" class="btn btn-primary">Add</a>
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
