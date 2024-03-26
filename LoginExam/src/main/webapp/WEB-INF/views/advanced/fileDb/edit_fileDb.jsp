<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2024-03-25
  Time: 오전 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--todo : 머리말--%>
<jsp:include page="../../common/header.jsp"/>
<%--todo : 본문--%>
<div class="container">
    <div>
        <%-- todo 파일 업로드 : 전송형태 : multipart/form-data 전송--%>
        <form action="/advanced/fileDb/edit/${fileDb.uuid}" method="post"
              enctype="multipart/form-data"><%--파일전송은 이 enctype속성을 또 추가해주어야한다.--%>
            <input type="hidden" name="_method" value="put">
            <%--todo : 제목(fileTitle)--%>
            <div class="mb-3">
                <label for="fileTitle" class="form-label">File Title</label>
                <input type="text" class="form-control" id="fileTitle" placeholder="제목입력" name="fileTitle" required
                       value="${fileDb.fileTitle}">
            </div>
            <%--todo : 내용(fileContent)--%>
            <div class="mb-3">
                <label for="fileContent" class="form-label">File Content</label>
                <input type="text" class="form-control" id="fileContent" placeholder="내용입력" name="fileContent" required
                       value="${fileDb.fileContent}">
            </div>

            <%--todo : 기존에 업로드 된 이미지 확인 --%>
            <div class="mb-3 col-12 " style="width: 18rem;">
                <img src="${fileDb.fileUrl}" class="card-img-top" alt="강의">
            </div>
            <%--todo : 파일 업로드 버튼--%>
            <div class="input-group">
                <%--todo : 파일 선택 상자 : 백엔드 전송--%>
                <input type="file" class="form-control" name="image" required >
                <%--todo : 업로든 버튼--%>
                <button class="btn btn-outline-secondary" type="submit">업로드</button>
            </div>
        </form>
    </div>
</div>
<%--todo : 꼬리말--%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>

