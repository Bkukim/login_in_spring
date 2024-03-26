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
        <form action="/basic/dept/save" method="post">
            <%--todo input 태그 : 부서명--%>
                <div class="input-group mb-3 mt-3">
                    <span class="input-group-text" id="dname" >dname</span>
                    <input type="text" class="form-control" name="dname" placeholder="Insert dname">
                </div>
            <%--todo input 태그 : 부서위치--%>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="loc">loc</span>
                    <input type="text" class="form-control" name="loc" placeholder="Insert loc">
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
