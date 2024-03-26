<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2024-03-20
  Time: 오후 5:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--todo 머리말--%>
<jsp:include page="../../common/header.jsp"/>
<%--todo 본문--%>
<div>
    <div>
        <form class="container mt-3" action="/basic/emp/edit/${emp.eno}" method="post">
            <input type="hidden" name="_method" value="put">
            <div class="input-group mb-3">
                <span class="input-group-text" id="ename">ename</span>
                <input type="text" class="form-control" name="ename" value="${emp.ename}">
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="job">job</span>
                <input type="text" class="form-control" name="job" value="${emp.job}">
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="manager">manager</span>
                <input type="text" class="form-control" name="manager" value="${emp.manager}">
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="hiredate">hire date</span>
                <input type="text" class="form-control" name="hiredate" value="${emp.hiredate}">
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="salary">salary</span>
                <input type="text" class="form-control" name="salary" value="${emp.salary}">
            </div><div class="input-group mb-3">
                <span class="input-group-text" id="commission">commission</span>
                <input type="text" class="form-control" name="commission" value="${emp.commission}">
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="dno">dno</span>
                <input type="text" class="form-control" name="dno" value="${emp.dno}">
            </div>
            <div>
                <button class="btn btn-primary" type="submit">
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
