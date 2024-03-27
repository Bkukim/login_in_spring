<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-22
  Time: 오후 3:23
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
<%--    본문 --%>
<div class="container">
    <%--    검색어 --%>
    <form class="row g-3 justify-content-center" action="/advanced/gallery" method="get">
        <%--        TODO: 검색 input 태그 : galleryTitle --%>
        <div class="col-auto mt-5">
            <input type="text"
                   class="form-control"
                   id="galleryTitle"
                   name="galleryTitle"
                   placeholder="제목입력">
            <input type="hidden" id="page" name="page" value="0"/>
            <input type="hidden" id="size" name="size" value="3"/>
        </div>
        <%--        TODO: 검색 버튼--%>
        <div class="col-auto mt-5 mb-3">
            <button type="submit" class="btn btn-primary">검색</button>
        </div>
    </form>

    <%--    TODO: 부트스트랩 카드(반복문) --%>
    <%--         복습 : row : 행 , col-숫자 : 열 (숫자: 총 12칸 중에 임의숫자)   --%>
    <div class="row">
        <%--        TODO: forEach 반복문 시작  : 열 반복 --%>
        <c:forEach var="data" items="${gallery}">
            <div class="col-3">
                <div class="card" style="width: 18rem;">
                        <%--                    TODO: 카드 이미지 : src="인터넷주소(다운로드url)" --%>
                    <img src="${data.galleryFileUrl}" class="card-img-top" alt="강의">

                    <div class="card-body">
                            <%--                        TODO: 카드 제목 : 수정페이지 url 추가(기본키 : uuid) --%>
                        <h5 class="card-title">
                            <a href="/advanced/gallery/edition/${data.uuid}">${data.galleryTitle}</a>
                        </h5>
                            <%--                        TODO: 삭제 버튼 : form 태그 추가 : delete 방식 --%>
                        <form action="/advanced/gallery/delete/${data.uuid}" method="post" onsubmit="return confirmDelete();">
                                <%--    TODO: springboot 에서 아래와 같이 hidden 값을 전송하면 :  delete 방식으로 인식해서 연결해줌    --%>
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="btn btn-danger" >삭제</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <%--    페이지번호 --%>
    <div class="d-flex justify-content-center">
        <ul class="pagination">
            <%--        이전 버튼 : 시작페이지가 0이면 버튼 비활성화 아니면 활성화 --%>
            <li class="page-item ${(startPage+1==1)? 'disabled' : ''}">
                <a href="/advanced/gallery?page=${startPage-1}&size=${3}&galleryTitle=${galleryTitle}" class="page-link">이전</a>
            </li>
            <c:forEach var="data" begin="${startPage}" end="${endPage}">
                <li class="page-item"><a class="page-link"
                                         href="/advanced/gallery?page=${data}&size=${3}&galleryTitle=${galleryTitle}">${data+1}</a>
                </li>
            </c:forEach>

            <%--        다음 버튼 : 끝페이지가 전체페이지와 같으면 비활성화 아니면 활성화 --%>
            <li class="page-item ${(endPage+1==totalPages)? 'disabled' : ''}">
                <a href="/advanced/gallery?page=${endPage+1}&size=${3}&galleryTitle=${galleryTitle}" class="page-link">다음</a>
            </li>
        </ul>
    </div>

    <%--    Add 버튼 --%>
    <div class="text-center">
        <a href="/advanced/gallery/addition" class="btn btn-primary">Add</a>
    </div>

</div>
<%--    꼬리말 --%>
<jsp:include page="../../common/footer.jsp"/>
<script>
    function confirmDelete() {
        return confirm("정말로 삭제하시겠습니까?");
    }
</script>
</body>
</html>
