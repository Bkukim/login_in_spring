<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2024-03-20
  Time: 오후 3:38
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
${emp}
<%--todo 꼬리말--%>
<jsp:include page="../../common/footer.jsp"/>

</body>
</html>
