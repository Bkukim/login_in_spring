<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2024-03-20
  Time: 오후 5:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body><%--todo 머리말--%>
<jsp:include page="../../common/header.jsp"/>
<%--todo 본문--%>
<div class="container">
    <div>
        <form action="/basic/faq/save" method="post">
            <%--todo input 태그 : 부서명--%>
            <div class="input-group mb-3 mt-3">
                <span class="input-group-text" id="title" >title</span>
                <input type="text" class="form-control" name="title" placeholder="Insert title">
            </div>
            <%--todo input 태그 : 부서위치--%>
            <div class="input-group mb-3">
                <span class="input-group-text" id="loc">content</span>
                <input type="text" class="form-control" name="content" placeholder="Insert content">
            </div>
            <div class="mb-3">
                <button type="submit" class="btn btn-primary">
                    저장
                </button>
            </div>
        </form>
    </div>
</div>
<%--todo 꼬리말--%>
<jsp:include page="../../common/footer.jsp"/>


</body>
</html>
