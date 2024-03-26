<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2024-03-22
  Time: 오전 9:27
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
        <form action="/basic/dept/edit/${faq1.fno}" method="post">
            <input type="hidden" name="_method" value="put"/>
            <%--todo input 태그 : 부서명--%>
            <div class="input-group mb-3 mt-3">
                <span class="input-group-text" id="title" >title</span>
                <input type="text" class="form-control" name="title" placeholder="Insert title" value="${faq1.title}">
            </div>
            <%--todo input 태그 : 부서위치--%>
            <div class="input-group mb-3">
                <span class="input-group-text" id="content">content</span>
                <input type="text" class="form-control" name="content" placeholder="Insert loc" value="${faq1.content}">
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